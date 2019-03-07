package com.example.android.photosapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.photosapp.data.FlickrPhoto;

import java.util.List;

public class FlickrPhotosAdapter extends RecyclerView.Adapter<FlickrPhotosAdapter.FlickrPhotoViewHolder> {
    private List<FlickrPhoto> mPhotos;
    private OnPhotoClickedListener mOnPhotoClickedListener;

    public interface OnPhotoClickedListener {
        void onPhotoClicked(FlickrPhoto photo);
    }

    public FlickrPhotosAdapter(OnPhotoClickedListener onPhotoClickedListener) {
        mOnPhotoClickedListener = onPhotoClickedListener;
    }

    public void updatePhotos(List<FlickrPhoto> photos) {
        mPhotos = photos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mPhotos != null) {
            return mPhotos.size();
        } else {
            return 0;
        }
    }

    @NonNull
    @Override
    public FlickrPhotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.flickr_photo_item, viewGroup, false);
        return new FlickrPhotoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FlickrPhotoViewHolder flickrPhotoViewHolder, int i) {
        flickrPhotoViewHolder.bind(mPhotos.get(i));
    }

    class FlickrPhotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mPhotoIV;
        private TextView mPhotoTitleTV;
        private TextView mPhotoOwnerTV;

        FlickrPhotoViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mPhotoIV = itemView.findViewById(R.id.iv_photo);
            mPhotoTitleTV = itemView.findViewById(R.id.tv_photo_title);
            mPhotoOwnerTV = itemView.findViewById(R.id.tv_photo_owner);
        }

        public void bind(FlickrPhoto photo) {
            mPhotoTitleTV.setText(photo.title);

            String byOwner = mPhotoOwnerTV.getContext().getString(R.string.by_owner, photo.ownername);
            mPhotoOwnerTV.setText(byOwner);
        }

        @Override
        public void onClick(View view) {
            mOnPhotoClickedListener.onPhotoClicked(mPhotos.get(getAdapterPosition()));
        }
    }
}
