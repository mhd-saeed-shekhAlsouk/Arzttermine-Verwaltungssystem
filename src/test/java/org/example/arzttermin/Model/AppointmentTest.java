package org.example.arzttermin.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {


    @Test
    void getId() {
        Appointment appointment = new Appointment(1, "2024-12-02", new Patient(1), new Doctor(10), "10:00");
        assertEquals(1, appointment.getId());
    }

    @Test
    void setId() {
        Appointment appointment = new Appointment(1, "2024-12-02", new Patient(1), new Doctor(10),"10:00");
        appointment.setId(2);
        assertEquals(2, appointment.getId());
    }

    @Test
    void getStatus() {
        Appointment appointment = new Appointment(1, "2024-12-02", new Patient(1), new Doctor(10), "10:00");
        appointment.setStatus("Not Accepted");
        assertEquals("Not Accepted", appointment.getStatus());
    }


    @Test
    void setStatus() {
        Appointment appointment = new Appointment(1, "2024-12-02", new Patient(1), new Doctor(10),"10:00");
        appointment.setStatus("Accepted");
        assertEquals("Accepted", appointment.getStatus());
    }

    @Test
    void getDate() {
        Appointment appointment = new Appointment(1, "2024-12-02", new Patient(1), new Doctor(10), "10:00");
        assertEquals("2024-12-02", appointment.getDate());
    }

    @Test
    void setDate() {
        Appointment appointment = new Appointment(1, "2024-12-02", new Patient(1), new Doctor(10), "10:00");
        appointment.setDate("2024-12-03");
        assertEquals("2024-12-03", appointment.getDate());
    }

    @Test
    void getPatient() {
        User patient = new Patient(1);
        Appointment appointment = new Appointment(1, "2024-12-02", patient, new Doctor(10),  "10:00");
        assertEquals(patient, appointment.getPatient());
    }

    @Test
    void setPatient() {
        User patient = new Patient(1);
        User newPatient = new Patient(2);
        Appointment appointment = new Appointment(1, "2024-12-02", patient, new Doctor(10), "10:00");
        appointment.setPatient(newPatient);
        assertEquals(newPatient, appointment.getPatient());
    }

    @Test
    void getDoctor() {
        User doctor = new Doctor(10);
        Appointment appointment = new Appointment(1, "2024-12-02", new Patient(1), doctor, "10:00");
        assertEquals(doctor, appointment.getDoctor());
    }

    @Test
    void setDoctor() {
        User doctor = new Doctor(10);
        User newDoctor = new Doctor(11);
        Appointment appointment = new Appointment(1, "2024-12-02", new Patient(1), doctor, "10:00");
        appointment.setDoctor(newDoctor);
        assertEquals(newDoctor, appointment.getDoctor());
    }

    @Test
    void getTime() {
        Appointment appointment = new Appointment(1, "2024-12-02", new Patient(1), new Doctor(10), "10:00");
        assertEquals("10:00", appointment.getTime());
    }

    @Test
    void setTime() {
        Appointment appointment = new Appointment(1, "2024-12-02", new Patient(1), new Doctor(10), "10:00");
        appointment.setTime("11:00");
        assertEquals("11:00", appointment.getTime());
    }
}
