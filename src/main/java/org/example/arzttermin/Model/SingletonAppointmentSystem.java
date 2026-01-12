package org.example.arzttermin.Model;
import java.sql.Date;
import java.util.*;

public class SingletonAppointmentSystem {
    private static SingletonAppointmentSystem instance;
    private ArrayList<User> users;
    private ArrayList<Appointment> appointments;
    private User LoggedInUser = null;
    private PersistanceHandlerController db;

    private SingletonAppointmentSystem() {
        users = new ArrayList<>();
        appointments = new ArrayList<>();
        db = PersistanceHandlerController.getInstance();
        loadUsers();
    }

    public static SingletonAppointmentSystem getInstance() {
        if (instance == null) {
            synchronized (SingletonAppointmentSystem.class) {
                if (instance == null) {
                    instance = new SingletonAppointmentSystem();
                }
            }
        }
        return instance;
    }

    public void reloadAppointmentsFromDb() {
        appointments.clear();
        db.loadAppointments(users, appointments);
    }


    public ArrayList<User> getUsers(String role) {
        ArrayList<User> filteredUsers = new ArrayList<>();
        for (User u : users) {
            if (u.getRole().equals(role)) {
                filteredUsers.add(u);
            }
        }
        return filteredUsers;
    }

    public void loadUsers() {
//        users.add(new Admin());
        System.out.println("Loading Data");
        db.loadPatients(users);
        db.loadDoctors(users);
        db.loadAppointments(users, appointments);
    }


    public String registerUser(String firstName, String lastName, String email, String usename, String password, String c_password, String gender, java.sql.Date dob, String role) {
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || usename.isEmpty() || password.isEmpty() || gender.isEmpty()) {
            return "Please Fill All the Fields";
        }
        if (dob.equals(null)) {
            return "Please Enter the Date of Birth";
        }
        if (password.equals(c_password)) {
            int id = users.get(users.size() - 1).getId() + 1;
            Patient p = new Patient(id, firstName, lastName, usename, password, dob, email, gender, true);
            users.add(p);
            db.addPatient(p);
            return "Created";
        }
        return "Password And Confirm Password Must Match";
    }

    public String login(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return "Please provide both username and password.";
        }
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                setLoggedInUser(user);
                return user.getRole();
            }
        }
        return "Invalid Username / Password";
    }

    public User getLoggedInUser() {
        return LoggedInUser;
    }

    public String editProfile(int id, String firstName, String lastName, Date dob, String email) {
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
            return "Please Fill All the Fields";
        }
        if (dob.equals(null)) {
            return "Please Enter the Date of Birth";
        }
        for (User user : users) {
            if (user.getId() == id) {
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setDob(dob);
                user.setEmail(email);
                if (user.getRole().equals("Patient")) {
                    db.updatePatient(user);
                }
                if (user.getRole().equals("Doctor")) {
                    db.updateDoctor(user);
                }
            }
        }
        return "Success";
    }

    public void setLoggedInUser(User user) {
        LoggedInUser = user;
    }

    public User getUser(int id) {
        for (User u : users) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<String> getSpecializations() {
        ArrayList<String> specializations = new ArrayList<>();
        specializations.add("General Physician");
        specializations.add("Cardiologist");
        specializations.add("Neurologist");
        specializations.add("Dermatologist");
        specializations.add("Pediatrician");
        specializations.add("Orthopedist");
        specializations.add("Gynecologist");
        specializations.add("Psychiatrist");
        return specializations;
    }

    public ArrayList<String> getDoctorsBySpecialization(String specialization) {
        ArrayList<String> doctors = new ArrayList<>();
        int doctorIndex = 0;
        for (User u : users) {
            if (u.getRole().equals("Doctor")) {
                // Assign specializations based on doctor index
                String doctorSpecialization = doctorIndex == 0 ? "General Physician" :
                        doctorIndex == 1 ? "Cardiologist" :
                                doctorIndex == 2 ? "Neurologist" : "General Physician";

                if (doctorSpecialization.equals(specialization)) {
                    doctors.add(u.getFirstName() + ' ' + u.getLastName());
                }
                doctorIndex++;
            }
        }
        System.out.println("Doctors for " + specialization + ": " + doctors);
        return doctors;
    }

    public ArrayList<String> getDates(String doc) {
        ArrayList<String> dates = new ArrayList<>();
        User doctor = null;
        for (User u : users) {
            if ((u.getFirstName() + ' ' + u.getLastName()).equals(doc)) {
                doctor = u;
                break;
            }
        }

        for (AvailabilityCalendar a : doctor.getAvailablility()) {
            dates.add(a.getDate());
        }

        return dates;
    }

    public ArrayList<String> getTime(String doc, String date) {
        reloadAppointmentsFromDb();

        User doctor = null;
        for (User u : users) {
            if ((u.getFirstName() + " " + u.getLastName()).equals(doc)) {
                doctor = u;
                break;
            }
        }

        if (doctor == null) {
            return new ArrayList<>();
        }


        ArrayList<String> availableTimes = new ArrayList<>();
        for (AvailabilityCalendar a : doctor.getAvailablility()) {
            if (a.getDate().equals(date)) {
                availableTimes.addAll(a.getAvailablityTimes());
                break;
            }
        }


        for (Appointment ap : appointments) {
            if (ap.getDoctor().equals(doctor) && ap.getDate().equals(date)) {
                availableTimes.remove(ap.getTime());
            }
        }

        return availableTimes;
    }


    public String bookAppointment(String doctor, String date, String time) {

        if (doctor == null || date == null || time == null) {
            return "Error: Select All the Options";
        }

        // Finding Doctor
        User doc = null;
        for (User u : users) {
            if ((u.getFirstName() + ' ' + u.getLastName()).equals(doctor)) {
                doc = u;
                break;
            }
        }

        // Removing Slots of Availability for Dentist
        for (AvailabilityCalendar a : doc.getAvailablility()) {
            if (a.getDate().equals(date)) {
                String tim = null;
                for (String t : a.getAvailablityTimes()) {
                    if (t.equals(time)) {
                        tim = t;
                        break;
                    }
                }
                a.getAvailablityTimes().remove(tim);
                break;
            }
        }

        int appId = appointments.size() > 0 ? appointments.get(appointments.size() - 1).getId() + 1 : 1;
        Appointment app = new Appointment(appId, date, LoggedInUser, doc, time);
        appointments.add(app);
        db.addAppointment(app);


        return "Success";
    }

    public ArrayList<Appointment> getAppointments() {
        ArrayList<Appointment> apps = new ArrayList<>();
        for (Appointment a : appointments) {
            if (a.getPatient().equals(LoggedInUser) || a.getDoctor().equals(LoggedInUser)) {
                apps.add(a);
            }
        }
        return apps;

    }


    public String cancelAppointment(Appointment app) {
        if (app == null) {
            return "Error: No appointment selected";
        }

        appointments.remove(app);

        db.deleteAppointment(app);


        return "Erfolgreich";
    }

}