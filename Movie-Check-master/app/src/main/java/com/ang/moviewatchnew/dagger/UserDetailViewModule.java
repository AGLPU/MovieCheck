package com.ang.moviewatchnew.dagger;

import com.ang.moviewatchnew.model.api.GenreApi;
import com.ang.moviewatchnew.model.dao.MovieInterestDao;
import com.ang.moviewatchnew.model.dao.MovieWatchedDao;
import com.ang.moviewatchnew.model.dao.UserDao;
import com.ang.moviewatchnew.presenter.UserDetailPresenter;
import com.ang.moviewatchnew.view.UserDetailView;
import com.ang.moviewatchnew.view.fragment.UserDetailFragment;

import dagger.Module;
import dagger.Provides;

@Module(library = true, injects = UserDetailFragment.class, includes = {AppModule.class, ApiModule.class, DaoModule.class})
public class UserDetailViewModule {

    private UserDetailView view;

    public UserDetailViewModule(UserDetailView view) {
        this.view = view;
    }

    @Provides
    public UserDetailPresenter provideUserDetailPresenter(UserDao userDao, MovieInterestDao movieInterestDao, MovieWatchedDao movieWatchedDao, GenreApi genreApi) {
        return new UserDetailPresenter(view, userDao, movieInterestDao, movieWatchedDao, genreApi);
    }
}
