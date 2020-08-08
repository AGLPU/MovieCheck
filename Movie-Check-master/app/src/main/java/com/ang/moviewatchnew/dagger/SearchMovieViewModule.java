package com.ang.moviewatchnew.dagger;

import com.ang.moviewatchnew.model.api.MovieApi;
import com.ang.moviewatchnew.presenter.SearchMoviePresenter;
import com.ang.moviewatchnew.view.SearchMovieView;
import com.ang.moviewatchnew.view.activity.SearchMovieActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = ApiModule.class, injects = SearchMovieActivity.class)
public class SearchMovieViewModule {

    SearchMovieView view;

    public SearchMovieViewModule(SearchMovieView view) {
        this.view = view;
    }

    @Provides
    public SearchMoviePresenter provideSearchMoviePresenter(MovieApi movieApi) {
        return new SearchMoviePresenter(view, movieApi);
    }
}
