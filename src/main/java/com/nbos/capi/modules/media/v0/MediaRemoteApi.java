package com.nbos.capi.modules.media.v0;

import com.nbos.capi.api.v0.RestMessage;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by vivekkiran on 7/1/16.
 */

public interface MediaRemoteApi {
    String mediaUrl = "/api/media/v0/media";
    @GET(mediaUrl)
    Call<MediaApiModel> getMedia(@Header("Authorization") String authorization, @Query("id") String uuid, @Query("mediafor") String mediafor);

    @Multipart
    @POST(mediaUrl)
    Call<RestMessage> uploadMedia(@Header("Authorization") String authorization, @PartMap Map<String, RequestBody> params);
}
