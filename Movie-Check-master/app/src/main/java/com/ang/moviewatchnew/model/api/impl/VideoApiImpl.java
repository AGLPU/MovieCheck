package com.ang.moviewatchnew.model.api.impl;

import android.content.Context;
import android.os.AsyncTask;

import com.ang.moviewatchnew.model.api.GenericApi;
import com.ang.moviewatchnew.model.api.VideoApi;
import com.ang.moviewatchnew.model.api.asynctask.ListVideoByMovieAsyncTask;
import com.ang.moviewatchnew.model.api.resource.VideoResource;
import com.ang.moviewatchnew.model.entity.Movie;

public class VideoApiImpl extends GenericApi implements VideoApi {

    private VideoResource videoResource;
    private ListVideoByMovieAsyncTask listVideoByMovieAsyncTask;

    public VideoApiImpl(Context context, VideoResource videoResource) {
        super(context);
        this.videoResource = videoResource;
    }

    @Override
    public void listAllByMovie(Movie movie) {
        verifyServiceResultListener();
        listVideoByMovieAsyncTask = new ListVideoByMovieAsyncTask(getContext(), videoResource, movie);
        listVideoByMovieAsyncTask.setApiResultListener(getApiResultListener());
        listVideoByMovieAsyncTask.execute();
    }

    @Override
    public void cancelAllServices() {
        if (listVideoByMovieAsyncTask != null && listVideoByMovieAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            listVideoByMovieAsyncTask.cancel(true);
        }
    }
}
