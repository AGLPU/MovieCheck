package com.ang.moviewatchnew.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.ang.moviewatchnew.Adsbuilder;
import com.ang.moviewatchnew.MovieCheckApplication;
import com.ang.moviewatchnew.R;
import com.ang.moviewatchnew.dagger.ListMoviesByGenreViewModule;
import com.ang.moviewatchnew.model.entity.Genre;
import com.ang.moviewatchnew.model.entity.Movie;
import com.ang.moviewatchnew.presenter.ListMoviesByGenrePresenter;
import com.ang.moviewatchnew.view.ListMoviesByGenreView;
import com.ang.moviewatchnew.view.adapter.ListViewAdapterWithPagination;
import com.ang.moviewatchnew.view.adapter.MovieListAdapter;
import com.ang.moviewatchnew.view.adapter.OnItemClickListener;
import com.ang.moviewatchnew.view.adapter.OnShowMoreListener;
import com.facebook.ads.AudienceNetworkAds;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListMoviesByGenreActivity extends AppCompatActivity implements ListMoviesByGenreView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerview_movies)
    RecyclerView recyclerViewMovies;
    @Bind(R.id.progressbar)
    ProgressBar progressBar;
    @Bind(R.id.linearlayout_anyfounded)
    LinearLayout linearLayoutAnyFounded;
    @Bind(R.id.linearlayout_loadfailed)
    LinearLayout linearLayoutLoadFailed;

    @Inject
    ListMoviesByGenrePresenter presenter;
    private List<Movie> movieList;
    private Integer columns = 3;
    private int scrollToItem;
    private int page = 1;
    private Genre genre;
    private static final String BUNDLE_KEY_MOVIELIST = "bundle_key_movielist";
    private static final String BUNDLE_KEY_PAGE = "bundle_key_page";
    private static final String KEY_GENRE = "GENRE";
    private final int itensPerPage = 20;

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
        adsbuilder=new Adsbuilder(this,adContainer);
        adsbuilder.buildAdsListner();
        adsbuilder.loadAds();
        adsbuilder.loadBannerAds();
    }

    public void startAds(){
        adsbuilder.showAds();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listmoviebygenre);
        ButterKnife.bind(this);
        ((MovieCheckApplication) getApplication()).getObjectGraph().plus(new ListMoviesByGenreViewModule(this)).inject(this);

        setupFBAds();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        genre = getIntent().getParcelableExtra(KEY_GENRE);
        if (savedInstanceState == null) {
            presenter.loadMovies(genre, page);
        } else {
            List<Movie> movieList = savedInstanceState.getParcelableArrayList(BUNDLE_KEY_MOVIELIST);
            if (movieList == null) {
                presenter.loadMovies(genre, page);
            } else if (movieList.size() == 0) {
                warnAnyMovieFounded();
            } else {
                page = savedInstanceState.getInt(BUNDLE_KEY_PAGE);
                showMovies(movieList);
            }
        }

        getSupportActionBar().setTitle(genre.getName());
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        presenter.stop();
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (movieList != null) {
            outState.putParcelableArrayList(BUNDLE_KEY_MOVIELIST, new ArrayList<>(movieList));
        }
        outState.putInt(BUNDLE_KEY_PAGE, page);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoadingMovies() {
        progressBar.setVisibility(View.VISIBLE);
        linearLayoutAnyFounded.setVisibility(View.GONE);
        linearLayoutLoadFailed.setVisibility(View.GONE);
    }

    @Override
    public void warnAnyMovieFounded() {
        linearLayoutAnyFounded.setVisibility(View.VISIBLE);
        linearLayoutLoadFailed.setVisibility(View.GONE);
        recyclerViewMovies.setVisibility(View.GONE);
    }

    @Override
    public void showMovies(final List<Movie> newMovieList) {
        if (movieList == null) {
            movieList = newMovieList;
        } else {
            movieList.addAll(newMovieList);
        }
        linearLayoutAnyFounded.setVisibility(View.GONE);
        linearLayoutLoadFailed.setVisibility(View.GONE);
        recyclerViewMovies.setVisibility(View.VISIBLE);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, columns, GridLayoutManager.VERTICAL, false);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position >= movieList.size() ? columns : 1;
            }
        });
        recyclerViewMovies.setLayoutManager(layoutManager);
        recyclerViewMovies.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMovies.setAdapter(
                new ListViewAdapterWithPagination(
                        new MovieListAdapter(movieList, new OnItemClickListener<Movie>() {
                            @Override
                            public void onClick(Movie movie, View view) {
                                startActivity(MovieProfileActivity.newIntent(ListMoviesByGenreActivity.this, movie), ActivityOptionsCompat.makeSceneTransitionAnimation(ListMoviesByGenreActivity.this).toBundle());
                            }

                            @Override
                            public void onLongClick(Movie movie, View view) {

                            }
                        }
                        ),
                        new OnShowMoreListener() {
                            @Override
                            public void showMore() {
                                startAds();
                                scrollToItem = layoutManager.findFirstVisibleItemPosition();
                                presenter.loadMovies(genre, ++page);
                            }
                        },
                        itensPerPage)
        );
        recyclerViewMovies.scrollToPosition(scrollToItem);
    }

    @Override
    public void hideLoadingMovies() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void warnFailedToLoadMovies() {
        linearLayoutAnyFounded.setVisibility(View.GONE);
        linearLayoutLoadFailed.setVisibility(View.VISIBLE);
        linearLayoutLoadFailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loadMovies(genre, page);
                startAds();
            }
        });
        recyclerViewMovies.setVisibility(View.GONE);
    }

    public static Intent newIntent(Context context, Genre genre) {
        Intent intent = new Intent(context, ListMoviesByGenreActivity.class);
        intent.putExtra(KEY_GENRE, genre);

        return intent;
    }
}
