package com.ang.moviewatchnew.model.api;

import com.ang.moviewatchnew.model.entity.Movie;

public interface VideoApi extends AsyncService {
    void listAllByMovie(Movie movie);
}
