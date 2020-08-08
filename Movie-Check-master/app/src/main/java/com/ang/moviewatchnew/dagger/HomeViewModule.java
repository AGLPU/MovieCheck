package com.ang.moviewatchnew.dagger;

import com.ang.moviewatchnew.model.api.MovieApi;
import com.ang.moviewatchnew.model.dao.UserDao;
import com.ang.moviewatchnew.presenter.HomePresenter;
import com.ang.moviewatchnew.view.HomeView;
import com.ang.moviewatchnew.view.activity.HomeActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = {ApiModule.class, DaoModule.class}, injects = HomeActivity.class)
public class HomeViewModule {

    HomeView view;

    public HomeViewModule(HomeView view) {
        this.view = view;
    }

    @Provides
    public HomePresenter provideHomePresenter(MovieApi movieApi, UserDao userDao) {
        return new HomePresenter(view, movieApi, userDao);
    }

}
