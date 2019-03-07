package com.example.android.photosapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

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

    static class FlickrPhotoFragment extends Fragment {

    }
}
