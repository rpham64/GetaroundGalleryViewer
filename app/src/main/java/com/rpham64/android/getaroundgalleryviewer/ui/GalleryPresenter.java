package com.rpham64.android.getaroundgalleryviewer.ui;

import com.orhanobut.logger.Logger;
import com.rpham64.android.getaroundgalleryviewer.models.Photo;
import com.rpham64.android.getaroundgalleryviewer.utils.BasePresenter;
import com.rpham64.android.getaroundgalleryviewer.utils.PagedResult;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Rudolf on 2/7/2017.
 */

public class GalleryPresenter extends BasePresenter<GalleryPresenter.View> {

    private static final String TAG = GalleryPresenter.class.getName();

    public GalleryPresenter() {
        Logger.d("GalleryPresenter created");
    }

    @Override
    public void onResume() {
        super.onResume();
        attachPageObservable();
    }

    public void attachPageObservable() {

        Logger.d("PagedObservable attached");

        addSubscription(
                getView().getPagedObservable()
                        .flatMap(pageNumber -> getRestClient().getPopularPhotosRx(pageNumber))
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(this::handleError)
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
        throwable.printStackTrace();
    }

    public interface View {
        void showPhotos(List<Photo> photos, PagedResult pagedResult);
        Observable<Integer> getPagedObservable();
    }
}
