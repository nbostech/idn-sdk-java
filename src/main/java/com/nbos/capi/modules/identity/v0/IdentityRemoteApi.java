package com.nbos.capi.modules.identity.v0;

import com.nbos.capi.api.v0.RestMessage;
import com.nbos.capi.api.v0.TokenApiModel;
import com.nbos.capi.modules.media.v0.MediaApiModel;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface IdentityRemoteApi {

    String baseIdentityUrl = "/api/identity/v0";
    String tokenUrl = "/oauth/token";
    String loginUrl = baseIdentityUrl + "/auth/login";
    String signupUrl = baseIdentityUrl + "/users/signup";
    String connectUrl = baseIdentityUrl + "/auth/social/{connectService}/connect";
    String authorizeUrl = baseIdentityUrl + "/auth/social/{authorizeService}/authorize";
    String profileUrl = baseIdentityUrl + "/users/{uuid}";
    String forgotUrl = baseIdentityUrl + "/auth/forgotPassword";
    String changeUrl  =baseIdentityUrl + "/auth/changePassword";
    String logoutUrl = baseIdentityUrl + "/auth/logout";
    String socialLoginUrl = baseIdentityUrl + "/auth/social/{loginService}/login";


    @FormUrlEncoded
    @POST(tokenUrl)
    Call<TokenApiModel> getToken(@Field("client_id") String clientId, @Field("client_secret") String clientSecret, @Field("grant_type") String grantType);

    @POST(tokenUrl)
    Call<TokenApiModel> refreshToken(@Field("client_id") String clientId, @Field("client_secret") String clientSecret, @Field("grant_type") String grantType, @Field("refresh_token") String refreshToken);

    @POST(loginUrl)
    Call<NewMemberApiModel> login(@Header("Authorization") String authorization, @Body LoginModel login);

    @POST(signupUrl)
    Call<NewMemberApiModel> signup(@Header("Authorization") String authorization, @Body MemberSignupModel memberSignupModel);

    @POST(forgotUrl)
    Call<RestMessage> forgot(@Header("Authorization") String authorization, @Body ResetPasswordModel resetPasswordModel);

    @POST(changeUrl)
    Call<RestMessage> changePassword(@Header("Authorization") String authorization, @Body UpdatePasswordApiModel changePassword);

    @GET(socialLoginUrl)
    Call<SocialConnectUrlResponse> socialLogin(@Header("Authorization") String authorization, @Path("loginService") String connectService);

    @GET(authorizeUrl)
    Call<NewMemberApiModel> authorize(@Header("Authorization") String authorization, @Path("authorizeService") String connectService, @Query("code") String code, @Query("state") String state);

    @POST(connectUrl)
    Call<NewMemberApiModel> connect(@Header("Authorization") String authorization, @Path("connectService") String connectService, @Body SocialConnectApiModel socialConnectApiModel);

    @GET(logoutUrl)
    Call<NewMemberApiModel> logout(@Header("Authorization") String authorization);

    @GET(profileUrl)
    Call<MemberApiModel> getProfile(@Header("Authorization") String authorization, @Path("uuid") String uuid);

    @PUT(profileUrl)
    Call<MemberApiModel> updateProfile(@Header("Authorization") String authorization, @Path("uuid") String uuid, @Body MemberApiModel memberApiModel);

}
