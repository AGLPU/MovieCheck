package com.ang.moviewatchnew.view;

import com.ang.moviewatchnew.model.entity.Movie;
import com.ang.moviewatchnew.model.entity.MovieWatched;

import java.util.List;

public interface ListMovieWatchedView {
    void showWatchedMovies(List<MovieWatched> movieWatchedList);

    void warnAnyWatchedMovieFounded();

    void warnMovieRemoved(Movie movie);
}
