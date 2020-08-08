package com.ang.moviewatchnew.dagger;

import com.ang.moviewatchnew.model.api.PersonApi;
import com.ang.moviewatchnew.presenter.SearchPersonPresenter;
import com.ang.moviewatchnew.view.SearchPersonView;
import com.ang.moviewatchnew.view.activity.SearchPersonActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = ApiModule.class, injects = SearchPersonActivity.class)
public class SearchPersonViewModule {

    SearchPersonView view;

    public SearchPersonViewModule(SearchPersonView view) {
        this.view = view;
    }

    @Provides
    public SearchPersonPresenter provideSearchPersonPresenter(PersonApi movieApi) {
        return new SearchPersonPresenter(view, movieApi);
    }
}
