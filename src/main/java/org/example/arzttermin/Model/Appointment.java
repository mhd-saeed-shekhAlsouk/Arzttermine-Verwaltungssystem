package org.example.arzttermin.Model;

public class Appointment {
    private int id;
    private String date;
    private User patient;
    private User doctor;
    private String time;
    private String status;


    public Appointment(int appId, String date, User patient, User doctor, String time) {
        this.id = appId;
        this.date = date;
        this.patient = patient;
        this.doctor = doctor;
        this.time = time;

    }
    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }
}
