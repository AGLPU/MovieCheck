package com.ang.moviewatchnew.presenter;

import com.ang.moviewatchnew.model.dao.UserDao;
import com.ang.moviewatchnew.view.UserProfileView;

public class UserPerfilPresenter {

    private UserProfileView view;
    private UserDao userDao;

    public UserPerfilPresenter(UserProfileView view, UserDao userDao) {
        this.view = view;
        this.userDao = userDao;
    }

    public void init() {
        view.showUserName(userDao.getLoggedUser().getName());
    }
}
