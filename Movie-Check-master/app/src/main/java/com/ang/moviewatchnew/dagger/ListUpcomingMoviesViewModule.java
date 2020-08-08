package com.ang.moviewatchnew.dagger;

import com.ang.moviewatchnew.model.api.MovieApi;
import com.ang.moviewatchnew.presenter.ListUpcomingMoviesPresenter;
import com.ang.moviewatchnew.view.ListUpcomingMoviesView;
import com.ang.moviewatchnew.view.activity.ListUpcomingMoviesActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = ApiModule.class, injects = ListUpcomingMoviesActivity.class)
public class ListUpcomingMoviesViewModule {

    ListUpcomingMoviesView view;

    public ListUpcomingMoviesViewModule(ListUpcomingMoviesView view) {
        this.view = view;
    }

    @Provides
    public ListUpcomingMoviesPresenter provideListUpcomingMoviesPresenter(MovieApi movieApi) {
        return new ListUpcomingMoviesPresenter(view, movieApi);
    }
}
