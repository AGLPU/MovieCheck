package com.ang.moviewatchnew.model.api.asynctask;

import android.content.Context;

import com.ang.moviewatchnew.model.api.asynctask.exception.BadRequestException;
import com.ang.moviewatchnew.model.api.resource.CastResource;
import com.ang.moviewatchnew.model.entity.Cast;
import com.ang.moviewatchnew.model.entity.Movie;

import java.util.List;

import retrofit.Response;

import static java.net.HttpURLConnection.HTTP_OK;

public class ListCastByMovieAsyncTask extends GenericAsyncTask<Void, Void, List<Cast>> {

    private CastResource castResource;
    private Movie movie;

    public ListCastByMovieAsyncTask(Context context, CastResource castResource, Movie movie) {
        super(context);
        this.castResource = castResource;
        this.movie = movie;
    }

    @Override
    protected AsyncTaskResult<List<Cast>> doInBackground(Void... params) {

        try {
            Response<List<Cast>> response = castResource.listAllByMovie(movie.getId(), getApiKey()).execute();
            switch (response.code()) {
                case HTTP_OK:
                    return new AsyncTaskResult<>(response.body());
                default:
                    return new AsyncTaskResult<>(new BadRequestException());
            }
        } catch (Exception ex) {
            return new AsyncTaskResult<>(new BadRequestException());
        }
    }
}
