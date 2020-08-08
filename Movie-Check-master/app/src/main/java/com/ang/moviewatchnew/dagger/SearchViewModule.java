package com.ang.moviewatchnew.dagger;

import com.ang.moviewatchnew.model.api.MovieApi;
import com.ang.moviewatchnew.model.api.PersonApi;
import com.ang.moviewatchnew.presenter.SearchPresenter;
import com.ang.moviewatchnew.view.SearchView;
import com.ang.moviewatchnew.view.activity.SearchActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = {AppModule.class, ApiModule.class}, injects = SearchActivity.class)
public class SearchViewModule {

    private SearchView view;

    public SearchViewModule(SearchView view) {
        this.view = view;
    }

    @Provides
    public SearchPresenter provideSearchPresenter(MovieApi movieApi, PersonApi personApi) {
        return new SearchPresenter(view, movieApi, personApi);
    }

}
