package com.example.restbox.objects;

public class Employee extends Person {

    private final String dateOfBirth;

    public Employee(String name, String function, String dateOfBirth) {
        super(name, function);
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
