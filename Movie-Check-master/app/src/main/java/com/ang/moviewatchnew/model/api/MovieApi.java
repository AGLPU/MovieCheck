package com.ang.moviewatchnew.model.api;

import com.ang.moviewatchnew.model.entity.Genre;

public interface MovieApi extends AsyncService {
    void listUpcomingMovies();

    void discoverMovies(int page);

    void listUpcomingMovies(int page);

    void listPopularMovies();

    void listPopularMovies(int page);

    void listTopRatedMovies();

    void listTopRatedMovies(int page);

    void listNowPlayingMovies();

    void listNowPlayingMovies(int page);

    void listByGenre(Genre genre, int page);

    void listByName(String name, int page);

    void listByName(String name);
}
