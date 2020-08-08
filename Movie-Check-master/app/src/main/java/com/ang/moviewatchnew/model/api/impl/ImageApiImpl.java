package com.ang.moviewatchnew.model.api.impl;

import android.content.Context;
import android.os.AsyncTask;

import com.ang.moviewatchnew.model.api.GenericApi;
import com.ang.moviewatchnew.model.api.ImageApi;
import com.ang.moviewatchnew.model.api.asynctask.ListImageByMovieAsyncTask;
import com.ang.moviewatchnew.model.api.asynctask.ListImageByPersonAsyncTask;
import com.ang.moviewatchnew.model.api.resource.ImageResource;
import com.ang.moviewatchnew.model.entity.Movie;
import com.ang.moviewatchnew.model.entity.Person;

public class ImageApiImpl extends GenericApi implements ImageApi {

    private ImageResource imageResource;
    private ListImageByMovieAsyncTask listImageByMovieAsyncTask;
    private ListImageByPersonAsyncTask listImageByPersonAsyncTask;

    public ImageApiImpl(Context context, ImageResource imageResource) {
        super(context);
        this.imageResource = imageResource;
    }

    @Override
    public void listAllByMovie(Movie movie) {
        verifyServiceResultListener();
        listImageByMovieAsyncTask = new ListImageByMovieAsyncTask(getContext(), imageResource, movie);
        listImageByMovieAsyncTask.setApiResultListener(getApiResultListener());
        listImageByMovieAsyncTask.execute();
    }

    @Override
    public void listAllByPerson(Person person) {
        verifyServiceResultListener();
        listImageByPersonAsyncTask = new ListImageByPersonAsyncTask(getContext(), imageResource, person);
        listImageByPersonAsyncTask.setApiResultListener(getApiResultListener());
        listImageByPersonAsyncTask.execute();
    }

    @Override
    public void cancelAllServices() {
        if(listImageByMovieAsyncTask != null && listImageByMovieAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            listImageByMovieAsyncTask.cancel(true);
        }
        if(listImageByPersonAsyncTask != null && listImageByPersonAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            listImageByPersonAsyncTask.cancel(true);
        }
    }
}
