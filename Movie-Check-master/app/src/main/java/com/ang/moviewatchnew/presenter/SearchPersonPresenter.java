package com.ang.moviewatchnew.presenter;

import com.ang.moviewatchnew.model.api.PersonApi;
import com.ang.moviewatchnew.model.api.asynctask.ApiResultListener;
import com.ang.moviewatchnew.model.entity.Person;
import com.ang.moviewatchnew.view.SearchPersonView;

import java.util.List;

public class SearchPersonPresenter {

    private SearchPersonView view;
    private PersonApi personApi;

    public SearchPersonPresenter(SearchPersonView view, PersonApi personApi) {
        this.view = view;
        this.personApi = personApi;
    }

    public void search(String query, Integer page) {
        view.showLoadingPerson();
        personApi.setApiResultListener(new ApiResultListener() {
            @Override
            public void onResult(Object object) {
                List<Person> personList = (List<Person>) object;
                if (personList == null || personList.size() == 0) {
                    view.warnAnyPersonFounded();
                } else {
                    view.showPerson(personList);
                }
                view.hideLoadingPerson();
            }

            @Override
            public void onException(Exception exception) {
                view.warnFailedToLoadPerson();
                view.hideLoadingPerson();
            }
        });

        personApi.listByName(query, page);
    }


    public void stop() {
        personApi.cancelAllServices();
    }
}
