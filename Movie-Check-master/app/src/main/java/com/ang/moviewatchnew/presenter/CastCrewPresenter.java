package com.ang.moviewatchnew.presenter;

import com.ang.moviewatchnew.model.api.CastApi;
import com.ang.moviewatchnew.model.api.CrewApi;
import com.ang.moviewatchnew.model.api.asynctask.ApiResultListener;
import com.ang.moviewatchnew.model.entity.Cast;
import com.ang.moviewatchnew.model.entity.Crew;
import com.ang.moviewatchnew.model.entity.Movie;
import com.ang.moviewatchnew.view.CastCrewView;

import java.util.List;

public class CastCrewPresenter {

    private CastCrewView view;
    private CastApi castApi;
    private CrewApi crewApi;

    public CastCrewPresenter(CastCrewView view, CastApi castApi, CrewApi crewApi) {
        this.view = view;
        this.castApi = castApi;
        this.crewApi = crewApi;
    }

    public void loadCrew(Movie movie) {
        view.showLoadingCrew();
        crewApi.setApiResultListener(new ApiResultListener() {
            @Override
            public void onResult(Object object) {
                List<Crew> crewList = (List<Crew>) object;
                if (crewList == null || crewList.size() == 0) {
                    view.warnAnyCrewFounded();
                } else {
                    view.showCrews(crewList);
                }
                view.hideLoadingCrew();
            }

            @Override
            public void onException(Exception exception) {
                view.warnFailedToLoadCrews();
                view.hideLoadingCrew();
            }
        });
        crewApi.listAllByMovie(movie);
    }

    public void loadCast(Movie movie) {
        view.showLoadingCast();
        castApi.setApiResultListener(new ApiResultListener() {
            @Override
            public void onResult(Object object) {
                List<Cast> castList = (List<Cast>) object;
                if (castList == null || castList.size() == 0) {
                    view.warnAnyCastFounded();
                } else {
                    view.showCasts(castList);
                }
                view.hideLoadingCast();
            }

            @Override
            public void onException(Exception exception) {
                view.warnFailedToLoadCasts();
                view.hideLoadingCast();
            }
        });
        castApi.listAllByMovie(movie);
    }

    public void stop() {
        castApi.cancelAllServices();
        crewApi.cancelAllServices();
    }
}
