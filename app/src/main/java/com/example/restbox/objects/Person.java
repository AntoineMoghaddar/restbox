package com.example.restbox.objects;

public class Person {
    private String name, function, dateOfBirth;

    public Person(String name, String dateOfBirth, String function) {
        this.name = name;
        this.function = function;
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }


}
