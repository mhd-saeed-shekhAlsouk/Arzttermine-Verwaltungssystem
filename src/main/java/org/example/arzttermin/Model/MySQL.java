package org.example.arzttermin.Model;

import java.sql.*;
import java.util.ArrayList;

public class MySQL implements PersistanceHandler {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://mysql-32de56fe-saeed-dceb.e.aivencloud.com:17742/appointmentSystem?ssl-mode=REQUIRED";
    static final String USER = "avnadmin";
    static final String PASS = "AVNS_cgKYd4impBC21BKLy5d";


    private Connection conn = null;
    private Statement stmt = null;

    public MySQL() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Connecting to database...");
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void loadPatients(ArrayList<User> users) {
        String query = "SELECT * FROM patients";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Date dob = rs.getDate("dob");
                String email = rs.getString("email");
                String gender = rs.getString("gender");
                boolean isActive = rs.getBoolean("isActive");

                Patient patient = new Patient(id, firstName, lastName, username, password, dob, email, gender, isActive);
                users.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadDoctors(ArrayList<User> users) {
        String query = "SELECT * FROM doctors";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Date dob = rs.getDate("dob");
                String email = rs.getString("email");
                String gender = rs.getString("gender");
                boolean isActive = rs.getBoolean("isActive");


                Doctor doctor = new Doctor(id, firstName, lastName, username, password, dob, email, gender, isActive);
                users.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPatient(Patient patient) {
        String query = "INSERT INTO patients (firstName, lastName, username, password, dob, email, gender, isActive) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setString(3, patient.getUsername());
            stmt.setString(4, patient.getPassword());
            stmt.setDate(5, new Date(patient.getDob().getTime()));
            stmt.setString(6, patient.getEmail());
            stmt.setString(7, patient.getGender());
            stmt.setBoolean(8, patient.isActive());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    patient.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addDoctor(Doctor doctor) {
        String query = "INSERT INTO doctors (firstName, lastName, username, password, dob, email, gender, isActive) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, doctor.getFirstName());
            stmt.setString(2, doctor.getLastName());
            stmt.setString(3, doctor.getUsername());
            stmt.setString(4, doctor.getPassword());
            stmt.setDate(5, new Date(doctor.getDob().getTime()));
            stmt.setString(6, doctor.getEmail());
            stmt.setString(7, doctor.getGender());
            stmt.setBoolean(8, doctor.isActive());


            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    doctor.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePatient(User patient) {
        String query = "UPDATE patients SET firstName = ?, lastName = ?, dob = ?, email = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setDate(3, (Date) patient.getDob());
            stmt.setString(4, patient.getEmail());
            stmt.setInt(5, patient.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Patient updated successfully.");
            } else {
                System.out.println("No patient found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDoctor(User doctor) {
        String query = "UPDATE doctors SET firstName = ?, lastName = ?, dob = ?, email = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, doctor.getFirstName());
            stmt.setString(2, doctor.getLastName());
            stmt.setDate(3, (Date) doctor.getDob());
            stmt.setString(4, doctor.getEmail());
            stmt.setInt(5, doctor.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Doctor updated successfully.");
            } else {
                System.out.println("No doctor found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
