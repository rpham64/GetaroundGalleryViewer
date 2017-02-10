package com.rpham64.android.getaroundgalleryviewer.ui.photo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.rpham64.android.getaroundgalleryviewer.R;
import com.rpham64.android.getaroundgalleryviewer.ui.gallery.GalleryAdapter;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Rudolf on 2/10/2017.
 */

public class PhotoActivity extends AppCompatActivity {

    @BindView(R.id.view_photo) ImageView viewPhoto;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    private String imageUrl;

    public PhotoActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            imageUrl = getIntent().getStringExtra(GalleryAdapter.Extras.imageUrl);
        }

        final PhotoViewAttacher attacher = new PhotoViewAttacher(viewPhoto);

        Picasso.with(this)
                .load(imageUrl)
                .into(viewPhoto, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                        attacher.update();
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
