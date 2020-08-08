package com.ang.moviewatchnew.dagger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ang.moviewatchnew.model.SqliteConnection;
import com.ang.moviewatchnew.model.dao.MovieDao;
import com.ang.moviewatchnew.model.dao.MovieInterestDao;
import com.ang.moviewatchnew.model.dao.MovieNotInterestDao;
import com.ang.moviewatchnew.model.dao.MovieWatchedDao;
import com.ang.moviewatchnew.model.dao.UserDao;
import com.ang.moviewatchnew.model.dao.impl.MovieDaoImpl;
import com.ang.moviewatchnew.model.dao.impl.MovieInterestDaoImpl;
import com.ang.moviewatchnew.model.dao.impl.MovieNotInterestDaoImpl;
import com.ang.moviewatchnew.model.dao.impl.MovieWatchedDaoImpl;
import com.ang.moviewatchnew.model.dao.impl.UserDaoImpl;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = AppModule.class)
public class DaoModule {

    @Provides
    public SQLiteDatabase provideSqLiteDatabase(Context context) {
        return new SqliteConnection(context).getWritableDatabase();
    }

    @Provides
    public UserDao provideUserDao(Context context) {
        return new UserDaoImpl(context, provideSqLiteDatabase(context));
    }

    @Provides
    public MovieDao provideMovieDao(Context context) {
        return new MovieDaoImpl(context, provideSqLiteDatabase(context));
    }

    @Provides
    public MovieInterestDao provideMovieInterestDao(Context context) {
        return new MovieInterestDaoImpl(context, provideSqLiteDatabase(context),
                provideMovieDao(context), provideUserDao(context));
    }

    @Provides
    public MovieNotInterestDao provideMovieNotInterestDao(Context context) {
        return new MovieNotInterestDaoImpl(context, provideSqLiteDatabase(context),
                provideMovieDao(context), provideUserDao(context));
    }

    @Provides
    public MovieWatchedDao provideMovieWatchedDao(Context context) {
        return new MovieWatchedDaoImpl(context, provideSqLiteDatabase(context),
                provideMovieDao(context), provideUserDao(context));
    }
}
