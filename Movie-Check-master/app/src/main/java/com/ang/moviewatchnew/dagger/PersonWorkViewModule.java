package com.ang.moviewatchnew.dagger;

import com.ang.moviewatchnew.model.api.CastApi;
import com.ang.moviewatchnew.model.api.CrewApi;
import com.ang.moviewatchnew.presenter.PersonWorkPresenter;
import com.ang.moviewatchnew.view.PersonWorkView;
import com.ang.moviewatchnew.view.fragment.PersonWorkFragment;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = {AppModule.class, ApiModule.class}, injects = PersonWorkFragment.class)
public class PersonWorkViewModule {

    private PersonWorkView view;

    public PersonWorkViewModule(PersonWorkView view) {
        this.view = view;
    }

    @Provides
    public PersonWorkPresenter providePersonWorkPresenter(CastApi castApi, CrewApi crewApi) {
        return new PersonWorkPresenter(view, castApi, crewApi);
    }

}
