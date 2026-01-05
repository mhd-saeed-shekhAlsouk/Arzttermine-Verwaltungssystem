package org.example.arzttermin.ViewModel;

import org.example.arzttermin.AppointmentSystem;
import org.example.arzttermin.Model.SingletonAppointmentSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField confirmPasswordTextField;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private RadioButton femaleRadioButton;

    @FXML
    private RadioButton otherRadioButton;

    @FXML
    private DatePicker dobDatePicker;

    @FXML
    private Label errorLabel;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button signUpButton;

    @FXML
    private Button loginButton;

    @FXML
    private void signUpButtonClicked() {
        errorLabel.setVisible(true);
        String gender = (maleRadioButton.isSelected() ? "Male" :
                femaleRadioButton.isSelected() ? "Female" : "Other");

        // Registrierung ohne termsAndConditions und MedicalHistory
        String response = SingletonAppointmentSystem.getInstance().registerUser(
                firstNameTextField.getText(),
                lastNameTextField.getText(),
                emailTextField.getText(),
                usernameTextField.getText(),
                passwordTextField.getText(),
                confirmPasswordTextField.getText(),
                gender,
                dobDatePicker.getValue() == null ? null : java.sql.Date.valueOf(dobDatePicker.getValue()),
                "Patient"
        );

        errorLabel.setText(response);
        if (response.equals("Created")) {
            signUpButton.setVisible(false);
            loginButton.setPrefSize(387, 40);
            loginButton.setLayoutX(111);
            loginButton.setLayoutY(520);
            signUpButton.setFont(Font.font("System Bold", FontWeight.BOLD, 14));
            errorLabel.setText("User Created Successfully");
            errorLabel.setTextFill(Paint.valueOf("GREEN"));
        }
    }

    @FXML
    private void loginButtonClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(AppointmentSystem.class.getResource("View/login.fxml"));
            Parent summaryRoot = loader.load();

            Stage stage = (Stage) rootPane.getScene().getWindow();
            Scene scene = new Scene(summaryRoot, 1000, 600);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        otherRadioButton.setSelected(true);

        maleRadioButton.setOnAction(e -> {
            femaleRadioButton.setSelected(false);
            otherRadioButton.setSelected(false);
        });

        femaleRadioButton.setOnAction(e -> {
            maleRadioButton.setSelected(false);
            otherRadioButton.setSelected(false);
        });

        otherRadioButton.setOnAction(e -> {
            maleRadioButton.setSelected(false);
            femaleRadioButton.setSelected(false);
        });
    }
}
