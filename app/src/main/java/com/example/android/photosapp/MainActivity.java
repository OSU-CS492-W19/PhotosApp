package com.example.android.photosapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.photosapp.data.FlickrPhoto;
import com.example.android.photosapp.data.Status;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FlickrPhotosAdapter.OnPhotoClickedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int NUM_PHOTO_COLUMNS = 2;

    private RecyclerView mPhotosRV;
    private ProgressBar mLoadingIndicatorPB;
    private TextView mLoadingErrorMessageTV;

    private FlickrPhotosAdapter mAdapter;
    private FlickrExploreViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoadingIndicatorPB = findViewById(R.id.pb_loading_indicator);
        mLoadingErrorMessageTV = findViewById(R.id.tv_loading_error_message);
        mPhotosRV = findViewById(R.id.rv_photos);

        mAdapter = new FlickrPhotosAdapter(this);
        mPhotosRV.setAdapter(mAdapter);

        mPhotosRV.setHasFixedSize(true);
//        mPhotosRV.setLayoutManager(new LinearLayoutManager(this));
        mPhotosRV.setLayoutManager(new StaggeredGridLayoutManager(NUM_PHOTO_COLUMNS,
                StaggeredGridLayoutManager.VERTICAL));

        mViewModel = ViewModelProviders.of(this).get(FlickrExploreViewModel.class);

        mViewModel.getLoadingStatus().observe(this, new Observer<Status>() {
            @Override
            public void onChanged(@Nullable Status status) {
                if (status == Status.LOADING) {
                    mLoadingIndicatorPB.setVisibility(View.VISIBLE);
                } else if (status == Status.SUCCESS) {
                    mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
                    mPhotosRV.setVisibility(View.VISIBLE);
                    mLoadingErrorMessageTV.setVisibility(View.INVISIBLE);
                } else {
                    mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
                    mPhotosRV.setVisibility(View.VISIBLE);
                    mLoadingErrorMessageTV.setVisibility(View.INVISIBLE);
                }
            }
        });

        mViewModel.getExplorePhotos().observe(this, new Observer<List<FlickrPhoto>>() {
            @Override
            public void onChanged(@Nullable List<FlickrPhoto> photos) {
                mAdapter.updatePhotos(photos);
            }
        });

        mViewModel.loadExplorePhotos();
    }

    @Override
    public void onPhotoClicked(int photoIdx) {
//        Log.d(TAG, "photo clicked: " + photoIdx);
        Intent intent = new Intent(this, PhotoViewActivity.class);
        intent.putExtra(PhotoViewActivity.EXTRA_PHOTO_IDX, photoIdx);
        startActivity(intent);
    }
}
