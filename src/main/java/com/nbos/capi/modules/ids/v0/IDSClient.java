package com.nbos.capi.modules.ids.v0;


import com.nbos.capi.api.v0.NetworkApi;
import com.nbos.capi.api.v0.NetworkCallback;
import com.nbos.capi.api.v0.TokenApiModel;
import com.nbos.capi.modules.identity.v0.IdentityApi;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Callback;

/**
 * Created by vivekkiran on 6/16/16.
 */

public class IDSClient {

    private void getToken() {

        NetworkApi networkApi = IDS.getModuleApi("networkapi");
        Request request = networkApi.newRequest("/oauth/token")
                .build();
        networkApi.get(request, new NetworkCallback() {
            @Override
            public void onResponse(Call call, Response response) {


            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

//        IdentityApi identityApi = IDS.getModuleApi("identity");
//        identityApi.getToken("","","").enqueue(new Callback<TokenApiModel>() {
//            @Override
//            public void onResponse(retrofit2.Call<TokenApiModel> call, retrofit2.Response<TokenApiModel> response) {
//                //nbosCallback.onResponse(response);
//             //   Log.i("IDSClient","token received response");
//            }
//
//            @Override
//            public void onFailure(retrofit2.Call<TokenApiModel> call, Throwable t) {
//               // Timber.i("IDSClient","token failure");
//            }
//        });

    }
}
