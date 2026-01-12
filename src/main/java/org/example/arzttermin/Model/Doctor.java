package org.example.arzttermin.Model;

import java.util.ArrayList;
import java.sql.Date;

public class Doctor extends User{
    public Doctor(int id, String firstName, String lastName, String username, String password, Date dob, String email, String gender, boolean isActive) {
        super(id, firstName, lastName, username, password, "Doctor", dob, email, gender, isActive);
        this.setAvailablility(new ArrayList<>());
        for (int i = 1; i < 30; i++){
            getAvailablility().add(new AvailabilityCalendar(i + "-01-2025"));
        }

    }


    public Doctor(int id) {
        super(id, "", "", "", "", "Patient", new Date(2024, 10, 2), "", "", true);
    }
}
