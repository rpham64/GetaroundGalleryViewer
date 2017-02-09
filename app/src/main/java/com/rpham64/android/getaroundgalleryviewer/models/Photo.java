package com.rpham64.android.getaroundgalleryviewer.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Rudolf on 2/8/2017.
 */

public class Photo {

    public int id;

    @SerializedName("hi_res_uploaded")
    public int isHighRes;

    public int width;

    public int height;

    @SerializedName("image_url")
    public String imageUrl;

    public List<Image> images;

    public String url;

    @SerializedName("image_format")
    public String format;
}
