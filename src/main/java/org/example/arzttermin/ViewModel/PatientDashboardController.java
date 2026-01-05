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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;


public class PatientDashboardController {
    @FXML
    private Button editProfileButton;

    @FXML
    private Label firstNameLabel1;

    @FXML
    private Label lastNameLabel1;

    @FXML
    private Label usernameLabel1;

    @FXML
    private Label dobLabel1;

    @FXML
    private Label emailLabel1;

    @FXML
    private Label genderLabel1;

    @FXML
    private Label roleLabel1;


    @FXML
    private Button bookAppointmentButton;

    @FXML
    private AnchorPane rootPane;

    // Doctors and Appointments sections
    @FXML
    private AnchorPane doctorsPane;

    @FXML
    private AnchorPane appointmentsPane;

    @FXML
    private ScrollPane appointmentsScrollPane;

    @FXML
    private ScrollPane doctorsScrollPane;

    @FXML
    private AnchorPane bookAppointmentPane;

    // Booking form elements (now in left panel)
    @FXML
    private Label patientNameLabel;

    @FXML
    private ComboBox<String> specializationComboBox;

    @FXML
    private ComboBox<String> doctorComboBox;

    @FXML
    private ComboBox<String> dateComboBox;

    @FXML
    private ComboBox<String> timeComboBox;

    @FXML
    private Button bookAppointmentBtn;

    @FXML
    private AnchorPane doctorProfilePane;

    private User user;
    private User selectedDoctor;

}
