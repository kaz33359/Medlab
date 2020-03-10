package com.example.hospital_mgmt;

public class User {

    private String address;
    private String contactNum;
    private String day;
    private String month;
    private String name;
    private String time;
    private String year;

    public User () {

    }

    public User(String address, String contactNum, String day, String month, String name, String time, String year) {
        this.address = address;
        this.contactNum = contactNum;
        this.day = day;
        this.month = month;
        this.name = name;
        this.time = time;
        this.year = year;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}

