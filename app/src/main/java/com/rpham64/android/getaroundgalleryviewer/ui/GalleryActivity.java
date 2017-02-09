package com.rpham64.android.getaroundgalleryviewer.ui;

import android.support.v4.app.Fragment;

import com.rpham64.android.getaroundgalleryviewer.utils.SingleFragmentActivity;

/**
 * Created by Rudolf on 2/7/2017.
 */

public class GalleryActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return GalleryFragment.newInstance();
    }
}
