package com.nbos.capi.modules.identity.v0;

import com.nbos.capi.api.v0.AbstractApiContext;
import com.nbos.capi.api.v0.IdnCallback;
import com.nbos.capi.api.v0.NetworkApi;
import com.nbos.capi.api.v0.RestMessage;
import com.nbos.capi.api.v0.TokenApiModel;

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
        final IdentityRemoteApi remoteApi = getRemoteApi();

        Map map = getApiContext().getClientCredentials();
        String clientId = (String)map.get("client_id");
        String secret = (String)map.get("client_secret");


        // The following works.
        Call<TokenApiModel> call = remoteApi.getToken(clientId,secret,"client_credentials");
        try {
            Response<TokenApiModel> response = call.execute();
            TokenApiModel tokenApiModel = response.body();
            System.out.println ( "token:" + tokenApiModel.getAccess_token() );
            getApiContext().setClientToken(tokenApiModel);
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
        TokenApiModel tokenApiModel = getApiContext().getClientToken();
        Call<NewMemberApiModel> call = identityRemoteApi.login("Bearer " + tokenApiModel.getAccess_token(),loginModel);

        NewMemberApiModel member=null;
        call.enqueue(new Callback<NewMemberApiModel>() {
            @Override
            public void onResponse(Call<NewMemberApiModel> call, Response<NewMemberApiModel> response) {
                if(response.code()==200) {
                    NewMemberApiModel newMemberApiModel = response.body();
                    getApiContext().setUserToken(moduleName,newMemberApiModel.getToken());
                }
                callback.onResponse(response);
            }

            @Override
            public void onFailure(Call<NewMemberApiModel> call, Throwable t) {
                callback.onFailure(t);
            }
        });
        return member;
    }
    
    public NewMemberApiModel signup(MemberSignupModel memberSignupModel, final IdnCallback<NewMemberApiModel> callback) {
        IdentityRemoteApi identityRemoteApi = getRemoteApi();
        TokenApiModel tokenApiModel = getApiContext().getClientToken();
        Call<NewMemberApiModel> call = identityRemoteApi.signup("Bearer " + tokenApiModel.getAccess_token(),memberSignupModel);

        NewMemberApiModel member=null;
        call.enqueue(new Callback<NewMemberApiModel>() {
            @Override
            public void onResponse(Call<NewMemberApiModel> call, Response<NewMemberApiModel> response) {
                if(response.code()==200) {
                    NewMemberApiModel newMemberApiModel = response.body();
                    getApiContext().setUserToken(moduleName,newMemberApiModel.getToken());
                }
                callback.onResponse(response);
            }

            @Override
            public void onFailure(Call<NewMemberApiModel> call, Throwable t) {
                callback.onFailure(t);
            }
        });
        return member;
    }
    public NewMemberApiModel connect(SocialConnectApiModel socialConnectApiModel, String connectService, final IdnCallback<NewMemberApiModel> callback) {
        IdentityRemoteApi identityRemoteApi = getRemoteApi();
        TokenApiModel tokenApiModel = getApiContext().getClientToken();
        Call<NewMemberApiModel> call = identityRemoteApi.connect("Bearer " + tokenApiModel.getAccess_token(), connectService, socialConnectApiModel);

        NewMemberApiModel member=null;
        call.enqueue(new Callback<NewMemberApiModel>() {
            @Override
            public void onResponse(Call<NewMemberApiModel> call, Response<NewMemberApiModel> response) {
                if(response.code()==200) {
                    NewMemberApiModel newMemberApiModel = response.body();
                    getApiContext().setUserToken(moduleName,newMemberApiModel.getToken());
                }
                callback.onResponse(response);
            }

            @Override
            public void onFailure(Call<NewMemberApiModel> call, Throwable t) {
                callback.onFailure(t);
            }
        });
        return member;
    }
    public NewMemberApiModel authorize(String authorizeService, String code, String state, final IdnCallback<NewMemberApiModel> callback) {
        IdentityRemoteApi identityRemoteApi = getRemoteApi();
        TokenApiModel tokenApiModel = getApiContext().getClientToken();
        Call<NewMemberApiModel> call = identityRemoteApi.authorize("Bearer " + tokenApiModel.getAccess_token(),authorizeService,code,state);

        NewMemberApiModel member=null;
        call.enqueue(new Callback<NewMemberApiModel>() {
            @Override
            public void onResponse(Call<NewMemberApiModel> call, Response<NewMemberApiModel> response) {
                if(response.code()==200) {
                    NewMemberApiModel newMemberApiModel = response.body();
                    getApiContext().setUserToken(moduleName,newMemberApiModel.getToken());
                }
                callback.onResponse(response);
            }

            @Override
            public void onFailure(Call<NewMemberApiModel> call, Throwable t) {
                callback.onFailure(t);
            }
        });
        return member;
    }
    public MemberApiModel getMemberDetails(String uuid, final IdnCallback<MemberApiModel> callback) {
        IdentityRemoteApi identityRemoteApi = getRemoteApi();
        TokenApiModel tokenApiModel = getApiContext().getUserToken(moduleName);
        Call<MemberApiModel> call = identityRemoteApi.getMemberDetails("Bearer " + tokenApiModel.getAccess_token(),uuid);

        MemberApiModel member=null;
        call.enqueue(new Callback<MemberApiModel>() {
            @Override
            public void onResponse(Call<MemberApiModel> call, Response<MemberApiModel> response) {
                if(response.code()==200) {
                    callback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<MemberApiModel> call, Throwable t) {
                callback.onFailure(t);
            }
        });
        return member;
    }
    public MemberApiModel updateMemberDetails(String uuid,MemberApiModel memberApiModel, final IdnCallback<MemberApiModel> callback) {
        IdentityRemoteApi identityRemoteApi = getRemoteApi();
        TokenApiModel tokenApiModel = getApiContext().getUserToken(moduleName);
        Call<MemberApiModel> call = identityRemoteApi.updateMemberDetails("Bearer " + tokenApiModel.getAccess_token(),uuid,memberApiModel);

        MemberApiModel member=null;
        call.enqueue(new Callback<MemberApiModel>() {
            @Override
            public void onResponse(Call<MemberApiModel> call, Response<MemberApiModel> response) {
                if(response.code()==200) {
                    callback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<MemberApiModel> call, Throwable t) {
                callback.onFailure(t);
            }
        });
        return member;
    }
    public RestMessage updateCredentials(UpdatePasswordApiModel updatePasswordApiModel, final IdnCallback<RestMessage> callback) {
        IdentityRemoteApi identityRemoteApi = getRemoteApi();
        TokenApiModel tokenApiModel = getApiContext().getUserToken(moduleName);
        Call<RestMessage> call = identityRemoteApi.updateCredentials("Bearer " + tokenApiModel.getAccess_token(),updatePasswordApiModel);

        RestMessage member=null;
        call.enqueue(new Callback<RestMessage>() {
            @Override
            public void onResponse(Call<RestMessage> call, Response<RestMessage> response) {
                if(response.code()==200) {
                    callback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<RestMessage> call, Throwable t) {
                callback.onFailure(t);
            }
        });
        return member;
    }
    public RestMessage resetCredentials(ResetPasswordModel resetPasswordModel, final IdnCallback<RestMessage> callback) {
        IdentityRemoteApi identityRemoteApi = getRemoteApi();
        TokenApiModel tokenApiModel = getApiContext().getUserToken(moduleName);
        Call<RestMessage> call = identityRemoteApi.resetCredentials("Bearer " + tokenApiModel.getAccess_token(),resetPasswordModel);

        RestMessage member=null;
        call.enqueue(new Callback<RestMessage>() {
            @Override
            public void onResponse(Call<RestMessage> call, Response<RestMessage> response) {
                if(response.code()==200) {
                    callback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<RestMessage> call, Throwable t) {
                callback.onFailure(t);
            }
        });
        return member;
    }
    public RestMessage logout(final IdnCallback<RestMessage> callback) {
        IdentityRemoteApi identityRemoteApi = getRemoteApi();
        TokenApiModel tokenApiModel = getApiContext().getUserToken(moduleName);
        Call<RestMessage> call = identityRemoteApi.logout("Bearer " + tokenApiModel.getAccess_token());
        RestMessage member=null;
        call.enqueue(new Callback<RestMessage>() {
            @Override
            public void onResponse(Call<RestMessage> call, Response<RestMessage> response) {
                if(response.code()==200) {
                    callback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<RestMessage> call, Throwable t) {
                callback.onFailure(t);
            }
        });
        return member;
    }
    public SocialConnectUrlResponse socialWebViewLogin(String connectService, final IdnCallback<SocialConnectUrlResponse> callback) {
        IdentityRemoteApi identityRemoteApi = getRemoteApi();
        TokenApiModel tokenApiModel = getApiContext().getUserToken(moduleName);
        Call<SocialConnectUrlResponse> call = identityRemoteApi.socialWebViewLogin("Bearer " + tokenApiModel.getAccess_token(),connectService);
        SocialConnectUrlResponse member=null;
        call.enqueue(new Callback<SocialConnectUrlResponse>() {
            @Override
            public void onResponse(Call<SocialConnectUrlResponse> call, Response<SocialConnectUrlResponse> response) {
                if(response.code()==200) {
                    callback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<SocialConnectUrlResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
        return member;
    }

}
