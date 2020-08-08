package com.ang.moviewatchnew.view;

import com.ang.moviewatchnew.model.entity.Movie;

import java.util.List;

public interface DiscoveryView {
    void showMovie(Movie movie, int index);

    void movieLoaded(List<Movie> movieList, int page);

    void warnWasNotPossibleToLoadMoreMovies();

    void warnFailedToLoadMoreMovies();

    void showLoading();

    void hideLoading();

    void checkInterest();

    void uncheckInterest();

    void removedFromInteresting();

    void warmAddedAsInteresting();
}
