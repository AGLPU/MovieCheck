package com.ang.moviewatchnew.model.api.impl;

import android.content.Context;
import android.os.AsyncTask;

import com.ang.moviewatchnew.model.api.CastApi;
import com.ang.moviewatchnew.model.api.GenericApi;
import com.ang.moviewatchnew.model.api.asynctask.ListCastByMovieAsyncTask;
import com.ang.moviewatchnew.model.api.asynctask.ListMovieByCastAsyncTask;
import com.ang.moviewatchnew.model.api.resource.CastResource;
import com.ang.moviewatchnew.model.entity.Movie;
import com.ang.moviewatchnew.model.entity.Person;

public class CastApiImpl extends GenericApi implements CastApi {

    private CastResource castResource;
    private ListCastByMovieAsyncTask listCastByMovieAsyncTask;
    private ListMovieByCastAsyncTask listMovieByCastAsyncTask;

    public CastApiImpl(Context context, CastResource castResource) {
        super(context);
        this.castResource = castResource;
    }

    @Override
    public void listAllByMovie(Movie movie) {
        verifyServiceResultListener();
        listCastByMovieAsyncTask = new ListCastByMovieAsyncTask(getContext(), castResource, movie);
        listCastByMovieAsyncTask.setApiResultListener(getApiResultListener());
        listCastByMovieAsyncTask.execute();
    }

    @Override
    public void listMoviesByCast(Person person) {
        verifyServiceResultListener();
        listMovieByCastAsyncTask = new ListMovieByCastAsyncTask(getContext(), castResource, person);
        listMovieByCastAsyncTask.setApiResultListener(getApiResultListener());
        listMovieByCastAsyncTask.execute();
    }

    @Override
    public void cancelAllServices() {
        if (listCastByMovieAsyncTask != null && listCastByMovieAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            listCastByMovieAsyncTask.cancel(true);
        }
        if (listMovieByCastAsyncTask != null && listMovieByCastAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            listMovieByCastAsyncTask.cancel(true);
        }
    }
}
