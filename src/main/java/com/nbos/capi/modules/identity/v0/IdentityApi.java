package com.nbos.capi.modules.identity.v0;

import com.nbos.capi.api.v0.*;
import com.nbos.capi.api.v0.TokenApiModel;
import com.nbos.capi.modules.ids.v0.IDS;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IdentityApi extends NetworkApi {
    public TokenApiModel getToken(){
        return null;
    }

    public NewMemberApiModel login(LoginModel loginModel, final IdnCallback<NewMemberApiModel> callback) {

        IdentityRemoteApi identityRemoteApi = getRemoteApi();
        TokenApiModel tokenApiModel = AbstractApiContext.getApiContext().getToken("client");
        Call<NewMemberApiModel> call = identityRemoteApi.login(tokenApiModel.getAccess_token(),loginModel);

        NewMemberApiModel member=null;
        call.enqueue(new Callback<NewMemberApiModel>() {

            @Override
            public void onResponse(Call<NewMemberApiModel> call, Response<NewMemberApiModel> response) {
                callback.onResponse(response);
                //member = response.body();
            }

            @Override
            public void onFailure(Call<NewMemberApiModel> call, Throwable t) {
                callback.onFailure(t);
            }
        });
        return member;
    }
    public NewMemberApiModel signup(MemberSignupModel memberSignupModel) {
        return null;
    }
}
