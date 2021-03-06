package com.ang.moviewatchnew.presenter;


import com.ang.moviewatchnew.model.api.GenreApi;
import com.ang.moviewatchnew.model.api.asynctask.ApiResultListener;
import com.ang.moviewatchnew.model.dao.MovieInterestDao;
import com.ang.moviewatchnew.model.dao.MovieWatchedDao;
import com.ang.moviewatchnew.model.dao.UserDao;
import com.ang.moviewatchnew.model.entity.Genre;
import com.ang.moviewatchnew.model.entity.MovieInterest;
import com.ang.moviewatchnew.view.UserDetailView;

import java.util.ArrayList;
import java.util.List;

public class UserDetailPresenter {

    private static final int NUMBER_GENRE = 5;

    private UserDetailView view;
    private UserDao userDao;
    private MovieInterestDao movieInterestDao;
    private MovieWatchedDao movieWatchedDao;
    private GenreApi genreApi;

    public UserDetailPresenter(UserDetailView view, UserDao userDao, MovieInterestDao movieInterestDao, MovieWatchedDao movieWatchedDao, GenreApi genreApi) {
        this.view = view;
        this.userDao = userDao;
        this.movieInterestDao = movieInterestDao;
        this.movieWatchedDao = movieWatchedDao;
        this.genreApi = genreApi;
    }

    public void init() {
        view.showUser(userDao.getLoggedUser());
    }

    public void loadUpcomingInterests() {
        List<MovieInterest> movieInterestList = movieInterestDao.listAllUpcoming(userDao.getLoggedUser());
        if (movieInterestList == null || movieInterestList.size() == 0) {
            view.warnAnyInterestFound();
        } else {
            view.showUpcomingInterests(movieInterestList);
        }
    }

    public void loadGenres() {
        final List<Long> favoriteGenreIdList = movieWatchedDao.favoriteGenres(userDao.getLoggedUser(), NUMBER_GENRE);
        if (favoriteGenreIdList.size() == 0) {
            view.warnAnyGenreFounded();
            return;
        }
        view.showLoadingGenres();
        genreApi.setApiResultListener(new ApiResultListener() {
            @Override
            public void onResult(Object object) {
                List<Genre> genreList = (List<Genre>) object;
                if (genreList == null || genreList.size() == 0) {
                    view.warnAnyGenreFounded();
                } else {
                    List<Genre> genresOfTheMovieList = new ArrayList<>();
                    for (Genre genre : genreList) {
                        if (favoriteGenreIdList.contains(genre.getId())) {
                            genresOfTheMovieList.add(genre);
                        }
                    }
                    view.showGenres(genresOfTheMovieList);
                }
                view.hideLoadingGenres();
            }

            @Override
            public void onException(Exception exception) {
                view.warnFailedOnLoadGenres();
                view.hideLoadingGenres();
            }
        });
        genreApi.listAllOfMovie();
    }

}
