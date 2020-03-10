package com.example.hospital_mgmt;

public class Doctorinformation {

    public String name;
    public String age;
    public String phoneno;
    public String address;
    public String gender;
    public String qualification;
    public String hospital;
    public String usertype;

    public Doctorinformation(){

    }

    public Doctorinformation(String name, String age, String phoneno, String address, String gender, String qualification, String hospital, String usertype) {
        this.name = name;
        this.age = age;
        this.phoneno = phoneno;
        this.address = address;
        this.gender = gender;
        this.qualification = qualification;
        this.hospital = hospital;
        this.usertype = usertype;
    }
}
