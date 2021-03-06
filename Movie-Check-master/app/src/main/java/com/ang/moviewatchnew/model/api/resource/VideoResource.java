package com.ang.moviewatchnew.model.api.resource;


import com.ang.moviewatchnew.model.entity.Video;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface VideoResource {

    @GET("movie/{id}/videos")
    Call<List<Video>> listAllByMovie(@Path("id") Long movieId, @Query("api_key") String apiKey);

}
