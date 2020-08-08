package com.ang.moviewatchnew.presenter;

import com.ang.moviewatchnew.model.entity.Person;
import com.ang.moviewatchnew.view.PersonProfileView;

public class PersonProfilePresenter {

    private PersonProfileView view;

    public PersonProfilePresenter(PersonProfileView view) {
        this.view = view;
    }

    public void init(Person person) {
        view.showPersonName(person.getName());
    }
}
