package com.nbos.capi.modules.identity.v0;

import com.nbos.capi.api.v0.AbstractApiContext;
import com.nbos.capi.api.v0.IdnCallback;
import com.nbos.capi.api.v0.NetworkApi;
import com.nbos.capi.api.v0.TokenApiModel;
import com.nbos.capi.modules.ids.v0.IDS;

import java.io.IOException;
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
        final IdentityRemoteApi remoteApi = identityApi.getRemoteApi();
        Map map = AbstractApiContext.get().getClientCredentials();
        String clientId = (String)map.get("client_id");
        String secret = (String)map.get("client_secret");


        // The following works.
        Call<TokenApiModel> call = remoteApi.getToken(clientId,secret,"client_credentials");
        try {
            Response<TokenApiModel> response = call.execute();
            TokenApiModel tokenApiModel = response.body();
            System.out.println ( "token:" + tokenApiModel.getAccess_token() );
            AbstractApiContext.get().setClientToken(tokenApiModel);
            return tokenApiModel;
        } catch( IOException x ) {
            System.out.println("Unable to get client token");
            return null;
        }

        // TODO: need to figure out how to do an asynchronous call & wait for the response
        /*
        final Call<TokenApiModel> call = remoteApi.getToken(clientId,secret,"client_credentials");
        final TokenApiModel[] result = new TokenApiModel[1];
        Thread thr2 = new Thread(new Runnable() {
            @Override
            public void run() {
                call.enqueue(new Callback<TokenApiModel>() {
                    @Override
                    public void onResponse(Call<TokenApiModel> call, Response<TokenApiModel> response) {
                        TokenApiModel tokenApiModel = response.body();
                        System.out.println ( "token:" + tokenApiModel.getAccess_token() );
                        AbstractApiContext.get().setClientToken(tokenApiModel);
                        result[0]=tokenApiModel;
                        System.out.println("results: " + result[0].getAccess_token());
                        result.notify();
                    }

                    @Override
                    public void onFailure(Call<TokenApiModel> call, Throwable t) {
                        System.out.println("failure:");
                        result.notify();
                    }
                });
            }
        });
        thr2.start();

        Thread thr = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (result) {
                    // wait until we fetch the resposne.
                    try {
                        if(result[0]==null) {
                            System.out.println("Waiting for response");
                            result.wait(); // This hangs forever, retrofit isn't calling the callback.
                        }
                    }catch(InterruptedException y) {
                        System.out.println("Interrupted"+ y.toString());
                    }
                }
            }
        });
        thr.run();
        try {
            thr.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result[0];
        */

    }

    public NewMemberApiModel login(LoginModel loginModel, final IdnCallback<NewMemberApiModel> callback) {

        IdentityRemoteApi identityRemoteApi = getRemoteApi();
        TokenApiModel tokenApiModel = AbstractApiContext.get().getClientToken();
        Call<NewMemberApiModel> call = identityRemoteApi.login("Bearer " + tokenApiModel.getAccess_token(),loginModel);

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
