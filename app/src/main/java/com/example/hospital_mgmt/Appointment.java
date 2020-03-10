package com.example.hospital_mgmt;

/**
 * Created by Mabel on 8/12/17.
 */


//This handles all appointment information.
public class Appointment {

   // private String username = "ANONYMUS";
    private String month;
    private String day;
    private String year;
    private String time;
    private String name;
    private String desease;
    private String contactNum;
    private String hospital;
    private String doctor;


    public Appointment() {
       // username = "ANONYMUS";
        month = null;
        day = null;
        year = null;
        time = null;
        name = null;
        desease = null;
        contactNum = null;
        hospital = null;
        doctor = null;
    }

    public Appointment(String month, String day, String year, String time, String name, String desease, String contactNum, String hospital, String doctor) {
      //  username = "ANONYMUS";
        this.month = month;
        this.day = day;
        this.year = year;
        this.time = time;
        this.name = name;
        this.desease = desease;
        this.contactNum = contactNum;
        this.hospital = hospital;
        this.doctor = doctor;
    }

  //  public void setUsername() {

  //  }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesease(String desease) {
        this.desease = desease;
    }

    public void setContactNum(String contactNum){
        this.contactNum = contactNum;
    }

    public void setDoctor(String doctor) { this.doctor = doctor; }

    public void setHospital(String hospital) { this.hospital = hospital; }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getYear() {
        return year;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getDesease() {
        return desease;
    }

    public String getContactNum() {
        return contactNum;
    }

    public String getDoctor() { return doctor; }

    public String getHospital() { return hospital; }
}