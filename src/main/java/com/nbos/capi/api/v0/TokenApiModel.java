package com.nbos.capi.api.v0;


public class TokenApiModel {


    public Long getExpires_in() {
        return expires_in;
    }

    public String getScope() {
        return scope;
    }

    public String getToken_type() {
        return token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    String scope;
    Long expires_in;
    String token_type;
    String refresh_token;
    String access_token;

}
