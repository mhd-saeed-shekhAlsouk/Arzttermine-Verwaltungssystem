package org.example.arzttermin.Model;

import java.sql.Date;

public class Patient extends User{
    public  Patient(int id, String firstName, String lastName, String username, String password, Date dob, String email, String gender, boolean isActive) {
        super(id, firstName, lastName, username, password, "Patient", dob, email, gender, isActive);
    }
    public Patient(int id) {
        super(id, "", "", "", "", "Patient", new Date(2024, 10, 2), "", "", true);
    }
}

