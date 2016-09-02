package io.nbos.capi.api.v0.models;

import java.util.List;


public class ValidationErrorResponse {

    public List<FieldErrorApiModel> getErrors() {
        return errors;
    }

    List<FieldErrorApiModel> errors;


}
