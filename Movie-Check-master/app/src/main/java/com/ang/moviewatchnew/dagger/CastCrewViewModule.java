package com.ang.moviewatchnew.dagger;

import com.ang.moviewatchnew.model.api.CastApi;
import com.ang.moviewatchnew.model.api.CrewApi;
import com.ang.moviewatchnew.presenter.CastCrewPresenter;
import com.ang.moviewatchnew.view.CastCrewView;
import com.ang.moviewatchnew.view.fragment.CastCrewFragment;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = {AppModule.class, ApiModule.class}, injects = CastCrewFragment.class)
public class CastCrewViewModule {

    private CastCrewView view;

    public CastCrewViewModule(CastCrewView view) {
        this.view = view;
    }

    @Provides
    public CastCrewPresenter provideCastCrewPresenter(CastApi castApi, CrewApi crewApi) {
        return new CastCrewPresenter(view, castApi, crewApi);
    }

}
