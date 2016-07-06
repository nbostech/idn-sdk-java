package com.nbos.capi.modules.identity.v0;

import com.nbos.capi.api.v0.TokenApiModel;

public class NewMemberApiModel {
    public MemberApiModel getMember() {
        return member;
    }

    public TokenApiModel getToken() {
        return token;
    }

    MemberApiModel member;
    TokenApiModel token;
}
