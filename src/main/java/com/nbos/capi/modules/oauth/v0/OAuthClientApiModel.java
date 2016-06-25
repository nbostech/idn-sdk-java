package com.nbos.capi.modules.oauth.v0;


import java.util.List;

public class OAuthClientApiModel {

    Long id;

    public String getClientName() {
        return clientName;
    }

    public Long getId() {
        return id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public Boolean getRevoked() {
        Boolean revoked = false;
        return revoked;
    }

    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public List<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public List<String> getResourceIds() {
        return resourceIds;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public List<String> getRedirectUris() {
        return redirectUris;
    }

    String clientName;
    String tenantId;
    String clientId;
    String clientSecret;

    Integer accessTokenValiditySeconds;
    Integer refreshTokenValiditySeconds;

    List<String> authorities;
    List<String> authorizedGrantTypes;
    List<String> resourceIds;
    List<String> scopes;
    List<String> redirectUris;
}
