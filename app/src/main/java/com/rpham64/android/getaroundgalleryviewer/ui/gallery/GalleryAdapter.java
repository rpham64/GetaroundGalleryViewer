package com.rpham64.android.getaroundgalleryviewer.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.orhanobut.logger.Logger;
import com.rpham64.android.getaroundgalleryviewer.R;
import com.rpham64.android.getaroundgalleryviewer.models.Photo;
import com.rpham64.android.getaroundgalleryviewer.ui.photo.PhotoActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rudolf on 2/9/2017.
 */

public class GalleryAdapter extends UltimateViewAdapter<GalleryAdapter.GalleryViewHolder> {

    public interface Extras {
        String imageUrl = "GalleryAdapter.imageUrl";
    }

    private Context mContext;
    private List<Photo> mPhotos;

    public GalleryAdapter(Context context, List<Photo> photos) {
        mContext = context;
        mPhotos = photos;
    }

    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_gallery_item, parent, false);
        ButterKnife.bind(this, view);

        return new GalleryViewHolder(mContext, view);
    }

    @Override
    public void onBindViewHolder(GalleryViewHolder holder, int position) {
        Photo photo = mPhotos.get(position);
        holder.bindPhoto(photo);
    }

    @Override
    public int getAdapterItemCount() {
        if (mPhotos != null) {
            return mPhotos.size();
        }

        Logger.d("Photos list in GalleryAdapter is null.");
        return 0;
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public GalleryViewHolder newFooterHolder(View view) {
        return null;
    }

    @Override
    public GalleryViewHolder newHeaderHolder(View view) {
        return null;
    }

    public void setPhotos(List<Photo> photos) {
        mPhotos = photos;
        notifyDataSetChanged();
    }

    public void addPhotos(List<Photo> photos) {
        mPhotos.addAll(photos);
        notifyDataSetChanged();
    }

    class GalleryViewHolder extends UltimateRecyclerviewViewHolder {

        @BindView(R.id.imageview_gallery_item) ImageView imgPhoto;

        private Context mContext;
        private Photo mPhoto;

        public GalleryViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = context;
        }

        public void bindPhoto(Photo photo) {

            mPhoto = photo;

            Picasso.with(mContext)
                    .load(photo.imageUrl)
                    .placeholder(R.color.grey)
                    .into(imgPhoto);
        }

        @OnClick(R.id.imageview_gallery_item)
        public void onPhotoClicked() {
            Intent intent = new Intent(mContext, PhotoActivity.class);
            intent.putExtra(Extras.imageUrl, mPhoto.imageUrl);
            mContext.startActivity(intent);
        }
    }
}
