package in.wavelabs.idn.ConnectionAPI;



import com.nbos.capi.api.v0.RestMessage;
import com.nbos.capi.modules.identity.v0.NewMemberApiModel;

import in.wavelabs.idn.APIClient;
import in.wavelabs.idn.ConnectionAPI.service.StarterClient;
import in.wavelabs.idn.DataModel.auth.ChangePassword;
import in.wavelabs.idn.DataModel.auth.Login;
import in.wavelabs.idn.DataModel.auth.Reset;
import in.wavelabs.idn.DataModel.auth.SignUp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vivek Kiran on 05/02/16.
 */
public class AuthApi {


    public static void resetPassword(String email, String clientToken, final NBOSCallback<RestMessage> nbosCallback) {
        Reset rb = new Reset();
        rb.setEmail(email);
      //  String token = TokenPrefrences.getClientToken(context);
        Call<RestMessage> call = StarterClient.getStarterAPI().forgot(clientToken,rb);
        call.enqueue(new Callback<RestMessage>() {


            @Override
            public void onResponse(Call<RestMessage> call, Response<RestMessage> response) {
                nbosCallback.onResponse(response);


            }

            @Override
            public void onFailure(Call<RestMessage> call, Throwable t) {
                nbosCallback.onFailure(t);
            }


        });


    }


    public static void createAccount(String clientToken,String email, String username, String firstName, String lastName, String password, final NBOSCallback<NewMemberApiModel> nbosCallback) {

        SignUp sb = new SignUp();
        sb.setClientId(APIClient.clientId);
        sb.setEmail(email);
        sb.setUsername(username);
        sb.setFirstName(firstName);
        sb.setLastName(lastName);
        sb.setPassword(password);
        Call<NewMemberApiModel> call = StarterClient.getStarterAPI().signup(clientToken,sb);
        call.enqueue(new Callback<NewMemberApiModel>() {

            @Override
            public void onResponse(Call<NewMemberApiModel> call, Response<NewMemberApiModel> response) {
                if(response.body() !=null) {
                    nbosCallback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<NewMemberApiModel> call, Throwable t) {
                nbosCallback.onFailure(t);
            }


        });


    }



    public static void login(String clientToken, String email, String password, final NBOSCallback<NewMemberApiModel> nbosCallback) {
            Login lb = new Login();
        lb.setClientId(APIClient.clientId);
        lb.setUsername(email);
        lb.setPassword(password);
        Call<NewMemberApiModel> call = StarterClient.getStarterAPI().login(clientToken,lb);
        call.enqueue(new Callback<NewMemberApiModel>() {


            @Override
            public void onResponse(Call<NewMemberApiModel> call, Response<NewMemberApiModel> response) {
                if(response.body() != null) {
                    nbosCallback.onResponse(response);
                } else {
                  //  Toast.makeText(context,"Error 400: Bad Request", Toast.LENGTH_SHORT).show();
                }

                }

            @Override
            public void onFailure(Call<NewMemberApiModel> call, Throwable t) {
                nbosCallback.onFailure(t);
               // Toast.makeText(context,"Error connecting to host, Please Check your Internet Connection", Toast.LENGTH_SHORT).show();

            }

        });
    }

    public static void logout(String authorization, final NBOSCallback<NewMemberApiModel> nbosCallback) {
        Call<NewMemberApiModel> call = StarterClient.getStarterAPI().logout(authorization);
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

    public static void changePassword(String accessToken, String oldPassword, String newPassword, final NBOSCallback<RestMessage> nbosCallback){
        ChangePassword cp = new ChangePassword();
        cp.setPasssword(oldPassword);
        cp.setNewPassword(newPassword);
        Call<RestMessage> call = StarterClient.getStarterAPI().changePassword(accessToken,cp);
        call.enqueue(new Callback<RestMessage>() {


            @Override
            public void onResponse(Call<RestMessage> call, Response<RestMessage> response) {
                nbosCallback.onResponse(response);

            }

            @Override
            public void onFailure(Call<RestMessage> call, Throwable t) {
                nbosCallback.onFailure(t);
            }


        });

    }


}
