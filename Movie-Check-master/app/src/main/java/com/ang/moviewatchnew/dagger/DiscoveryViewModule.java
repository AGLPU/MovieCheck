package com.ang.moviewatchnew.dagger;

import com.ang.moviewatchnew.model.api.MovieApi;
import com.ang.moviewatchnew.model.dao.MovieInterestDao;
import com.ang.moviewatchnew.model.dao.MovieNotInterestDao;
import com.ang.moviewatchnew.model.dao.MovieWatchedDao;
import com.ang.moviewatchnew.model.dao.UserDao;
import com.ang.moviewatchnew.presenter.DiscoveryPresenter;
import com.ang.moviewatchnew.view.DiscoveryView;
import com.ang.moviewatchnew.view.activity.DiscoveryActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = {ApiModule.class, DaoModule.class}, injects = DiscoveryActivity.class)
public class DiscoveryViewModule {

    private DiscoveryView view;

    public DiscoveryViewModule(DiscoveryView view) {
        this.view = view;
    }

    @Provides
    public DiscoveryPresenter provideDiscoveryPresenter(MovieApi movieApi, MovieWatchedDao movieWatchedDao,
                                                        MovieInterestDao movieInterestDao, UserDao userDao,
                                                        MovieNotInterestDao movieNotInterestDao) {
        return new DiscoveryPresenter(view, movieApi, movieWatchedDao, movieInterestDao, movieNotInterestDao, userDao);
    }
}
