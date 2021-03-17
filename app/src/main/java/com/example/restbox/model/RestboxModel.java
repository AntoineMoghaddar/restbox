package com.example.restbox.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.restbox.objects.Employee;
import com.example.restbox.objects.Manager;
import com.example.restbox.objects.Person;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class RestboxModel {

    private ArrayList<Person> people;
    private static RestboxModel instance = null;

    private FirebaseFirestore db;

    //Singleton.
    private RestboxModel() {
        people = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
    }

    public static RestboxModel getInstance() {
        if (instance == null)
            instance = new RestboxModel();
        return instance;
    }

    public void newCompany() {

    }

    public void newShift() {

    }

    public ArrayList<Person> getPeople() {
        return people;
    }
    
    public void getPeopleDatabase() {
        db.collection("people").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        if(document.getData().get())
//                        Person person = new Person()
                        if (document.get("function").toString().contains("Bedrijfsleider")) {
                            people.add(newPerson(
                                    document.get("name").toString(),
                                    document.get("dateOfBirth").toString(),
                                    document.get("function").toString(),
                                    document.get("phone").toString()));
                        } else {
                            people.add(newPerson(
                                    document.get("name").toString(),
                                    document.get("dateOfBirth").toString(),
                                    document.get("function").toString(), ""));
                        }
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
        getPeople();
    }

    public Person newPerson(String name, String dateOfBirth, String function, String tel) {
        Person person = null;
        if (function.contains("Bedrijfsleider")) {
            person = new Manager(name, function, dateOfBirth, tel);
        } else {
            person = new Employee(name, function, dateOfBirth);
        }
        return person;
    }

    public Person newPerson(String name, int day, String month, int year, String function, String tel) {
        Person person = null;
        if (function.contains("Bedrijfsleider")) {
            person = new Manager(name, function, formatDate(day, month, year), tel);
        } else {
            person = new Employee(name, function, formatDate(day, month, year));
        }
        return person;
    }

    public void updateFirebase(Person person) {

        HashMap<String, Object> userdata = new HashMap<>();
        userdata.put("name", person.getName());
        userdata.put("function", person.getFunction());
        userdata.put("dateOfBirth", person.getDateOfBirth());

        if (person instanceof Manager) {
            userdata.put("company", ((Manager) person).getCompany());
            userdata.put("phone", ((Manager) person).getPhone());
        }

        System.out.println("updating firebase");
        db.collection("people").add(userdata).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, " DocumentSnapshot added with ID: " + documentReference.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, " Error adding document", e);
            }
        });
    }


    private String formatDate(int day, String month, int year) {
        int maand = 0;
        switch (month) {
            case "Januari":
                maand = 1;
                break;
            case "Februari":
                maand = 2;
                break;
            case "Maart":
                maand = 3;
                break;
            case "April":
                maand = 4;
                break;
            case "Mei":
                maand = 5;
                break;
            case "Juni":
                maand = 6;
                break;
            case "Juli":
                maand = 7;
                break;
            case "Augustus":
                maand = 8;
                break;
            case "September":
                maand = 9;
                break;
            case "Oktober":
                maand = 10;
                break;
            case "November":
                maand = 11;
                break;
            case "December":
                maand = 12;
                break;
        }
        return day + "/" + maand + "/" + year;
    }
}
