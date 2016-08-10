package com.nbos.capi.api.v0.models;


public class FieldErrorApiModel extends RestMessage {
    public String getObjectName() {
        return objectName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    String objectName;
    String propertyName;
}
