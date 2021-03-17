package com.example.restbox.objects;

public class Manager extends Person {

    private Company company;
    private final String phone;

    public Manager(String name, String function, String dateOfBirth, String phone) {
        super(name, dateOfBirth ,function);
        this.phone = phone;
    }

    public Company getCompany() {
        return company;
    }

    public String getPhone() {
        return phone;
    }
}
