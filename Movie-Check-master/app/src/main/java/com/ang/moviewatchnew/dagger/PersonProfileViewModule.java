package com.ang.moviewatchnew.dagger;

import com.ang.moviewatchnew.presenter.PersonProfilePresenter;
import com.ang.moviewatchnew.view.PersonProfileView;
import com.ang.moviewatchnew.view.activity.PersonProfileActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, injects = {PersonProfileActivity.class}, includes = {AppModule.class, ApiModule.class})
public class PersonProfileViewModule {

    private PersonProfileView view;

    public PersonProfileViewModule(PersonProfileView view) {
        this.view = view;
    }

    @Provides
    public PersonProfilePresenter providePersonProfilePresenter() {
        return new PersonProfilePresenter(view);
    }
}
