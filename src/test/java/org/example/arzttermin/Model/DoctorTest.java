
package org.example.arzttermin.Model;


import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {

    @Test
    void doctorConstructorInitializesCorrectly() {
        Doctor doctor = new Doctor(1, "John", "Doe", "johndoe", "password123",
                Date.valueOf("1980-01-01"), "johndoe@example.com", "Male", true);

        assertEquals(1, doctor.getId());
        assertEquals("John", doctor.getFirstName());
        assertEquals("Doe", doctor.getLastName());
        assertEquals("johndoe", doctor.getUsername());
        assertEquals("password123", doctor.getPassword());
        assertEquals("Doctor", doctor.getRole());
        assertEquals(Date.valueOf("1980-01-01"), doctor.getDob());
        assertEquals("johndoe@example.com", doctor.getEmail());
        assertEquals("Male", doctor.getGender());

        assertTrue(doctor.isActive());


        ArrayList<AvailabilityCalendar> availability = doctor.getAvailablility();
        assertEquals(28, availability.size());
        assertEquals("1-02-2026", availability.get(0).getDate());
        assertEquals("28-02-2026", availability.get(27).getDate());
    }

    @Test
    void getFullNameForDoctor() {
        Doctor doctor = new Doctor(1, "Alice", "Smith", "alicesmith", "securepass",
                Date.valueOf("1975-12-15"), "alice@example.com", "Female", true);

        assertEquals("Alice Smith", doctor.getFullName());

    }
}
