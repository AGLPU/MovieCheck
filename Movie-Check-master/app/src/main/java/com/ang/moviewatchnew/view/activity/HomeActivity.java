package com.ang.moviewatchnew.view.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.ang.moviewatchnew.Adsbuilder;
import com.ang.moviewatchnew.MovieCheckApplication;
import com.ang.moviewatchnew.R;
import com.ang.moviewatchnew.dagger.HomeViewModule;
import com.ang.moviewatchnew.model.entity.Movie;
import com.ang.moviewatchnew.model.entity.User;
import com.ang.moviewatchnew.presenter.HomePresenter;
import com.ang.moviewatchnew.view.HomeView;
import com.ang.moviewatchnew.view.adapter.MovieListAdapter;
import com.ang.moviewatchnew.view.adapter.NowPlayingMovieListAdapter;
import com.ang.moviewatchnew.view.adapter.OnItemClickListener;
import com.ang.moviewatchnew.view.adapter.TopRatedMovieListAdapter;
import com.ang.moviewatchnew.view.adapter.UpcomingMovieListAdapter;
import com.facebook.ads.AudienceNetworkAds;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView {

    @Inject
    HomePresenter presenter;
    private List<Movie> popularMovieList;
    private List<Movie> topRatedMovieList;
    private List<Movie> upcomingMovieList;
    private List<Movie> nowPlayingMovieList;
    private static final String KEY_POPULARMOVIELIST = "POPULARMOVIELIST";
    private static final String KEY_TOPRATEMOVIELIST = "TOPRATEMOVIELIST";
    private static final String KEY_UPCOMINGMOVIELIST = "UPCOMINGMOVIELIST";
    private static final String KEY_NOWPLAYINGMOVIELIST = "NOWPLAYINGMOVIELIST";
    private static final int RC_SIGN_IN = 27832;

    @Bind(R.id.recyclerview_nowplaying)
    RecyclerView recyclerViewNowPlaying;
    @Bind(R.id.recyclerview_popular)
    RecyclerView recyclerViewPopular;
    @Bind(R.id.recyclerview_toprated)
    RecyclerView recyclerViewTopRated;
    @Bind(R.id.recyclerview_upcoming)
    RecyclerView recyclerViewUpcoming;
    @Bind(R.id.progressbar_popular)
    ProgressBar progressBarPopular;
    @Bind(R.id.progressbar_toprated)
    ProgressBar progressBarTopRated;
    @Bind(R.id.progressbar_upcoming)
    ProgressBar progressBarUpcoming;
    @Bind(R.id.progressbar_nowplaying)
    ProgressBar progressBarNowPlaying;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.linearlayout_nowplaying_anyfounded)
    LinearLayout linearLayoutNowPlayingAnyFounded;
    @Bind(R.id.linearlayout_nowplaying_loadfailed)
    LinearLayout linearLayoutNowPlayingLoadFailed;
    @Bind(R.id.linearlayout_upcoming_anyfounded)
    LinearLayout linearLayoutUpcomingAnyFounded;
    @Bind(R.id.linearlayout_upcoming_loadfailed)
    LinearLayout linearLayoutUpcomingLoadFailed;
    @Bind(R.id.linearlayout_toprated_anyfounded)
    LinearLayout linearLayoutTopRatedAnyFounded;
    @Bind(R.id.linearlayout_toprated_loadfailed)
    LinearLayout linearLayoutTopRatedLoadFailed;
    @Bind(R.id.linearlayout_popular_anyfounded)
    LinearLayout linearLayoutPopularAnyFounded;
    @Bind(R.id.linearlayout_popular_loadfailed)
    LinearLayout linearLayoutPopularLoadFailed;
    @Bind(R.id.navigation_view)
    NavigationView navigationView;
    @Bind(R.id.drawer)
    DrawerLayout drawerLayout;

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
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        ((MovieCheckApplication) getApplication()).getObjectGraph().plus(new HomeViewModule(this)).inject(this);

        setSupportActionBar(toolbar);

        setupFBAds();
        if (savedInstanceState == null) {
            presenter.listPopularMovies();
            presenter.listTopRatedMovies();
            presenter.listUpcomingMovies();
            presenter.listNowPlayingMovies();
        } else {
            popularMovieList = savedInstanceState.getParcelableArrayList(KEY_POPULARMOVIELIST);
            topRatedMovieList = savedInstanceState.getParcelableArrayList(KEY_TOPRATEMOVIELIST);
            upcomingMovieList = savedInstanceState.getParcelableArrayList(KEY_UPCOMINGMOVIELIST);
            nowPlayingMovieList = savedInstanceState.getParcelableArrayList(KEY_NOWPLAYINGMOVIELIST);
            if (popularMovieList == null) {
                presenter.listPopularMovies();
            } else {
                if (popularMovieList.size() == 0) {
                    warnAnyPopularMovieFounded();
                } else {
                    showPopularMovies(popularMovieList);
                }
            }
            if (topRatedMovieList == null) {
                presenter.listTopRatedMovies();
            } else {
                if (topRatedMovieList.size() == 0) {
                    warnAnyTopRatedMovieFounded();
                } else {
                    showTopRatedMovies(topRatedMovieList);
                }
            }
            if (upcomingMovieList == null) {
                presenter.listUpcomingMovies();
            } else {
                if (upcomingMovieList.size() == 0) {
                    warnAnyUpcomingMovieFounded();
                } else {
                    showUpcomingMovies(upcomingMovieList);
                }
            }
            if (nowPlayingMovieList == null) {
                presenter.listNowPlayingMovies();
            } else {
                if (nowPlayingMovieList.size() == 0) {
                    warnAnyNowPlayingMovieFounded();
                } else {
                    showNowPlayingMovies(nowPlayingMovieList);
                }
            }
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_search:
                        startActivity(SearchActivity.newIntent(HomeActivity.this), ActivityOptionsCompat.makeSceneTransitionAnimation(HomeActivity.this).toBundle());
                        drawerLayout.closeDrawers();
                        startAds();
                        break;
                    case R.id.drawer_nowplaying:
                        startActivity(ListNowPlayingMoviesActivity.newIntent(HomeActivity.this), ActivityOptionsCompat.makeSceneTransitionAnimation(HomeActivity.this).toBundle());
                        drawerLayout.closeDrawers();
                        startAds();
                        break;
                    case R.id.drawer_upcoming:
                        startActivity(ListUpcomingMoviesActivity.newIntent(HomeActivity.this), ActivityOptionsCompat.makeSceneTransitionAnimation(HomeActivity.this).toBundle());
                        drawerLayout.closeDrawers();
                        startAds();
                        break;
                    case R.id.drawer_toprated:
                        startActivity(ListTopRatedMoviesActivity.newIntent(HomeActivity.this), ActivityOptionsCompat.makeSceneTransitionAnimation(HomeActivity.this).toBundle());
                        drawerLayout.closeDrawers();
                        startAds();
                        break;
                    case R.id.drawer_popular:
                        startActivity(ListPopularMoviesActivity.newIntent(HomeActivity.this), ActivityOptionsCompat.makeSceneTransitionAnimation(HomeActivity.this).toBundle());
                        drawerLayout.closeDrawers();
                        startAds();
                        break;
                }
                return false;

            }
        });
        MenuItem menuItemDiscovery = navigationView.getMenu().getItem(1);
        SpannableString spannableString = new SpannableString(getString(R.string.discoveryactivity_title));
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.gray_light)), 0, spannableString.length(), 0);
        menuItemDiscovery.setTitle(spannableString);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.generic_opendrawer, R.string.generic_closedrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        View headerView = navigationView.getHeaderView(0);


        presenter.init();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void startActivity(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            startActivity(SearchActivity.newIntent(this, intent.getStringExtra(SearchManager.QUERY)), ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
        } else {
            super.startActivity(intent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (popularMovieList != null) {
            outState.putParcelableArrayList(KEY_POPULARMOVIELIST, new ArrayList<>(popularMovieList));
        }
        if (topRatedMovieList != null) {
            outState.putParcelableArrayList(KEY_TOPRATEMOVIELIST, new ArrayList<>(topRatedMovieList));
        }
        if (upcomingMovieList != null) {
            outState.putParcelableArrayList(KEY_UPCOMINGMOVIELIST, new ArrayList<>(upcomingMovieList));
        }
        if (nowPlayingMovieList != null) {
            outState.putParcelableArrayList(KEY_NOWPLAYINGMOVIELIST, new ArrayList<>(nowPlayingMovieList));
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showLoadingUpcomingMovies() {
        progressBarUpcoming.setVisibility(View.VISIBLE);
        linearLayoutUpcomingAnyFounded.setVisibility(View.GONE);
        linearLayoutUpcomingLoadFailed.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadingUpcomingMovies() {
        progressBarUpcoming.setVisibility(View.GONE);
    }

    @Override
    public void showUpcomingMovies(List<Movie> movieList) {
        upcomingMovieList = movieList;
        recyclerViewUpcoming.setVisibility(View.VISIBLE);
        linearLayoutUpcomingAnyFounded.setVisibility(View.GONE);
        linearLayoutUpcomingLoadFailed.setVisibility(View.GONE);
        recyclerViewUpcoming.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        recyclerViewUpcoming.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUpcoming.setAdapter(new UpcomingMovieListAdapter(movieList, new OnItemClickListener<Movie>() {
            @Override
            public void onClick(Movie movie, View view) {
                startActivity(MovieProfileActivity.newIntent(HomeActivity.this, movie), ActivityOptionsCompat.makeSceneTransitionAnimation(HomeActivity.this, view.findViewById(R.id.imageview_poster), "moviePoster").toBundle());
            }

            @Override
            public void onLongClick(Movie movie, View view) {

            }
        }));
    }

    @Override
    public void warnFailedToLoadUpcomingMovies() {
        recyclerViewUpcoming.setVisibility(View.GONE);
        linearLayoutUpcomingAnyFounded.setVisibility(View.GONE);
        linearLayoutUpcomingLoadFailed.setVisibility(View.VISIBLE);
        linearLayoutUpcomingLoadFailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.listUpcomingMovies();
            }
        });
    }

    @Override
    public void warnAnyUpcomingMovieFounded() {
        recyclerViewUpcoming.setVisibility(View.GONE);
        linearLayoutUpcomingAnyFounded.setVisibility(View.VISIBLE);
        linearLayoutUpcomingLoadFailed.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingPopularMovies() {
        progressBarPopular.setVisibility(View.VISIBLE);
        linearLayoutPopularAnyFounded.setVisibility(View.GONE);
        linearLayoutPopularLoadFailed.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadingPopularMovies() {
        progressBarPopular.setVisibility(View.GONE);
    }

    @Override
    public void warnAnyPopularMovieFounded() {
        recyclerViewPopular.setVisibility(View.GONE);
        linearLayoutPopularAnyFounded.setVisibility(View.VISIBLE);
        linearLayoutPopularLoadFailed.setVisibility(View.GONE);
    }

    @Override
    public void warnFailedToLoadPopularMovies() {
        recyclerViewPopular.setVisibility(View.GONE);
        linearLayoutPopularAnyFounded.setVisibility(View.GONE);
        linearLayoutPopularLoadFailed.setVisibility(View.VISIBLE);
        linearLayoutPopularLoadFailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.listPopularMovies();
            }
        });
    }

    @Override
    public void showPopularMovies(List<Movie> movieList) {
        this.popularMovieList = movieList;
        recyclerViewPopular.setVisibility(View.VISIBLE);
        linearLayoutPopularAnyFounded.setVisibility(View.GONE);
        linearLayoutPopularLoadFailed.setVisibility(View.GONE);
        recyclerViewPopular.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        recyclerViewPopular.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPopular.setAdapter(new MovieListAdapter(movieList, new OnItemClickListener<Movie>() {
            @Override
            public void onClick(Movie movie, View view) {
                startActivity(MovieProfileActivity.newIntent(HomeActivity.this, movie), ActivityOptionsCompat.makeSceneTransitionAnimation(HomeActivity.this, view.findViewById(R.id.imageview_poster), "moviePoster").toBundle());
            }

            @Override
            public void onLongClick(Movie movie, View view) {

            }
        }));
    }

    @Override
    public void showLoadingTopRatedMovies() {
        progressBarTopRated.setVisibility(View.VISIBLE);
        linearLayoutTopRatedAnyFounded.setVisibility(View.GONE);
        linearLayoutTopRatedLoadFailed.setVisibility(View.GONE);
    }

    @Override
    public void warnAnyTopRatedMovieFounded() {
        recyclerViewTopRated.setVisibility(View.GONE);
        linearLayoutTopRatedAnyFounded.setVisibility(View.VISIBLE);
        linearLayoutTopRatedLoadFailed.setVisibility(View.GONE);
    }

    @Override
    public void showTopRatedMovies(List<Movie> movieList) {
        topRatedMovieList = movieList;
        recyclerViewTopRated.setVisibility(View.VISIBLE);
        linearLayoutTopRatedAnyFounded.setVisibility(View.GONE);
        linearLayoutTopRatedLoadFailed.setVisibility(View.GONE);
        recyclerViewTopRated.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        recyclerViewTopRated.setItemAnimator(new DefaultItemAnimator());
        recyclerViewTopRated.setAdapter(new TopRatedMovieListAdapter(movieList, new OnItemClickListener<Movie>() {
            @Override
            public void onClick(Movie movie, View view) {
                startActivity(MovieProfileActivity.newIntent(HomeActivity.this, movie), ActivityOptionsCompat.makeSceneTransitionAnimation(HomeActivity.this, view.findViewById(R.id.imageview_poster), "moviePoster").toBundle());
            }

            @Override
            public void onLongClick(Movie movie, View view) {

            }
        }));
    }

    @Override
    public void hideLoadingTopRatedMovies() {
        progressBarTopRated.setVisibility(View.GONE);
    }

    @Override
    public void warnFailedToLoadTopRatedMovies() {
        recyclerViewTopRated.setVisibility(View.GONE);
        linearLayoutTopRatedAnyFounded.setVisibility(View.GONE);
        linearLayoutTopRatedLoadFailed.setVisibility(View.VISIBLE);
        linearLayoutTopRatedLoadFailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.listTopRatedMovies();
            }
        });
    }

    @Override
    public void showLoadingNowPlayingMovies() {
        progressBarNowPlaying.setVisibility(View.VISIBLE);
        linearLayoutNowPlayingAnyFounded.setVisibility(View.GONE);
        linearLayoutNowPlayingLoadFailed.setVisibility(View.GONE);
    }

    @Override
    public void warnAnyNowPlayingMovieFounded() {
        recyclerViewNowPlaying.setVisibility(View.GONE);
        linearLayoutNowPlayingAnyFounded.setVisibility(View.VISIBLE);
        linearLayoutNowPlayingLoadFailed.setVisibility(View.GONE);
    }

    @Override
    public void showNowPlayingMovies(List<Movie> movieList) {
        nowPlayingMovieList = movieList;
        linearLayoutNowPlayingAnyFounded.setVisibility(View.GONE);
        linearLayoutNowPlayingLoadFailed.setVisibility(View.GONE);
        recyclerViewNowPlaying.setVisibility(View.VISIBLE);
        recyclerViewNowPlaying.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        recyclerViewNowPlaying.setItemAnimator(new DefaultItemAnimator());
        recyclerViewNowPlaying.setAdapter(new NowPlayingMovieListAdapter(movieList, new OnItemClickListener<Movie>() {
            @Override
            public void onClick(Movie movie, View view) {
                startActivity(MovieProfileActivity.newIntent(HomeActivity.this, movie), ActivityOptionsCompat.makeSceneTransitionAnimation(HomeActivity.this, view.findViewById(R.id.imageview_backdrop), "movieBackdrop").toBundle());
            }

            @Override
            public void onLongClick(Movie movie, View view) {

            }
        }));
    }

    @Override
    public void hideLoadingNowPlayingMovies() {
        progressBarNowPlaying.setVisibility(View.GONE);
    }

    @Override
    public void warnFailedToLoadNowPlayingMovies() {
        recyclerViewNowPlaying.setVisibility(View.GONE);
        linearLayoutNowPlayingAnyFounded.setVisibility(View.GONE);
        linearLayoutNowPlayingLoadFailed.setVisibility(View.VISIBLE);
        linearLayoutNowPlayingLoadFailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.listNowPlayingMovies();
            }
        });
    }

    @Override
    public void showLoggedUser(User user) {

    }

    @Override
    public void warnUserDesconnected() {

    }


    @Override
    public void showTutorial() {
        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogTheme))
                .setTitle(getString(R.string.homeactivity_welcometitle))
                .setMessage(getString(R.string.homeactivity_welcomedetail))
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        presenter.informUserHasReadTutorial();
                    }
                }).setPositiveButton(getString(R.string.generic_ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                }).create().show();
    }

    public void morePopularMovies(View view) {
        startActivity(ListPopularMoviesActivity.newIntent(this), ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }

    public void moreNowPlayingMovies(View view) {
        startActivity(ListNowPlayingMoviesActivity.newIntent(this), ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }

    public void moreUpcomingMovies(View view) {
        startActivity(ListUpcomingMoviesActivity.newIntent(this), ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }

    public void moreTopRatedMovies(View view) {
        startActivity(ListTopRatedMoviesActivity.newIntent(this), ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }

    public void logout(View view) {
        presenter.logout();
    }

    public void showUserProfile(View view) {
        startActivity(UserProfileActivity.newIntent(this));
    }
}
