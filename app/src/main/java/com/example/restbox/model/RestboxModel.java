package com.example.restbox.model;

import android.util.Log;

import com.example.restbox.objects.Employee;
import com.example.restbox.objects.Manager;
import com.example.restbox.objects.Person;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class RestboxModel {

    private ArrayList<Person> people;
    private static RestboxModel instance = null;

    private ArrayList<Person> export_queue;
    private final FirebaseFirestore db;

    //Singleton.
    private RestboxModel() {
        people = new ArrayList<>();
        export_queue = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        fetchAllUsers();
    }

    public static RestboxModel getInstance() {
        if (instance == null)
            instance = new RestboxModel();
        return instance;
    }

    public ArrayList<Person> getManagers() {
        ArrayList<Person> managers = new ArrayList<>();

        for (Person p : people) {
            if (p instanceof Manager) {
                managers.add(p);
            }
        }

        return managers;
    }

    public ArrayList<Person> getQueue() {
        return export_queue;
    }

    public boolean enableExport() {
        return export_queue.size() > 0;
    }

    public void addToExport(Person person) {
        export_queue.add(person);
        System.out.println("Size of export_queue " + export_queue.size());
    }


    public void removeFromExport(Person person) {
        export_queue.remove(person);
        System.out.println("Size of export_queue " + export_queue.size());
    }

    private void cleanCache() {
        people.clear();
        people = new ArrayList<>();
        fetchAllUsers();
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    private Person fetchSpecific(String id) {

        return null;
    }

    public void fetchAllUsers() {
        db.collection("people").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    if (document.get("function").toString().contains("Bedrijfsleider")) {
                        people.add(newPerson(
                                document.getId(),
                                document.get("name").toString(),
                                document.get("dateOfBirth").toString(),
                                document.get("function").toString(),
                                document.get("phone").toString()));
                    } else {
                        people.add(newPerson(
                                document.getId(),
                                document.get("name").toString(),
                                document.get("dateOfBirth").toString(),
                                document.get("function").toString(), ""));
                    }
                }
            } else {
                Log.w(TAG, "Error getting documents.", task.getException());
            }
        });
    }


    public void emptyQueue() {
        System.out.println("cleaning cache");
//       TODO export_queue.clear();
    }

    public Person newPerson(String id, String name, String dateOfBirth, String function, String tel) {
        Person person = null;
        if (function.contains("Bedrijfsleider")) {
            person = new Manager(id, name, function, dateOfBirth, tel);
        } else {
            person = new Employee(id, name, function, dateOfBirth);
        }
        return person;
    }

    public Person newPerson(String id, String name, int day, String month, int year, String function, String tel) {
        Person person;
        if (function.contains("Bedrijfsleider")) {
            person = new Manager(id, name, function, formatDate(day, month, year), tel);
        } else {
            person = new Employee(id, name, function, formatDate(day, month, year));
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
        db.collection("people").add(userdata).addOnSuccessListener(documentReference -> {
            cleanCache();
            Log.d(TAG, " DocumentSnapshot added with ID: " + documentReference.getId());
        }).addOnFailureListener(e -> Log.w(TAG, " Error adding document", e));
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
