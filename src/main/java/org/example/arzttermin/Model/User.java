package org.example.arzttermin.Model;

import java.util.ArrayList;
import java.sql.Date;

public abstract class User {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String role;
    private Date dob;
    private String email;
    private String gender;

    private boolean isActive;


    private ArrayList<AvailabilityCalendar> availablility;


    public User() {
    }




    public User(int id, String firstName, String lastName, String username, String password, String role, Date dob, String email, String gender, boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.dob = dob;
        this.email = email;
        this.gender = gender;

        this.isActive = isActive;
        this.availablility = null;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }




    public ArrayList<AvailabilityCalendar> getAvailablility() {
        return availablility;
    }

    public void setAvailablility(ArrayList<AvailabilityCalendar> availablility) {
        this.availablility = availablility;
    }

    public String getFullName(){
        return firstName + ' ' + lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", isActive=" + isActive +
                ", availablility=" + availablility +
                '}';
    }
}
