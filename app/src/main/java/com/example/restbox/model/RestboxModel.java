package com.example.restbox.model;

import com.example.restbox.objects.Person;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class RestboxModel {

    private ArrayList<HashMap<String, String>> data;
    private RestboxModel instance = null;

    private FirebaseFirestore db;

    //Singleton.
    private RestboxModel() {
        data = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
    }

    public RestboxModel get_instance() {
        if (instance == null)
            instance = new RestboxModel();
        return instance;
    }

    public void newCompany() {

    }

    public void newShift() {

    }

    public void newPerson() {
//        Person person = new Person();
    }
}
