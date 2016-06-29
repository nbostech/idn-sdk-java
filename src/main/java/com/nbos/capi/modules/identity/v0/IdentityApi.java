package com.nbos.capi.modules.identity.v0;

import com.nbos.capi.api.v0.AbstractApiContext;
import com.nbos.capi.api.v0.IdnCallback;
import com.nbos.capi.api.v0.NetworkApi;
import com.nbos.capi.api.v0.TokenApiModel;
import com.nbos.capi.modules.ids.v0.IDS;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IdentityApi extends NetworkApi {
    public IdentityApi() {
        super();
        setModuleName("identity");
        setRemoteApiClass(IdentityRemoteApi.class);
    }

    public TokenApiModel getClientToken() {
        IdentityApi identityApi = IDS.getModuleApi("identity");
        IdentityRemoteApi remoteApi = identityApi.getRemoteApi();
        Map map = AbstractApiContext.get().getClientCredentials();
        String clientId = (String)map.get("client_id");
        String secret = (String)map.get("client_secret");

        final TokenApiModel[] result = new TokenApiModel[1];
        Call<TokenApiModel> call = remoteApi.getToken(clientId,secret,"client_credentials");
        call.enqueue(new Callback<TokenApiModel>() {
            @Override
            public void onResponse(Call<TokenApiModel> call, Response<TokenApiModel> response) {
                TokenApiModel tokenApiModel = response.body();
                System.out.println ( tokenApiModel );
                AbstractApiContext.get().setToken("client",tokenApiModel);
            }

            @Override
            public void onFailure(Call<TokenApiModel> call, Throwable t) {
                System.out.println("failure:");
            }
        });
        return result[0];
    }

    public TokenApiModel getToken(){
        return null;
    }

    public NewMemberApiModel login(LoginModel loginModel, final IdnCallback<NewMemberApiModel> callback) {

        IdentityRemoteApi identityRemoteApi = getRemoteApi();
      //  TokenApiModel tokenApiModel = AbstractApiContext.get().getToken("client");
        Call<NewMemberApiModel> call = identityRemoteApi.login("sample",loginModel);

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
