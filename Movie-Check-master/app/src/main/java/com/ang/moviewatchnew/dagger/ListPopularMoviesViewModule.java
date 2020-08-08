package com.ang.moviewatchnew.dagger;

import com.ang.moviewatchnew.model.api.MovieApi;
import com.ang.moviewatchnew.presenter.ListPopularMoviesPresenter;
import com.ang.moviewatchnew.view.ListPopularMoviesView;
import com.ang.moviewatchnew.view.activity.ListPopularMoviesActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = ApiModule.class, injects = ListPopularMoviesActivity.class)
public class ListPopularMoviesViewModule {

    ListPopularMoviesView view;

    public ListPopularMoviesViewModule(ListPopularMoviesView view) {
        this.view = view;
    }

    @Provides
    public ListPopularMoviesPresenter provideListPopularMoviesPresenter(MovieApi movieApi) {
        return new ListPopularMoviesPresenter(view, movieApi);
    }
}
