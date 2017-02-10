package com.rpham64.android.getaroundgalleryviewer.network;

import com.rpham64.android.getaroundgalleryviewer.network.response.PopularPhotosResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Rudolf on 2/8/2017.
 */

public interface RestClient {

    @GET("v1/photos?feature=popular")
    PopularPhotosResponse getPopularPhotos(@Query("page") int page);

    @GET("v1/photos?feature=popular")
    Observable<PopularPhotosResponse> getPopularPhotosRx(@Query("page") int page);

}
