package com.nbos.capi.modules.tenant.v0;

public class TenantApiModel {
    Long id;

    public String getTenantId() {
        return tenantId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    String tenantId;
    String name;
    Boolean isPublic;
}
