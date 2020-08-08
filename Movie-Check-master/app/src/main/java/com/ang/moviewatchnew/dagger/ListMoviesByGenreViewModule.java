package com.ang.moviewatchnew.dagger;

import com.ang.moviewatchnew.model.api.MovieApi;
import com.ang.moviewatchnew.presenter.ListMoviesByGenrePresenter;
import com.ang.moviewatchnew.view.ListMoviesByGenreView;
import com.ang.moviewatchnew.view.activity.ListMoviesByGenreActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = ApiModule.class, injects = ListMoviesByGenreActivity.class)
public class ListMoviesByGenreViewModule {

    ListMoviesByGenreView view;

    public ListMoviesByGenreViewModule(ListMoviesByGenreView view) {
        this.view = view;
    }

    @Provides
    public ListMoviesByGenrePresenter provideListMoviesByGenrePresenter(MovieApi movieApi) {
        return new ListMoviesByGenrePresenter(view, movieApi);
    }
}
