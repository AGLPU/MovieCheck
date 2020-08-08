package com.ang.moviewatchnew.dagger;

import com.ang.moviewatchnew.model.api.ImageApi;
import com.ang.moviewatchnew.model.api.VideoApi;
import com.ang.moviewatchnew.presenter.ListMovieMediaPresenter;
import com.ang.moviewatchnew.view.ListMovieMediaView;
import com.ang.moviewatchnew.view.fragment.ListMovieMediaFragment;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = {AppModule.class, ApiModule.class}, injects = ListMovieMediaFragment.class)
public class ListMovieMediaViewModule {

    private ListMovieMediaView view;

    public ListMovieMediaViewModule(ListMovieMediaView view) {
        this.view = view;
    }

    @Provides
    public ListMovieMediaPresenter provideListVideoPresenter(VideoApi videoApi, ImageApi imageApi) {
        return new ListMovieMediaPresenter(view, videoApi, imageApi);
    }
}
