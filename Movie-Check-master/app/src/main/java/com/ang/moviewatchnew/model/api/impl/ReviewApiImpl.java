package com.ang.moviewatchnew.model.api.impl;

import android.content.Context;
import android.os.AsyncTask;

import com.ang.moviewatchnew.model.api.GenericApi;
import com.ang.moviewatchnew.model.api.ReviewApi;
import com.ang.moviewatchnew.model.api.asynctask.ListReviewByMovieAsyncTask;
import com.ang.moviewatchnew.model.api.resource.ReviewResource;
import com.ang.moviewatchnew.model.entity.Movie;

public class ReviewApiImpl extends GenericApi implements ReviewApi {

    private ReviewResource reviewResource;
    private ListReviewByMovieAsyncTask listReviewByMovieAsyncTask;

    public ReviewApiImpl(Context context, ReviewResource reviewResource) {
        super(context);
        this.reviewResource = reviewResource;
    }

    @Override
    public void listByMovies(Movie movie, int page) {
        verifyServiceResultListener();
        listReviewByMovieAsyncTask = new ListReviewByMovieAsyncTask(getContext(), reviewResource, movie, page);
        listReviewByMovieAsyncTask.setApiResultListener(getApiResultListener());
        listReviewByMovieAsyncTask.execute();
    }

    @Override
    public void cancelAllServices() {
        if(listReviewByMovieAsyncTask != null && listReviewByMovieAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            listReviewByMovieAsyncTask.cancel(true);
        }
    }
}
