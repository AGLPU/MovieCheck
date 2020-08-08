package com.ang.moviewatchnew.view;

import com.ang.moviewatchnew.model.entity.Image;
import com.ang.moviewatchnew.model.entity.Media;

import java.util.List;

public interface ListPersonMediaView {
    void warnAnyMediaFounded();

    void showMedias(List<Media> mediaList);

    void showLoadingMedias();

    void hideLoadingMedias();

    void warnFailedToLoadMedias();

    void showImages(List<Image> imageList);
}
