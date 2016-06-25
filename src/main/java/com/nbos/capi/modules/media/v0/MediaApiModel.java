package com.nbos.capi.modules.media.v0;

import java.util.List;

public class MediaApiModel {
    String uuid;

    public String getExtension() {
        return extension;
    }

    public String getUuid() {
        return uuid;
    }

    public String getSupportedsizes() {
        return supportedsizes;
    }

    public List<MediaFileDetails> getMediaFileDetailsList() {
        return mediaFileDetailsList;
    }

    String extension;
    String supportedsizes;
    List<MediaFileDetails> mediaFileDetailsList;
}
