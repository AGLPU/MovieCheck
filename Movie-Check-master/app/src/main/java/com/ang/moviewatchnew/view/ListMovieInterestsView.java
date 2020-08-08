package com.ang.moviewatchnew.view;

import com.ang.moviewatchnew.model.entity.Movie;
import com.ang.moviewatchnew.model.entity.MovieInterest;

import java.util.List;

public interface ListMovieInterestsView {
    void showMovieInterests(List<MovieInterest> movieInterestList);

    void warnAnyInterestFounded();

    void warnMovieRemoved(Movie movie);
}
