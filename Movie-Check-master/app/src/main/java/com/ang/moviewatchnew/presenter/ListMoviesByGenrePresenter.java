package com.ang.moviewatchnew.presenter;

import com.ang.moviewatchnew.model.api.MovieApi;
import com.ang.moviewatchnew.model.api.asynctask.ApiResultListener;
import com.ang.moviewatchnew.model.entity.Genre;
import com.ang.moviewatchnew.model.entity.Movie;
import com.ang.moviewatchnew.view.ListMoviesByGenreView;

import java.util.List;

public class ListMoviesByGenrePresenter {

    private ListMoviesByGenreView view;
    private MovieApi movieApi;

    public ListMoviesByGenrePresenter(ListMoviesByGenreView view, MovieApi movieApi) {
        this.view = view;
        this.movieApi = movieApi;
    }

    public void loadMovies(Genre genre, Integer page) {
        view.showLoadingMovies();
        movieApi.setApiResultListener(new ApiResultListener() {
            @Override
            public void onResult(Object object) {
                List<Movie> movieList = (List<Movie>) object;
                if (movieList == null || movieList.size() == 0) {
                    view.warnAnyMovieFounded();
                } else {
                    view.showMovies(movieList);
                }
                view.hideLoadingMovies();
            }

            @Override
            public void onException(Exception exception) {
                view.warnFailedToLoadMovies();
                view.hideLoadingMovies();
            }
        });

        movieApi.listByGenre(genre, page);
    }


    public void stop() {
        movieApi.cancelAllServices();
    }
}
