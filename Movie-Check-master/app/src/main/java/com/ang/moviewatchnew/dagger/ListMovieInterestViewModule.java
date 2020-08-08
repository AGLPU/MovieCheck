package com.ang.moviewatchnew.dagger;

import android.support.v4.app.FragmentActivity;

import com.ang.moviewatchnew.model.dao.MovieInterestDao;
import com.ang.moviewatchnew.model.dao.UserDao;
import com.ang.moviewatchnew.presenter.ListMovieInterestsPresenter;
import com.ang.moviewatchnew.view.ListMovieInterestsView;
import com.ang.moviewatchnew.view.fragment.ListMovieInterestsFragment;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = {AppModule.class, ApiModule.class, DaoModule.class}, injects = ListMovieInterestsFragment.class)
public class ListMovieInterestViewModule {

    private ListMovieInterestsView view;
    private FragmentActivity activity;

    public ListMovieInterestViewModule(ListMovieInterestsView view, FragmentActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Provides
    public ListMovieInterestsPresenter provideListMovieInterestsPresenter(MovieInterestDao movieInterestDao, UserDao userDao) {
        movieInterestDao.setActivity(activity);
        return new ListMovieInterestsPresenter(view, movieInterestDao, userDao);
    }
}
