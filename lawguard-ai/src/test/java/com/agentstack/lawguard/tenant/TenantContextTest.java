package com.agentstack.lawguard.tenant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TenantContext ThreadLocal 行为单元测试：
 * 默认租户回退、空值回退、显式设置/清除、线程隔离。
 */
class TenantContextTest {

    @AfterEach
    void tearDown() {
        TenantContext.clear();
    }

    @Test
    void defaultsToDemoWhenNeverSet() {
        assertThat(TenantContext.getTenantId()).isEqualTo("demo");
    }

    @Test
    void fallsBackToDemoForBlankValue() {
        TenantContext.setTenantId("  ");
        assertThat(TenantContext.getTenantId()).isEqualTo("demo");
    }

    @Test
    void fallsBackToDemoForNullValue() {
        TenantContext.setTenantId(null);
        assertThat(TenantContext.getTenantId()).isEqualTo("demo");
    }

    @Test
    void returnsExplicitTenantId() {
        TenantContext.setTenantId("law_firm_a");
        assertThat(TenantContext.getTenantId()).isEqualTo("law_firm_a");
    }

    @Test
    void clearResetsToDemo() {
        TenantContext.setTenantId("law_firm_b");
        TenantContext.clear();
        assertThat(TenantContext.getTenantId()).isEqualTo("demo");
    }

    @Test
    void isIsolatedBetweenThreads() throws Exception {
        TenantContext.setTenantId("main-tenant");
        Thread worker = new Thread(() -> {
            TenantContext.setTenantId("worker-tenant");
            assertThat(TenantContext.getTenantId()).isEqualTo("worker-tenant");
        });
        worker.start();
        worker.join();
        // 主线程不受子线程影响
        assertThat(TenantContext.getTenantId()).isEqualTo("main-tenant");
    }
}