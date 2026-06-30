package com.agentstack.lawguard.tenant;

public final class TenantContext {
    private static final ThreadLocal<String> CURRENT = new ThreadLocal<>();
    private TenantContext() {}
    public static void setTenantId(String tenantId) { CURRENT.set(tenantId == null || tenantId.isBlank() ? "demo" : tenantId); }
    public static String getTenantId() { return CURRENT.get() == null ? "demo" : CURRENT.get(); }
    public static void clear() { CURRENT.remove(); }
}
