package com.ang.moviewatchnew.model.api;

import com.ang.moviewatchnew.model.entity.Movie;

public interface ReviewApi extends AsyncService {
    void listByMovies(Movie movie, int page);
}
