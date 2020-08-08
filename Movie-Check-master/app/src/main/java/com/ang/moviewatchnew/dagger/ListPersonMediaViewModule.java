package com.ang.moviewatchnew.dagger;

import com.ang.moviewatchnew.model.api.ImageApi;
import com.ang.moviewatchnew.presenter.ListPersonMediaPresenter;
import com.ang.moviewatchnew.view.ListPersonMediaView;
import com.ang.moviewatchnew.view.fragment.ListPersonMediaFragment;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = {AppModule.class, ApiModule.class}, injects = ListPersonMediaFragment.class)
public class ListPersonMediaViewModule {

    private ListPersonMediaView view;

    public ListPersonMediaViewModule(ListPersonMediaView view) {
        this.view = view;
    }

    @Provides
    public ListPersonMediaPresenter provideListPersonMediaPresenter(ImageApi imageApi) {
        return new ListPersonMediaPresenter(view, imageApi);
    }
}
