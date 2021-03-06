package com.ang.moviewatchnew.presenter;

import com.ang.moviewatchnew.model.api.ReviewApi;
import com.ang.moviewatchnew.model.api.asynctask.ApiResultListener;
import com.ang.moviewatchnew.model.entity.Movie;
import com.ang.moviewatchnew.model.entity.Review;
import com.ang.moviewatchnew.view.ListReviewView;

import java.util.List;

public class ListReviewPresenter {

    private ListReviewView view;
    private ReviewApi reviewApi;

    public ListReviewPresenter(ListReviewView view, ReviewApi reviewApi) {
        this.view = view;
        this.reviewApi = reviewApi;
    }

    public void loadReviews(Movie movie, int page) {
        view.showLoadingReview();
        reviewApi.setApiResultListener(new ApiResultListener() {
            @Override
            public void onResult(Object object) {
                List<Review> reviewList = (List<Review>) object;
                if (reviewList == null || reviewList.size() == 0) {
                    view.warnAnyReviewFounded();
                } else {
                    view.showReviews(reviewList);
                }
                view.hideLoadingReviews();
            }

            @Override
            public void onException(Exception exception) {
                view.warnFailedToLoadReviews();
                view.hideLoadingReviews();
            }
        });
        reviewApi.listByMovies(movie, page);
    }

    public void stop() {
        reviewApi.cancelAllServices();
    }
}
