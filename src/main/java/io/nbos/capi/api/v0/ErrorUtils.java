package io.nbos.capi.api.v0;

import io.nbos.capi.api.v0.models.ValidationErrorResponse;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by vivekkiran on 7/25/16.
 */

public class ErrorUtils {
    public static <Any> Any parseError(NetworkApi networkApi, Class errorClass, Response<?> response) {
        //   NetworkApi nw = new NetworkApi();
        Converter<ResponseBody, ValidationErrorResponse> converter =
                networkApi.getRetrofitClient().responseBodyConverter(errorClass, new Annotation[0]);

        ValidationErrorResponse error = null;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (Any) error;
    }
}
