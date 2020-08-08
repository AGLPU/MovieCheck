package com.ang.moviewatchnew.dagger;

import com.ang.moviewatchnew.model.api.GenreApi;
import com.ang.moviewatchnew.model.dao.MovieInterestDao;
import com.ang.moviewatchnew.model.dao.MovieWatchedDao;
import com.ang.moviewatchnew.model.dao.UserDao;
import com.ang.moviewatchnew.presenter.MovieDetailPresenter;
import com.ang.moviewatchnew.view.MovieDetailView;
import com.ang.moviewatchnew.view.fragment.MovieDetailFragment;

import dagger.Module;
import dagger.Provides;

@Module(library = true, injects = MovieDetailFragment.class, includes = {AppModule.class, ApiModule.class, DaoModule.class})
public class MovieDetailViewModule {

    private MovieDetailView view;

    public MovieDetailViewModule(MovieDetailView view) {
        this.view = view;
    }

    @Provides
    public MovieDetailPresenter provideMovieDetailPresenter(GenreApi genreApi, MovieInterestDao movieInterestDao, MovieWatchedDao movieWatchedDao, UserDao userDao) {
        return new MovieDetailPresenter(view, genreApi, movieInterestDao, movieWatchedDao, userDao);
    }
}
