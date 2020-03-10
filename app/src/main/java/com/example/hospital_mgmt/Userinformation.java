package com.example.hospital_mgmt;

public class Userinformation {

    public String name;
    public String age;
    public String address;
    public String phoneno;
    public String emergencyph;
    public String relation;
    public String gender;
    public String bloodgroup;
    public String usertype;

    public Userinformation(){

    }

    public Userinformation(String name, String age, String address, String phoneno, String emergencyph, String relation, String gender, String bloodgroup, String usertype) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneno = phoneno;
        this.emergencyph = emergencyph;
        this.relation = relation;
        this.gender = gender;
        this.bloodgroup = bloodgroup;
        this.usertype = usertype;

    }
}

