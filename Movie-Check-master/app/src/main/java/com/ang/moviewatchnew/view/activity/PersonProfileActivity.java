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
import com.ang.moviewatchnew.dagger.PersonProfileViewModule;
import com.ang.moviewatchnew.model.entity.Person;
import com.ang.moviewatchnew.presenter.PersonProfilePresenter;
import com.ang.moviewatchnew.view.PersonProfileView;
import com.ang.moviewatchnew.view.fragment.ListPersonMediaFragment;
import com.ang.moviewatchnew.view.fragment.PersonDetailFragment;
import com.ang.moviewatchnew.view.fragment.PersonWorkFragment;
import com.facebook.ads.AudienceNetworkAds;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PersonProfileActivity extends AppCompatActivity implements PersonProfileView {

    private static final String KEY_PERSON = "PERSON";

    @Inject
    PersonProfilePresenter presenter;

    @Nullable
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Nullable
    @Bind(R.id.tabs)
    TabLayout tabLayout;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personprofile);
        ButterKnife.bind(this);
        ((MovieCheckApplication) getApplication()).getObjectGraph().plus(new PersonProfileViewModule(this)).inject(this);
        setupFBAds();

        setSupportActionBar(toolbar);

        final Person person = getIntent().getParcelableExtra(KEY_PERSON);

        presenter.init(person);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (viewPager != null) {
            viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    switch (position) {
                        case 0:
                            startAds();
                            return PersonDetailFragment.newInstance(person);
                        case 1:
                            startAds();
                            return PersonWorkFragment.newInstance(person);
                        case 2:
                            startAds();
                            return ListPersonMediaFragment.newInstance(person);
                        default:
                            return null;
                    }
                }

                @Override
                public int getCount() {
                    return 3;
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    switch (position) {
                        case 0:
                            return getString(R.string.personprofileactivity_general);
                        case 1:
                            return getString(R.string.personprofileactivity_works);
                        case 2:
                            return getString(R.string.personprofileactivity_media);
                        default:
                            return null;
                    }
                }
            });
            tabLayout.setupWithViewPager(viewPager);
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_detail, PersonDetailFragment.newInstance(person)).commit();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_work, PersonWorkFragment.newInstance(person)).commit();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_media, ListPersonMediaFragment.newInstance(person)).commit();
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

    public static Intent newIntent(Context context, Person person) {
        Intent intent = new Intent(context, PersonProfileActivity.class);
        intent.putExtra(KEY_PERSON, person);
        return intent;
    }

    @Override
    public void showPersonName(String title) {
        getSupportActionBar().setTitle(title);
    }
}
