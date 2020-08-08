package com.ang.moviewatchnew.dagger;

import com.ang.moviewatchnew.model.dao.UserDao;
import com.ang.moviewatchnew.presenter.UserPerfilPresenter;
import com.ang.moviewatchnew.view.UserProfileView;
import com.ang.moviewatchnew.view.activity.UserProfileActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, injects = {UserProfileActivity.class}, includes = {AppModule.class, ApiModule.class, DaoModule.class})
public class UserProfileViewModule {

    private UserProfileView view;

    public UserProfileViewModule(UserProfileView view) {
        this.view = view;
    }

    @Provides
    public UserPerfilPresenter provideUserPresenter(UserDao userDao) {
        return new UserPerfilPresenter(view, userDao);
    }
}
