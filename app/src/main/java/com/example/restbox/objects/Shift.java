package com.example.restbox.objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Shift {
    private Person employee, manager;
    private Company company;
    private Date date;
    private String timeStart, timeEnd;

    public Shift(Person employee, Person manager, Company company, String date, String timeStart, String timeEnd) {
        this.employee = employee;
        this.manager = manager;
        this.company = company;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            this.date = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Person getEmployee() {
        return employee;
    }

    public void setEmployee(Person employee) {
        this.employee = employee;
    }

    public Person getManager() {
        return manager;
    }

    public void setManager(Person manager) {
        this.manager = manager;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) {
        try {
            this.date = new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
