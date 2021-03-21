package com.example.restbox.objects;

public class Manager extends Person {

    private Company company;
    private final String phone;

    public Manager(String id, String name, String function, String dateOfBirth, String phone) {
        super(id, name, dateOfBirth ,function);
        this.phone = phone;
    }

    public Company getCompany() {
        return company;
    }

    public String getPhone() {
        return phone;
    }
}
