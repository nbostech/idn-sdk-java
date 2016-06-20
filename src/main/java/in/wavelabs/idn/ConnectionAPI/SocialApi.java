package in.wavelabs.idn.ConnectionAPI;


import com.nbos.capi.modules.identity.v0.NewMemberApiModel;
import com.nbos.capi.modules.identity.v0.SocialConnectUrlResponse;

import in.wavelabs.idn.APIClient;
import in.wavelabs.idn.ConnectionAPI.service.StarterClient;
import in.wavelabs.idn.DataModel.auth.Connect;
import in.wavelabs.idn.DataModel.auth.DigitsConnect;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vivek Kiran on 05/02/16.
 */
public class SocialApi {


    public static void authorizeAndConnect(String clientToken,String service,String code, String state, final NBOSCallback<NewMemberApiModel> nbosCallback) {
        Call<NewMemberApiModel> call = StarterClient.getStarterAPI().authorize(clientToken, service, code,state);
        call.enqueue(new Callback<NewMemberApiModel>() {


            @Override
            public void onResponse(Call<NewMemberApiModel> call, Response<NewMemberApiModel> response) {
                nbosCallback.onResponse(response);
            }

            @Override
            public void onFailure(Call<NewMemberApiModel> call, Throwable t) {
                nbosCallback.onFailure(t);
            }
        });
    }
    public static void socialConnect(String accessToken, String service, final NBOSCallback<NewMemberApiModel> nbosCallback) {

            Connect cn = new Connect();
            cn.setClientId(APIClient.clientId);
            cn.setAccessToken(accessToken);
            cn.setExpiresIn("");

            Call<NewMemberApiModel> call = StarterClient.getStarterAPI().connect(accessToken, service, cn);
            call.enqueue(new Callback<NewMemberApiModel>() {


                @Override
                public void onResponse(Call<NewMemberApiModel> call, Response<NewMemberApiModel> response) {
                        nbosCallback.onResponse(response);
                    }

                @Override
                public void onFailure(Call<NewMemberApiModel> call, Throwable t) {
                    nbosCallback.onFailure(t);
                }
            });

        }

    public static void socialLogin(String clientToken, final String service, final NBOSCallback<SocialConnectUrlResponse> nbosCallback) {
        Call<SocialConnectUrlResponse> call = StarterClient.getStarterAPI().socialLogin(clientToken,service);
        call.enqueue(new Callback<SocialConnectUrlResponse>() {
            @Override
            public void onResponse(Call<SocialConnectUrlResponse> call, Response<SocialConnectUrlResponse> response) {
//                    Intent i = new Intent(context, WebViewActivity.class);
//                    i.putExtra("name", service);
//                    i.putExtra("url", response.body().getUrl());
//                   // context.startActivity(i);
//
//                ((Activity)context).startActivityForResult(i, 0);
//
//      //              nbosCallback.onSuccess(response);
                nbosCallback.onResponse(response);

            }

            @Override
            public void onFailure(Call<SocialConnectUrlResponse> call, Throwable t) {

            }
        });
    }


    public static void digitsConnect(String clientToken, String provider, String authorization, String firstName, String lastName, String emailId, final NBOSCallback<NewMemberApiModel>  nbosCallback){

        DigitsConnect cn = new DigitsConnect();
        cn.setClientId(APIClient.clientId);
        cn.setFirstName(firstName);
        cn.setLastName(lastName);
        cn.setEmailId(emailId);
        cn.setProvider(provider);
        cn.setAuthorization(authorization);

        Call<NewMemberApiModel> call = StarterClient.getStarterAPI().digitsConnect(clientToken,cn);
        call.enqueue(new Callback<NewMemberApiModel>() {

            @Override
            public void onResponse(Call<NewMemberApiModel> call, Response<NewMemberApiModel> response) {
                nbosCallback.onResponse(response);

            }

            @Override
            public void onFailure(Call<NewMemberApiModel> call, Throwable t) {
                nbosCallback.onFailure(t);

            }
        });
    }


}
