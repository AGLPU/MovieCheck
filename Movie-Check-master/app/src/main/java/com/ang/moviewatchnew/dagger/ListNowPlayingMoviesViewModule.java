package com.ang.moviewatchnew.dagger;

import com.ang.moviewatchnew.model.api.MovieApi;
import com.ang.moviewatchnew.presenter.ListNowPlayingMoviesPresenter;
import com.ang.moviewatchnew.view.ListNowPlayingMoviesView;
import com.ang.moviewatchnew.view.activity.ListNowPlayingMoviesActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = ApiModule.class, injects = ListNowPlayingMoviesActivity.class)
public class ListNowPlayingMoviesViewModule {

    ListNowPlayingMoviesView view;

    public ListNowPlayingMoviesViewModule(ListNowPlayingMoviesView view) {
        this.view = view;
    }

    @Provides
    public ListNowPlayingMoviesPresenter provideListNowPlayingMoviesPresenter(MovieApi movieApi) {
        return new ListNowPlayingMoviesPresenter(view, movieApi);
    }
}
