package com.rpham64.android.getaroundgalleryviewer.utils;

/**
 * Created by Rudolf on 2/9/2017.
 */

public interface IPresenter<T> {
    void attachView(T mvpView);

    void detachView();

    void onResume();
    void onPause();
    void onDestroy();
}
