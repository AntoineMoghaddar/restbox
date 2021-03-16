package com.example.restbox.objects;

public class Person {
    private String name, function;
    private static int id = 0;

    public Person(String name, String function) {
        this.name = name;
        this.function = function;
        Person.id++;
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

    public int getId() {
        return id;
    }
}
