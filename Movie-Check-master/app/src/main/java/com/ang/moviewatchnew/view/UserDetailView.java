package com.ang.moviewatchnew.view;

import com.ang.moviewatchnew.model.entity.Genre;
import com.ang.moviewatchnew.model.entity.MovieInterest;
import com.ang.moviewatchnew.model.entity.User;

import java.util.List;

public interface UserDetailView {

    void showFullname(String fulname);

    void showEmail(String email);

    void showPhoto(String posterUrl);

    void showUser(User user);

    void showUpcomingInterests(List<MovieInterest> movieInterests);

    void showLoadingGenres();

    void warnAnyGenreFounded();

    void showGenres(List<Genre> genreList);

    void hideLoadingGenres();

    void warnFailedOnLoadGenres();

    void warnAnyInterestFound();
}
