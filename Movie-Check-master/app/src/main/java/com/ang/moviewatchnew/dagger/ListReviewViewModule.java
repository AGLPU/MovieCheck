package com.ang.moviewatchnew.dagger;

import com.ang.moviewatchnew.model.api.ReviewApi;
import com.ang.moviewatchnew.presenter.ListReviewPresenter;
import com.ang.moviewatchnew.view.ListReviewView;
import com.ang.moviewatchnew.view.fragment.ListReviewFragment;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = {AppModule.class, ApiModule.class}, injects = ListReviewFragment.class)
public class ListReviewViewModule {

    private ListReviewView view;

    public ListReviewViewModule(ListReviewView view) {
        this.view = view;
    }

    @Provides
    public ListReviewPresenter provideListReviewPresenter(ReviewApi reviewApi) {
        return new ListReviewPresenter(view, reviewApi);
    }
}
