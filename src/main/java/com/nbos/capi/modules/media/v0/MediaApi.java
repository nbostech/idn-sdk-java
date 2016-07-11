package com.nbos.capi.modules.media.v0;

import com.nbos.capi.api.v0.AbstractApiContext;
import com.nbos.capi.api.v0.IdnCallback;
import com.nbos.capi.api.v0.NetworkApi;
import com.nbos.capi.api.v0.RestMessage;
import com.nbos.capi.api.v0.TokenApiModel;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vivekkiran on 7/1/16.
 */

public class MediaApi extends NetworkApi {
    public MediaApi() {
        super();
        setModuleName("media");
        setRemoteApiClass(MediaRemoteApi.class);
    }

    public MediaApiModel getMedia(String uuid, String mediafor, final IdnCallback<MediaApiModel> callback) {
        MediaRemoteApi mediaRemoteApi = getRemoteApi();
        TokenApiModel tokenApiModel = getApiContext().getUserToken(moduleName);
        Call<MediaApiModel> call = mediaRemoteApi.getMedia("Bearer " + tokenApiModel.getAccess_token(), uuid, mediafor);

        MediaApiModel media = null;
        call.enqueue(new Callback<MediaApiModel>() {
            @Override
            public void onResponse(Call<MediaApiModel> call, Response<MediaApiModel> response) {
                if (response.code() == 200) {
                    callback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<MediaApiModel> call, Throwable t) {
                callback.onFailure(t);
            }
        });
        return media;
    }

    public RestMessage uploadMedia(String id, String mediafor, Map<String, RequestBody> params,final IdnCallback<RestMessage> callback) {
        MediaRemoteApi mediaRemoteApi = getRemoteApi();
        TokenApiModel tokenApiModel = getApiContext().getUserToken(moduleName);
        Call<RestMessage> call = mediaRemoteApi.uploadMedia("Bearer " + tokenApiModel.getAccess_token(),id,mediafor,params);

        RestMessage media = null;
        call.enqueue(new Callback<RestMessage>() {
            @Override
            public void onResponse(Call<RestMessage> call, Response<RestMessage> response) {
                if (response.code() == 200) {
                    callback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<RestMessage> call, Throwable t) {
                callback.onFailure(t);
            }
        });
        return media;
    }
}
