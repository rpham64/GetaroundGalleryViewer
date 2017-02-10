package com.rpham64.android.getaroundgalleryviewer.utils;

import com.rpham64.android.getaroundgalleryviewer.ApplicationController;
import com.rpham64.android.getaroundgalleryviewer.network.RestClient;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Rudolf on 2/9/2017.
 */

public class BasePresenter<T> implements IPresenter<T> {

    private T mView;
    private CompositeSubscription mSubs = new CompositeSubscription();

    public BasePresenter() {

    }

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        mView = null;

        if (mSubs != null) {
            mSubs.unsubscribe();
        }
    }

    protected void addSubscription(Subscription sub) {
        mSubs.add(sub);
    }

    protected RestClient getRestClient() {
        return ApplicationController.getInstance().getRestClient();
    }

    public T getView() {
        return mView;
    }
}
