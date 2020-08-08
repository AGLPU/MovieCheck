package com.ang.moviewatchnew.model.dao;

import com.ang.moviewatchnew.model.entity.Movie;
import com.ang.moviewatchnew.model.entity.MovieNotInterest;
import com.ang.moviewatchnew.model.entity.User;

import java.util.List;

public interface MovieNotInterestDao {
    List<MovieNotInterest> listAll(User user);

    List<MovieNotInterest> listAllUpcoming(User user);

    MovieNotInterest findByMovie(Movie movie, User user);

    void remove(MovieNotInterest movieNotInterest);

    void remove(Movie movie, User user);

    void insert(MovieNotInterest movieNotInterest);
}
