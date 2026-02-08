package org.example.arzttermin.Model;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    @Test
    void patientConstructorInitializesCorrectly() {
        Patient patient = new Patient(2, "Bob", "Brown", "bobbrown", "mypassword",
                Date.valueOf("1990-06-10"), "bob@example.com", "Male", true);

        assertEquals(2, patient.getId());
        assertEquals("Bob", patient.getFirstName());
        assertEquals("Brown", patient.getLastName());
        assertEquals("bobbrown", patient.getUsername());
        assertEquals("mypassword", patient.getPassword());
        assertEquals("Patient", patient.getRole());
        assertEquals(Date.valueOf("1990-06-10"), patient.getDob());
        assertEquals("bob@example.com", patient.getEmail());
        assertEquals("Male", patient.getGender());
        assertTrue(patient.isActive());
    }

    @Test
    void getFullNameForPatient() {
        Patient patient = new Patient(3, "Charlie", "Davis", "charlied", "charliepass",
                Date.valueOf("2000-03-25"), "charlie@example.com", "Non-Binary", true);

        assertEquals("Charlie Davis", patient.getFullName());
    }
}
