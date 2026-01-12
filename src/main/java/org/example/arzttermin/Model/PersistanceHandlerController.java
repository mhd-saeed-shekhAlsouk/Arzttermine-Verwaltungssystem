package org.example.arzttermin.Model;

import java.util.ArrayList;

public class PersistanceHandlerController {
    private PersistanceHandler db;
    private static PersistanceHandlerController instance;

    public static PersistanceHandlerController getInstance() {
        if (instance == null) {
            synchronized (PersistanceHandlerController.class) {
                if (instance == null) {
                    instance = new PersistanceHandlerController();
                }
            }
        }
        return instance;
    }

    private PersistanceHandlerController(){
        db = new MySQL();
    }

    public void loadPatients(ArrayList<User> users){
        db.loadPatients(users);
    }

    public void loadDoctors(ArrayList<User> users){
        db.loadDoctors(users);
    }

    public void addPatient(Patient patient) { db.addPatient(patient); }
    public void addDoctor(Doctor doctor){ db.addDoctor(doctor); }
    public void updatePatient(User patient) { db.updatePatient(patient); }
    public void updateDoctor(User doctor){ db.updateDoctor(doctor); }
    public void addAppointment(Appointment appointment){db.addAppointment(appointment);}
    public void loadAppointments(ArrayList<User> users, ArrayList<Appointment> appointments){db.loadAppointments(users,appointments);}
    public void deleteAppointment(Appointment appointment){db.deleteAppointment(appointment);}

}
