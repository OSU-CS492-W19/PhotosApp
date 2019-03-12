package com.example.android.photosapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.android.photosapp.data.FlickrPhoto;

import java.util.List;

public class FlickrPhotoPagerAdapter extends FragmentStatePagerAdapter {

    private static final String ARG_PHOTO = "FlickrPhoto";

    private List<FlickrPhoto> mPhotos;

    public FlickrPhotoPagerAdapter(FragmentManager fm) {
        super(fm);
        mPhotos = null;
    }

    public void updatePhotos(List<FlickrPhoto> photos) {
        mPhotos = photos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mPhotos != null) {
            return mPhotos.size();
        } else {
            return 0;
        }
    }

    @Override
    public Fragment getItem(int i) {
        FlickrPhotoFragment fragment = new FlickrPhotoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PHOTO, mPhotos.get(i));
        fragment.setArguments(args);
        return fragment;
    }

    public static class FlickrPhotoFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.photo_pager_item, container, false);

            Bundle args = getArguments();
            FlickrPhoto photo = (FlickrPhoto)args.getSerializable(ARG_PHOTO);

            ImageView imageView = rootView.findViewById(R.id.iv_photo);
            Glide.with(imageView)
                    .load(photo.url_l)
                    .into(imageView);

            return rootView;
        }
    }
}
