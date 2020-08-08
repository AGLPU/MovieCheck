package com.ang.moviewatchnew.view;

import com.ang.moviewatchnew.model.entity.Movie;
import com.ang.moviewatchnew.model.entity.Person;

import java.util.List;

public interface SearchView {
    void showLoadingMovies();

    void warnAnyMovieFounded();

    void showMovies(List<Movie> movieList);

    void hideLoadingMovies();

    void warnFailedToLoadMovies();

    void showLoadingPerson();

    void warnAnyPersonFounded();

    void showPerson(List<Person> personList);

    void hideLoadingPerson();

    void warnFailedToLoadPerson();
}
