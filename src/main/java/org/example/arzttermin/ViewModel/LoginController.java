package org.example.arzttermin.ViewModel;

import org.example.arzttermin.AppointmentSystem;
import org.example.arzttermin.Model.SingletonAppointmentSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label errorLabel;

    @FXML
    private void loginButtonClicked() {
        String response = SingletonAppointmentSystem.getInstance()
                .login(usernameField.getText(), passwordField.getText());

        if ("Patient".equals(response)) {
            errorLabel.setVisible(false);
            showPatientPanel();
        } else if ("Doctor".equals(response)) {
            errorLabel.setVisible(false);
            showDoctorPanel();
        } else {
            errorLabel.setVisible(true);
            errorLabel.setText(response);
        }
    }


    @FXML
    private void signUpButtonClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(AppointmentSystem.class.getResource("/org.example.arzttermin/View/signup.fxml"));
            Parent summaryRoot = loader.load();

            Stage stage = (Stage) rootPane.getScene().getWindow();
            Scene scene = new Scene(summaryRoot, 1000 , 600);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void showPatientPanel() {
        try {
            FXMLLoader loader = new FXMLLoader(AppointmentSystem.class.getResource("/org.example.arzttermin/View/patientDashboard.fxml"));
            Parent summaryRoot = loader.load();
            PatientDashboardController controller = loader.getController();
            controller.loadDetails(SingletonAppointmentSystem.getInstance().getLoggedInUser(), null);
            Stage stage = (Stage) rootPane.getScene().getWindow();
            Scene scene = new Scene(summaryRoot, 1000 , 600);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void showDoctorPanel() {
        try {
            FXMLLoader loader = new FXMLLoader(AppointmentSystem.class.getResource("/org.example.arzttermin/View/doctorDashboard.fxml"));
            Parent summaryRoot = loader.load();
            DoctorDashboardController controller = loader.getController();
            controller.loadDetails(SingletonAppointmentSystem.getInstance().getLoggedInUser(), null);
            Stage stage = (Stage) rootPane.getScene().getWindow();
            Scene scene = new Scene(summaryRoot, 1000 , 600);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
