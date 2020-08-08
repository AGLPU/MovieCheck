package com.ang.moviewatchnew;

import android.support.multidex.MultiDexApplication;

import com.ang.moviewatchnew.dagger.ApiModule;
import com.ang.moviewatchnew.dagger.AppModule;
import com.ang.moviewatchnew.dagger.DaoModule;

import dagger.ObjectGraph;

public class MovieCheckApplication extends MultiDexApplication {

    private ObjectGraph objectGraph;


    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(
                new Object[]{
                        new AppModule(MovieCheckApplication.this),
                        new ApiModule(),
                        new DaoModule()
                }
        );

    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }
}
