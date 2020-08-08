package com.ang.moviewatchnew.dagger;

import com.ang.moviewatchnew.model.api.PersonApi;
import com.ang.moviewatchnew.presenter.PersonDetailPresenter;
import com.ang.moviewatchnew.view.PersonDetailView;
import com.ang.moviewatchnew.view.fragment.PersonDetailFragment;

import dagger.Module;
import dagger.Provides;

@Module(library = true, injects = PersonDetailFragment.class, includes = {AppModule.class, ApiModule.class})
public class PersonDetailViewModule {

    private PersonDetailView view;

    public PersonDetailViewModule(PersonDetailView view) {
        this.view = view;
    }

    @Provides
    public PersonDetailPresenter providePersonDetailPresenter(PersonApi personApi) {
        return new PersonDetailPresenter(view, personApi);
    }
}
