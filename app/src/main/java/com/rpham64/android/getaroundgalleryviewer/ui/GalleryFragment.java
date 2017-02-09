package com.rpham64.android.getaroundgalleryviewer.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.rpham64.android.getaroundgalleryviewer.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Rudolf on 2/7/2017.
 */

public class GalleryFragment extends Fragment {

    public static final String TAG = GalleryFragment.class.getName();

    @BindView(R.id.recycler_view_gallery_fragment) UltimateRecyclerView recyclerViewImages;

    private Unbinder mUnbinder;

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
