package in.wavelabs.idn.ConnectionAPI;

import com.nbos.capi.api.v0.RestMessage;
import com.nbos.capi.modules.media.v0.MediaApiModel;

import java.util.HashMap;
import java.util.Map;

import in.wavelabs.idn.ConnectionAPI.service.StarterClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vivek Kiran on 05/02/16.
 */
public class MediaApi {

    public static void getProfileImage(String accessToken,String uuid, final NBOSCallback<MediaApiModel> nbosCallback){
        Call<MediaApiModel> call = StarterClient.getStarterAPI().media(accessToken, uuid,"profile");
        call.enqueue(new Callback<MediaApiModel>() {


            @Override
            public void onResponse(Call<MediaApiModel> call, Response<MediaApiModel> response) {
                nbosCallback.onResponse(response);
            }

            @Override
            public void onFailure(Call<MediaApiModel> call, Throwable t) {
                nbosCallback.onFailure(t);

            }
        });
    }
    public static void updateProfileImage(String accessToken,String fileName, String userId,final NBOSCallback<RestMessage> nbosCallback) {

        String mediafor = "profile";
//        Uri imageUri = Uri.fromFile(new File(fileName));
//
        Map<String, RequestBody> map = new HashMap<>();
//        if (imageUri != null) {
//            File file = new File(imageUri.getPath());
//            String fileExtension = imageUri.getPath().substring(imageUri.getPath().lastIndexOf(".") + 1);
//
//            RequestBody fileBody = RequestBody.create(MediaType.parse("image/" + fileExtension), file);
//            RequestBody ids = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(userId));
//            RequestBody mediaFor = RequestBody.create(MediaType.parse("text/plain"), mediafor);
//            map.put("id", ids);
//            map.put("mediafor", mediaFor);
//            map.put("file\"; filename=\"" + file + "", fileBody);
//            System.out.println(map);
//        }


        //     String authorization = "Bearer 7c37c8696eb7257921f70a37bc57434a1c0c6e52";
        //Uri.parse("/storage/emulated/0/WhatsApp/Media/WhatsApp Images/IMG-20151117-WA0006.jpg");

        //   File file = new File(imageUri.getPath());
        //  RequestBody fbody = RequestBody.create(MediaType.parse("image/*"), file);
        //  String filepart = "file\"; filename=\"IMG-20151117-WA0006.jpg\"";

        Call<RestMessage> call = StarterClient.getStarterAPI().uploadMedia(accessToken, map);
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
