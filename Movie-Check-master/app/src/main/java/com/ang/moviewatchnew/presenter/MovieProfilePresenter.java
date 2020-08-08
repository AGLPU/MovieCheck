package com.ang.moviewatchnew.presenter;

import com.ang.moviewatchnew.model.entity.Movie;
import com.ang.moviewatchnew.view.MovieProfileView;

public class MovieProfilePresenter {

    private MovieProfileView view;

    public MovieProfilePresenter(MovieProfileView view) {
        this.view = view;
    }

    public void init(Movie movie) {
        view.showMovieName(movie.getTitle());
    }
}
