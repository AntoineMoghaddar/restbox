package com.example.restbox.objects;

public class Manager extends Person {

    private final Company company;
    private final String phone;

    public Manager(String name, String function, String phone, Company company) {
        super(name, function);
        this.company = company;
        this.phone = phone;
    }

    public Company getCompany() {
        return company;
    }

    public String getPhone() {
        return phone;
    }
}
