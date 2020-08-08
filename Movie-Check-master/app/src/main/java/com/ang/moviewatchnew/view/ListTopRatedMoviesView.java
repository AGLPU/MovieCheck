package com.ang.moviewatchnew.view;

import com.ang.moviewatchnew.model.entity.Movie;

import java.util.List;

public interface ListTopRatedMoviesView {
    void showLoadingMovies();

    void warnAnyMovieFounded();

    void showMovies(List<Movie> movieList);

    void hideLoadingMovies();

    void warnFailedToLoadMovies();
}
