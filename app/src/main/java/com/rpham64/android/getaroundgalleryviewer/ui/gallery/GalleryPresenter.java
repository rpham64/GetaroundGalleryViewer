package com.rpham64.android.getaroundgalleryviewer.ui.gallery;

import com.orhanobut.logger.Logger;
import com.rpham64.android.getaroundgalleryviewer.models.Photo;
import com.rpham64.android.getaroundgalleryviewer.utils.BasePresenter;
import com.rpham64.android.getaroundgalleryviewer.utils.PagedResult;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Rudolf on 2/7/2017.
 */

public class GalleryPresenter extends BasePresenter<GalleryPresenter.View> {

    private static final String TAG = GalleryPresenter.class.getName();

    public GalleryPresenter() {

    }

    public void fetch(int page) {

        addSubscription(
                Observable.just(page)
                    .flatMap(pageNumber -> getRestClient().getPopularPhotosRx(pageNumber))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError(this::handleError)
                    .retry()
                    .subscribe(
                            response -> {
                                PagedResult pagedResult = new PagedResult(
                                        response.currentPage,
                                        response.numPages
                                );

                                getView().showPhotos(response.photos, pagedResult);
                            },
                            this::handleError
                    )
        );

    }

    private void handleError(Throwable throwable) {
        Logger.d(throwable.toString());
        throwable.printStackTrace();
    }

    public interface View {
        void showPhotos(List<Photo> photos, PagedResult pagedResult);
    }
}
