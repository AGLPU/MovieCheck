package com.ang.moviewatchnew.view;

import com.ang.moviewatchnew.model.entity.Image;
import com.ang.moviewatchnew.model.entity.Media;
import com.ang.moviewatchnew.model.entity.Video;

import java.util.List;

public interface ListMovieMediaView {
    void warnAnyMediaFounded();

    void showLoadingMedias();

    void showVideos(List<Video> videoList);

    void showMedias(List<Media> mediaList);

    void hideLoadingMedias();

    void warnFailedToLoadMedias();

    void showImages(List<Image> imageList);
}
