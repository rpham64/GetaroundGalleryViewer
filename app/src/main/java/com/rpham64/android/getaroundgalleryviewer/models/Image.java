package com.rpham64.android.getaroundgalleryviewer.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rudolf on 2/8/2017.
 */

public class Image {

    public int size;

    public String url;

    @SerializedName("https_url")
    public String httpsUrl;

    public String format;
}
