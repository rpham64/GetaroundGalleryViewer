package com.rpham64.android.getaroundgalleryviewer.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.rpham64.android.getaroundgalleryviewer.R;
import com.rpham64.android.getaroundgalleryviewer.models.Photo;
import com.rpham64.android.getaroundgalleryviewer.utils.GalleryAdapter;
import com.rpham64.android.getaroundgalleryviewer.utils.PagedResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Rudolf on 2/7/2017.
 */

public class GalleryFragment extends Fragment implements GalleryPresenter.View{

    public static final String TAG = GalleryFragment.class.getName();

    @BindView(R.id.recycler_view_gallery_fragment) UltimateRecyclerView recyclerView;

    private Unbinder mUnbinder;
    private GalleryAdapter mAdapter;
    private GalleryPresenter mPresenter;

    private int currentPage;
    private int pages;

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new GalleryPresenter();
        currentPage = 1;

        mPresenter.fetch(currentPage);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mPresenter.attachView(this);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.reenableLoadmore();
        recyclerView.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int itemsCount, int maxLastVisiblePosition) {

                if (currentPage < pages) {

                    // Load next page of photos
                    mPresenter.fetch(currentPage + 1);

                } else {
                    Toast.makeText(getContext(), "No more pictures to show.", Toast.LENGTH_SHORT).show();
                    recyclerView.disableLoadmore();
                }

            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onDestroyView() {
        mPresenter.detachView();
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void showPhotos(List<Photo> photos, PagedResult pagedResult) {

        if (isAdded() && mAdapter == null) {
            mAdapter = new GalleryAdapter(getContext(), photos);
            recyclerView.setAdapter(mAdapter);
        }

        currentPage = pagedResult.page;
        pages = pagedResult.pages;

        Toast.makeText(getContext(), "Loading page: " + currentPage, Toast.LENGTH_SHORT).show();

        if (currentPage == 1) {
            mAdapter.setPhotos(photos);
        } else {
            mAdapter.addPhotos(photos);
        }
    }
}
