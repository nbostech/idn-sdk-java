package com.nbos.capi.modules.identity.v0;

public class NewMemberApiModel {
    public MemberApiModel getMember() {
        return member;
    }

    public com.nbos.capi.api.v0.TokenApiModel getToken() {
        return token;
    }

    MemberApiModel member;
    com.nbos.capi.api.v0.TokenApiModel token;
}
