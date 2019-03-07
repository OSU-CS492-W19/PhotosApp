package com.example.android.photosapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.photosapp.data.FlickrExploreRepository;
import com.example.android.photosapp.data.FlickrPhoto;
import com.example.android.photosapp.data.Status;

import java.util.List;

public class FlickrExploreViewModel extends ViewModel {
    private LiveData<List<FlickrPhoto>> mExplorePhotos;
    private LiveData<Status> mLoadingStatus;
    private FlickrExploreRepository mRepository;

    public FlickrExploreViewModel() {
        mRepository = new FlickrExploreRepository();
        mExplorePhotos = mRepository.getExplorePhotos();
        mLoadingStatus = mRepository.getLoadingStatus();
    }

    public LiveData<List<FlickrPhoto>> getExplorePhotos() {
        return mExplorePhotos;
    }

    public LiveData<Status> getLoadingStatus() {
        return mLoadingStatus;
    }

    public void loadExplorePhotos() {
        mRepository.loadExplorePhotos();
    }
}
