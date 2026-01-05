package org.example.arzttermin.ViewModel;

import org.example.arzttermin.AppointmentSystem;
import org.example.arzttermin.Model.Appointment;
import org.example.arzttermin.Model.SingletonAppointmentSystem;
import org.example.arzttermin.Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
//        rootPane = pane;


    }


}