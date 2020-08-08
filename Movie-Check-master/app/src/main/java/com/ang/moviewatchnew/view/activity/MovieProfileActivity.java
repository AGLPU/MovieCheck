package com.ang.moviewatchnew.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.ang.moviewatchnew.Adsbuilder;
import com.ang.moviewatchnew.MovieCheckApplication;
import com.ang.moviewatchnew.R;
import com.ang.moviewatchnew.dagger.MovieProfileViewModule;
import com.ang.moviewatchnew.model.entity.Movie;
import com.ang.moviewatchnew.presenter.MovieProfilePresenter;
import com.ang.moviewatchnew.view.MovieProfileView;
import com.ang.moviewatchnew.view.fragment.CastCrewFragment;
import com.ang.moviewatchnew.view.fragment.ListMovieMediaFragment;
import com.ang.moviewatchnew.view.fragment.ListReviewFragment;
import com.ang.moviewatchnew.view.fragment.MovieDetailFragment;
import com.facebook.ads.AudienceNetworkAds;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieProfileActivity extends AppCompatActivity implements MovieProfileView {

    Adsbuilder adsbuilder;

    @Override
    protected void onDestroy() {
        adsbuilder.destroyInterstialAds();
        adsbuilder.destroyBannerAds();
        super.onDestroy();
    }

    private void setupFBAds() {
        // Initialize the Audience Network SDK
        AudienceNetworkAds.initialize(this);


        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        adsbuilder = new Adsbuilder(this, adContainer);
        adsbuilder.buildAdsListner();
        adsbuilder.loadAds();
        adsbuilder.loadBannerAds();
    }

    public void startAds() {
        adsbuilder.showAds();
    }

    private static final String KEY_MOVIE = "MOVIE";

    @Inject
    MovieProfilePresenter presenter;

    @Nullable
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Nullable
    @Bind(R.id.tabs)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movieprofile);
        ButterKnife.bind(this);
        ((MovieCheckApplication) getApplication()).getObjectGraph().plus(new MovieProfileViewModule(this)).inject(this);
        setupFBAds();

        setSupportActionBar(toolbar);

        final Movie movie = getIntent().getParcelableExtra(KEY_MOVIE);

        presenter.init(movie);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (viewPager != null) {
            viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {

                    switch (position) {
                        case 0:
                            startAds();
                            return MovieDetailFragment.newInstance(movie, true);
                        case 1:
                            startAds();
                            return CastCrewFragment.newInstance(movie);
                        case 2:
                            startAds();
                            return ListReviewFragment.newInstance(movie);
                        case 3:
                            startAds();
                            return ListMovieMediaFragment.newInstance(movie);

                        default:
                            return null;
                    }
                }

                @Override
                public int getCount() {
                    return 4;
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    switch (position) {
                        case 0:
                            return getString(R.string.movieprofileactivity_general);
                        case 1:
                            return getString(R.string.movieprofileactivity_castcrew);
                        case 2:
                            return getString(R.string.movieprofileactivity_reviews);
                        case 3:
                            return getString(R.string.movieprofileactivity_media);
                        case 4:
                            return getString(R.string.movieprofileactivity_watch);

                        default:
                            return null;
                    }
                }
            });
            tabLayout.setupWithViewPager(viewPager);
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_media, ListMovieMediaFragment.newInstance(movie)).commit();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_detail, MovieDetailFragment.newInstance(movie, true)).commit();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_castcrew, CastCrewFragment.newInstance(movie)).commit();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_review, ListReviewFragment.newInstance(movie)).commit();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_watch, ListReviewFragment.newInstance(movie)).commit();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieProfileActivity.class);
        intent.putExtra(KEY_MOVIE, movie);
        return intent;
    }

    @Override
    public void showMovieName(String title) {
        getSupportActionBar().setTitle(title);
    }
}
