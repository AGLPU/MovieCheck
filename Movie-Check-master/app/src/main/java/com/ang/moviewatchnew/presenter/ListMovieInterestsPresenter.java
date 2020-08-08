package com.ang.moviewatchnew.presenter;

import com.ang.moviewatchnew.model.dao.DaoListener;
import com.ang.moviewatchnew.model.dao.MovieInterestDao;
import com.ang.moviewatchnew.model.dao.UserDao;
import com.ang.moviewatchnew.model.entity.MovieInterest;
import com.ang.moviewatchnew.view.ListMovieInterestsView;

import java.util.List;

public class ListMovieInterestsPresenter {

    private ListMovieInterestsView view;
    private MovieInterestDao movieInterestDao;
    private UserDao userDao;

    public ListMovieInterestsPresenter(ListMovieInterestsView view, MovieInterestDao movieInterestDao, UserDao userDao) {
        this.view = view;
        this.movieInterestDao = movieInterestDao;
        this.userDao = userDao;
    }

    public void loadMovieInterests() {
        movieInterestDao.setDaoListener(new DaoListener() {
            @Override
            public void onLoad(Object object) {
                List<MovieInterest> movieInterestList = (List<MovieInterest>) object;
                if(movieInterestList.size() == 0) {
                    view.warnAnyInterestFounded();
                } else {
                    view.showMovieInterests(movieInterestList);
                }
            }
        });
        movieInterestDao.listAll(userDao.getLoggedUser());
    }

    public void remove(MovieInterest movieInterest) {
        movieInterestDao.remove(movieInterest);
        view.warnMovieRemoved(movieInterest.getMovie());
    }
}
