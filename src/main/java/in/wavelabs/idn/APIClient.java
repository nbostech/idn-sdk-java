package in.wavelabs.idn;

import com.nbos.capi.api.v0.TokenApiModel;
import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import in.wavelabs.idn.ConnectionAPI.service.StarterClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

/**
 * Created by vivekkiran on 6/20/16.
 */
public class APIClient {
    public static String clientId;
    public static String clientSecret;
    private List<String> hostsArray;

    public APIClient(String clientId, String clientSecret){
        APIClient.clientId = clientId;
        APIClient.clientSecret = clientSecret;
    }
    public APIClient(String clientId, String clientSecret, List<String> hostsArray) {
        APIClient.clientId = clientId;
        APIClient.clientSecret = clientSecret;
        this.hostsArray = hostsArray;

    }

    public static void getToken(final NBOSCallback<TokenApiModel> nbosCallback){
        Call<TokenApiModel> call = StarterClient.getStarterAPI().getToken(clientId, clientSecret, "client_credentials");
        call.enqueue(new Callback<TokenApiModel>() {

            @Override
            public void onResponse(Call<TokenApiModel> call, Response<TokenApiModel> response) {
                nbosCallback.onResponse(response);
            }

            @Override
            public void onFailure(Call<TokenApiModel> call, Throwable t) {
                nbosCallback.onFailure(t);

            }


        });

    }
    public static void refreshToken(String clientId, String clientSecret, String refreshToken, final NBOSCallback<TokenApiModel> nbosCallback){
        Call<TokenApiModel> call = StarterClient.getStarterAPI().refreshToken(clientId, clientSecret, "refresh_token",refreshToken);
        call.enqueue(new Callback<TokenApiModel>() {


            @Override
            public void onResponse(Call<TokenApiModel> call, Response<TokenApiModel> response) {
                nbosCallback.onResponse(response);
            }


            @Override
            public void onFailure(Call<TokenApiModel> call, Throwable t) {
                nbosCallback.onFailure(t);


            }


        });

    }

}
