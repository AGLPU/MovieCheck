package com.ang.moviewatchnew.view;

import com.ang.moviewatchnew.model.entity.Cast;
import com.ang.moviewatchnew.model.entity.Crew;

import java.util.List;

public interface CastCrewView {
    void hideLoadingCrew();

    void showLoadingCrew();

    void showCrews(List<Crew> crewList);

    void warnAnyCrewFounded();

    void warnFailedToLoadCrews();

    void showLoadingCast();

    void warnAnyCastFounded();

    void showCasts(List<Cast> crewList);

    void hideLoadingCast();

    void warnFailedToLoadCasts();
}
