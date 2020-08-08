package com.ang.moviewatchnew.presenter;

import com.ang.moviewatchnew.model.api.ImageApi;
import com.ang.moviewatchnew.model.api.asynctask.ApiResultListener;
import com.ang.moviewatchnew.model.entity.Image;
import com.ang.moviewatchnew.model.entity.Person;
import com.ang.moviewatchnew.view.ListPersonMediaView;

import java.util.List;

public class ListPersonMediaPresenter {

    private ListPersonMediaView view;
    private ImageApi imageApi;

    public ListPersonMediaPresenter(ListPersonMediaView view, ImageApi imageApi) {
        this.view = view;
        this.imageApi = imageApi;
    }

    public void loadImages(Person person) {
        view.showLoadingMedias();
        imageApi.setApiResultListener(new ApiResultListener() {
            @Override
            public void onResult(Object object) {
                List<Image> imageList = (List<Image>) object;
                if (imageList == null || imageList.size() == 0) {
                    view.warnAnyMediaFounded();
                } else {
                    view.showImages(imageList);
                }
                view.hideLoadingMedias();
            }

            @Override
            public void onException(Exception exception) {
                view.warnFailedToLoadMedias();
                view.hideLoadingMedias();
            }
        });
        imageApi.listAllByPerson(person);
    }

    public void stop() {
        imageApi.cancelAllServices();
    }
}
