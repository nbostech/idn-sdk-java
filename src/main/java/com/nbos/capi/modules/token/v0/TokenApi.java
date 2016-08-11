package com.nbos.capi.modules.token.v0;

import com.nbos.capi.api.v0.NetworkApi;
import com.nbos.capi.api.v0.models.TokenApiModel;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by vivekkiran on 8/11/16.
 */

public class TokenApi extends NetworkApi {
    public TokenApi() {
        super();
        setModuleName("token");
        setRemoteApiClass(TokenRemoteApi.class);
    }

    public TokenApiModel getClientToken() {
        final TokenRemoteApi remoteApi = getRemoteApi();

        Map map = apiContext.getClientCredentials();
        String clientId = (String) map.get("client_id");
        String secret = (String) map.get("client_secret");

        // The following works.
        Call<TokenApiModel> call = remoteApi.getToken(clientId, secret, "client_credentials");
        try {
            Response<TokenApiModel> response = call.execute();
            TokenApiModel tokenApiModel = response.body();
            System.out.println("token:" + tokenApiModel.getAccess_token());
            apiContext.setClientToken(tokenApiModel);
            return tokenApiModel;
        } catch (IOException x) {
            System.out.println("Unable to get client token");
            return null;
        }

    }
}
