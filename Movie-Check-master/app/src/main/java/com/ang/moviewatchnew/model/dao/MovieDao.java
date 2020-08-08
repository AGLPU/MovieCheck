package com.ang.moviewatchnew.model.dao;

import com.ang.moviewatchnew.model.entity.Movie;

import java.util.List;

public interface MovieDao {
    void save(Movie movie);

    void update(Movie movie);

    void insert(Movie movie);

    Movie findById(Long id);

    List<Movie> listAll();

    List<Movie> listAllUpcoming();
}
