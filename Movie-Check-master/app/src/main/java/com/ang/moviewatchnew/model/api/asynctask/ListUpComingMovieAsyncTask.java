package com.ang.moviewatchnew.model.api.asynctask;

import android.content.Context;

import com.ang.moviewatchnew.model.api.asynctask.exception.BadRequestException;
import com.ang.moviewatchnew.model.api.resource.MovieResource;
import com.ang.moviewatchnew.model.entity.Movie;

import java.util.List;

import retrofit.Response;

import static java.net.HttpURLConnection.HTTP_OK;

public class ListUpComingMovieAsyncTask extends GenericAsyncTask<Void, Void, List<Movie>> {

    private MovieResource movieResource;
    private Integer page;

    public ListUpComingMovieAsyncTask(Context context, MovieResource movieResource, Integer page) {
        super(context);
        this.movieResource = movieResource;
        this.page = page;
    }

    @Override
    protected AsyncTaskResult<List<Movie>> doInBackground(Void... params) {

        try {
            Response<List<Movie>> response = movieResource.listUpComing(getApiKey(), page).execute();
            switch (response.code()) {
                case HTTP_OK:
                    return new AsyncTaskResult<>(response.body());
                default:
                    return new AsyncTaskResult<>(new BadRequestException());
            }
        } catch (Exception e) {
            return new AsyncTaskResult<>(new BadRequestException());
        }
    }
}
