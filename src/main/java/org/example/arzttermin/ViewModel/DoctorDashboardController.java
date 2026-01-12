package org.example.arzttermin.ViewModel;

import org.example.arzttermin.AppointmentSystem;
import org.example.arzttermin.Model.Appointment;
import org.example.arzttermin.Model.SingletonAppointmentSystem;
import org.example.arzttermin.Model.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class DoctorDashboardController {

    @FXML
    private AnchorPane rootPane;

    // Doctor Profile Labels
    @FXML
    private Label doctorNameLabel;
    @FXML
    private Label doctorEmailLabel;
    @FXML
    private Label specializationLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label dobLabel;
    @FXML
    private Button editProfileButton;
    @FXML
    private Button logoutButton;

    // Patient Information Panel
    @FXML
    private AnchorPane patientInfoPane;

    // Appointments Panel
    @FXML
    private AnchorPane appointmentsPane;
    @FXML
    private ScrollPane appointmentsScrollPane;

    private User doctor;
    private User selectedPatient;

    public void loadDetails(User doctorUser, AnchorPane pane) {
        doctor = doctorUser;
        // rootPane = pane;

        // Load doctor profile information
        loadDoctorProfile();

        // Load appointments list
        loadAppointmentsList();

        // Show default message in patient info panel
        showDefaultPatientInfo();
    }

    private void loadDoctorProfile() {
        doctorNameLabel.setText("Dr. " + doctor.getFirstName() + " " + doctor.getLastName());
        doctorEmailLabel.setText(doctor.getEmail());
        genderLabel.setText(doctor.getGender());
        dobLabel.setText(new SimpleDateFormat("MM-dd-yyyy").format(doctor.getDob()));

        // Set specialization based on doctor index
        int doctorIndex = SingletonAppointmentSystem.getInstance().getUsers("Doctor").indexOf(doctor);
        String specialization = doctorIndex == 0 ? "General Physician" :
                doctorIndex == 1 ? "Cardiologist" : "Neurologist";
        specializationLabel.setText(specialization);
    }

    private void loadAppointmentsList() {
        if (appointmentsPane == null) return;

        appointmentsPane.getChildren().clear();

        List<Appointment> appointments = SingletonAppointmentSystem.getInstance().getAppointments();

        if (appointments.isEmpty()) {
            Label noAppointmentsLabel = new Label("No appointments found");
            noAppointmentsLabel.setLayoutX(10.0);
            noAppointmentsLabel.setLayoutY(20.0);
            noAppointmentsLabel.setTextFill(Color.web("#666666"));
            noAppointmentsLabel.setFont(new Font("System", 12.0));
            appointmentsPane.getChildren().add(noAppointmentsLabel);
            return;
        }

        for (int i = 0; i < appointments.size(); i++) {
            Appointment app = appointments.get(i);

            // Create appointment pane
            AnchorPane appointmentPane = new AnchorPane();
            appointmentPane.setLayoutX(5.0);
            appointmentPane.setLayoutY(5.0 + (i * 65));
            appointmentPane.setPrefHeight(60.0);
            appointmentPane.setPrefWidth(340.0);
            appointmentPane.setStyle("-fx-background-color: white; -fx-border-color: #343e7c; -fx-border-radius: 5; -fx-background-radius: 5;");

            // Patient name
            Label patientNameLabel = new Label("Patient: " + app.getPatient().getFirstName() + " " + app.getPatient().getLastName());
            patientNameLabel.setLayoutX(10.0);
            patientNameLabel.setLayoutY(5.0);
            patientNameLabel.setFont(new Font("System Bold", 11.0));
            patientNameLabel.setTextFill(Color.web("#343e7c"));

            // Date and time
            Label dateTimeLabel = new Label("Date: " + app.getDate() + " | Time: " + app.getTime());
            dateTimeLabel.setLayoutX(10.0);
            dateTimeLabel.setLayoutY(20.0);
            dateTimeLabel.setFont(new Font("System", 10.0));
            dateTimeLabel.setTextFill(Color.web("#666666"));




            // View Profile Button
            Button profileBtn = new Button("Profile");
            profileBtn.setLayoutX(100.0);
            profileBtn.setLayoutY(35.0);
            profileBtn.setPrefHeight(18.0);
            profileBtn.setPrefWidth(60.0);
            profileBtn.setStyle("-fx-background-color: #2e9f9b; -fx-background-radius: 5; -fx-border-radius: 5;");
            profileBtn.setTextFill(Color.WHITE);
            profileBtn.setFont(new Font("System Bold", 8.0));
            profileBtn.setOnMouseClicked(e -> {
                showPatientProfile(app.getPatient());
            });


            // Cancel Button (for accepted appointments)
            Button cancelBtn = new Button("Cancel");
            cancelBtn.setLayoutX(170.0);
            cancelBtn.setLayoutY(35.0);
            cancelBtn.setPrefHeight(18.0);
            cancelBtn.setPrefWidth(50.0);
            cancelBtn.setStyle("-fx-background-color: #dc3545; -fx-background-radius: 5; -fx-border-radius: 5;");
            cancelBtn.setTextFill(Color.WHITE);
            cancelBtn.setFont(new Font("System Bold", 8.0));
            cancelBtn.setOnMouseClicked(e -> {
                cancelAppointment(app);
            });

            appointmentPane.getChildren().addAll(
                    patientNameLabel, dateTimeLabel, profileBtn, cancelBtn
            );

            appointmentsPane.getChildren().add(appointmentPane);
        }
    }

    private void showPatientProfile(User patient) {
        if (patientInfoPane == null) return;

        patientInfoPane.getChildren().clear();
        selectedPatient = patient;

        // Title
        Label titleLabel = new Label("Patient Profile");
        titleLabel.setLayoutX(10.0);
        titleLabel.setLayoutY(10.0);
        titleLabel.setTextFill(Color.web("#343e7c"));
        titleLabel.setFont(new Font("System Bold", 16.0));

        // Patient Name
        Label nameLabel = new Label("Name: " + patient.getFirstName() + " " + patient.getLastName());
        nameLabel.setLayoutX(10.0);
        nameLabel.setLayoutY(40.0);
        nameLabel.setFont(new Font("System Bold", 12.0));
        nameLabel.setTextFill(Color.web("#343e7c"));

        // Email
        Label emailLabel = new Label("Email: " + patient.getEmail());
        emailLabel.setLayoutX(10.0);
        emailLabel.setLayoutY(65.0);
        emailLabel.setFont(new Font("System", 12.0));
        emailLabel.setTextFill(Color.web("#666666"));

        // Gender
        Label genderLabel = new Label("Gender: " + patient.getGender());
        genderLabel.setLayoutX(10.0);
        genderLabel.setLayoutY(90.0);
        genderLabel.setFont(new Font("System", 12.0));
        genderLabel.setTextFill(Color.web("#666666"));

        // DOB
        Label dobLabel = new Label("Date of Birth: " + new SimpleDateFormat("MM-dd-yyyy").format(patient.getDob()));
        dobLabel.setLayoutX(10.0);
        dobLabel.setLayoutY(115.0);
        dobLabel.setFont(new Font("System", 12.0));
        dobLabel.setTextFill(Color.web("#666666"));

        // Role
        Label roleLabel = new Label("Role: " + patient.getRole());
        roleLabel.setLayoutX(10.0);
        roleLabel.setLayoutY(140.0);
        roleLabel.setFont(new Font("System", 12.0));
        roleLabel.setTextFill(Color.web("#666666"));





        patientInfoPane.getChildren().addAll(
                titleLabel, nameLabel, emailLabel, genderLabel, dobLabel, roleLabel
        );
    }


    private void showDefaultPatientInfo() {
        if (patientInfoPane == null) return;

        patientInfoPane.getChildren().clear();

        Label titleLabel = new Label("Patient Information");
        titleLabel.setLayoutX(10.0);
        titleLabel.setLayoutY(10.0);
        titleLabel.setTextFill(Color.web("#343e7c"));
        titleLabel.setFont(new Font("System Bold", 16.0));

        Label instructionLabel = new Label("Select an appointment to view patient information");
        instructionLabel.setLayoutX(10.0);
        instructionLabel.setLayoutY(50.0);
        instructionLabel.setFont(new Font("System", 12.0));
        instructionLabel.setTextFill(Color.web("#666666"));

        patientInfoPane.getChildren().addAll(titleLabel, instructionLabel);
    }



    private void cancelAppointment(Appointment appointment) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Cancellation");
        confirmAlert.setHeaderText("Cancel Appointment");
        confirmAlert.setContentText("Are you sure you want to cancel this appointment?");

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                String result = SingletonAppointmentSystem.getInstance().cancelAppointment(appointment);

                if (result.equals("Success")) {
                    // Show success message
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Appointment Cancelled");
                    alert.setContentText("The appointment has been successfully cancelled!");
                    alert.showAndWait();

                    // Refresh the appointments list
                    loadAppointmentsList();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Failed to Cancel Appointment");
                    alert.setContentText(result);
                    alert.showAndWait();
                }
            }
        });


    }


    @FXML
    private void handleLogoutClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(AppointmentSystem.class.getResource("View/login.fxml"));
            Parent summaryRoot = loader.load();
            Stage stage = (Stage) rootPane.getScene().getWindow();
            Scene scene = new Scene(summaryRoot, 1000 , 600);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}