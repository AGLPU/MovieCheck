package com.ang.moviewatchnew.view;

import com.ang.moviewatchnew.model.entity.Review;

import java.util.List;

public interface ListReviewView {
    void warnAnyReviewFounded();

    void showLoadingReview();

    void showReviews(List<Review> reviewList);

    void showReviews();

    void hideLoadingReviews();

    void warnFailedToLoadReviews();
}
