package com.ang.moviewatchnew.model.api;

import com.ang.moviewatchnew.model.entity.Movie;
import com.ang.moviewatchnew.model.entity.Person;

public interface CrewApi extends AsyncService{
    void listAllByMovie(Movie movie);

    void listMoviesByCrew(Person person);
}
