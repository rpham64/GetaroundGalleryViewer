package com.rpham64.android.getaroundgalleryviewer.network.response;

import com.google.gson.annotations.SerializedName;
import com.rpham64.android.getaroundgalleryviewer.models.Filter;
import com.rpham64.android.getaroundgalleryviewer.models.Photo;

import java.util.List;

/**
 * Created by Rudolf on 2/8/2017.
 */

public class PopularPhotosResponse {

    public String feature;

    public Filter filters;

    @SerializedName("current_page")
    public int currentPage;

    @SerializedName("total_pages")
    public int numPages;

    @SerializedName("total_items")
    public int numItems;

    public List<Photo> photos;
}
