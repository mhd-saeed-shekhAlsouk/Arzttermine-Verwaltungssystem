package org.example.arzttermin.ViewModel;

import org.example.arzttermin.Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

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

}