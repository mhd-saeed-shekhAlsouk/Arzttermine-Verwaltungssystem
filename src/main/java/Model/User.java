package Model;

import java.sql.Date;
import java.util.ArrayList;

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
    private String city;
    private ArrayList<AvailabilityCalendar> availability;

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
        this.availability = null;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<AvailabilityCalendar> getAvailability() {
        return availability;
    }

    public void setAvailability(ArrayList<AvailabilityCalendar> availability) {
        this.availability = availability;
    }

    public String getFullName(){
        return firstName + ' ' + lastName;
    }
}
