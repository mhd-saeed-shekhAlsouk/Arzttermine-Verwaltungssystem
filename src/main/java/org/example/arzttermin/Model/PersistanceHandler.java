package org.example.arzttermin.Model;

import java.util.ArrayList;

public interface PersistanceHandler {
    void loadPatients(ArrayList<User> users);

    void loadDoctors(ArrayList<User> users);

    void loadAppointments(ArrayList<User> users, ArrayList<Appointment> appointments);

    void addAppointment(Appointment appointment);

    void addPatient(Patient patient);

    void addDoctor(Doctor doctor);

    void updatePatient(User patient);

    void updateDoctor(User doctor);

    void deleteAppointment(Appointment appointment);
}
