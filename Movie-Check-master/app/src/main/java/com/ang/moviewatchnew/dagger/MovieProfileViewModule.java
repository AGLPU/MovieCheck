package com.ang.moviewatchnew.dagger;

import com.ang.moviewatchnew.presenter.MovieProfilePresenter;
import com.ang.moviewatchnew.view.MovieProfileView;
import com.ang.moviewatchnew.view.activity.MovieProfileActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, injects = {MovieProfileActivity.class}, includes = {AppModule.class, ApiModule.class})
public class MovieProfileViewModule {

    private MovieProfileView view;

    public MovieProfileViewModule(MovieProfileView view) {
        this.view = view;
    }

    @Provides
    public MovieProfilePresenter provideProfilePresenter() {
        return new MovieProfilePresenter(view);
    }
}
