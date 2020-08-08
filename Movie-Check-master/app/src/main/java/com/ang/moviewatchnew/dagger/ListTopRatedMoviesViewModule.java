package com.ang.moviewatchnew.dagger;

import com.ang.moviewatchnew.model.api.MovieApi;
import com.ang.moviewatchnew.presenter.ListTopRatedMoviesPresenter;
import com.ang.moviewatchnew.view.ListTopRatedMoviesView;
import com.ang.moviewatchnew.view.activity.ListTopRatedMoviesActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = ApiModule.class, injects = ListTopRatedMoviesActivity.class)
public class ListTopRatedMoviesViewModule {

    ListTopRatedMoviesView view;

    public ListTopRatedMoviesViewModule(ListTopRatedMoviesView view) {
        this.view = view;
    }

    @Provides
    public ListTopRatedMoviesPresenter provideListTopRatedMoviesPresenter(MovieApi movieApi) {
        return new ListTopRatedMoviesPresenter(view, movieApi);
    }
}
