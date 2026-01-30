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

    public void loadDetails(User u, AnchorPane pane) {
        user = u;

        if (SingletonAppointmentSystem.getInstance().getLoggedInUser().getId() != u.getId()) {
            editProfileButton.setVisible(false);
        }

        // Load profile information
        firstNameLabel1.setText(u.getFirstName());
        lastNameLabel1.setText(u.getLastName());
        usernameLabel1.setText(u.getUsername());
        dobLabel1.setText(new SimpleDateFormat("MM-dd-yyyy").format(u.getDob()));
        emailLabel1.setText(u.getEmail());
        genderLabel1.setText(u.getGender());
        roleLabel1.setText(u.getRole());

        loadDoctorsList();
        loadAppointmentsList();
        initializeBookingForm();
        showCurrentProfileInPanel();
    }

    @FXML
    private void editProfile() {
        showEditProfileInPanel();
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

    private void loadDoctorsList() {
        if (doctorsPane == null) return;

        doctorsPane.getChildren().clear();
        int i = 0;
        for (User doctor : SingletonAppointmentSystem.getInstance().getUsers("Doctor")) {
            Pane doctorPane = new Pane();
            doctorPane.setLayoutX(0.0);
            doctorPane.setLayoutY(5.0 + (i * 60));
            doctorPane.setPrefHeight(55.0);
            doctorPane.setPrefWidth(505.0);
            doctorPane.setStyle("-fx-background-color: #232323; -fx-border-color: #5e9f5a; -fx-border-radius: 5; -fx-background-radius: 5;");

            Label doctorName = createLabel(doctor.getFirstName() + " " + doctor.getLastName(), 10, 8, 15, "#5e9f5a", true);
            Label doctorInfo = createLabel(i == 0 ? "Allgemeinmediziner" : i == 1 ? "Kardiologe" : "Neurologe", 10, 27, 11, "#ffffff", false);

            Button viewProfileBtn = new Button("Profil");
            viewProfileBtn.setLayoutX(380.0);
            viewProfileBtn.setLayoutY(12.0);
            viewProfileBtn.setMnemonicParsing(false);
            viewProfileBtn.setPrefHeight(25.0);
            viewProfileBtn.setPrefWidth(80.0);
            viewProfileBtn.setStyle("-fx-background-radius: 15; -fx-background-color: #5e9f5a;");
            viewProfileBtn.setTextFill(Color.WHITE);
            viewProfileBtn.setFont(new Font("System Bold", 10.0));
            viewProfileBtn.setOnMouseClicked(e -> showDoctorProfileInPanel(doctor));

            doctorPane.getChildren().addAll(doctorName, doctorInfo, viewProfileBtn);
            doctorsPane.getChildren().add(doctorPane);
            i++;
        }
    }

    private void loadAppointmentsList() {
        if (appointmentsPane == null) return;

        appointmentsPane.getChildren().clear();
        int i = 0;
        for (Appointment app : SingletonAppointmentSystem.getInstance().getAppointments()) {
            Pane appointmentPane = new Pane();
            appointmentPane.setLayoutX(0.0);
            appointmentPane.setLayoutY(5.0 + (i * 65));
            appointmentPane.setPrefHeight(60.0);
            appointmentPane.setPrefWidth(540.0);
            appointmentPane.setStyle("-fx-background-color: #232323; -fx-border-color: #5e9f5a; -fx-border-radius: 5; -fx-background-radius: 5;");

            Label patientName = createLabel(
                    "Patient: " + app.getPatient().getFirstName() + " " + app.getPatient().getLastName(),
                    10, 5, 15, "#5e9f5a", true
            );
            Label appointmentInfo = createLabel("Datum: " + app.getDate() + " | Uhrzeit: " + app.getTime(), 10, 27, 11, "#ffffff", false);

            Button cancelBtn = new Button("Stornieren");
            cancelBtn.setLayoutX(420.0);
            cancelBtn.setLayoutY(15.0);
            cancelBtn.setMnemonicParsing(false);
            cancelBtn.setPrefHeight(25.0);
            cancelBtn.setPrefWidth(70.0);
            cancelBtn.setStyle("-fx-background-color: #dc2424; -fx-border-radius: 5; -fx-background-radius: 15;");
            cancelBtn.setTextFill(Color.web("#ffffff"));
            cancelBtn.setFont(new Font("System Bold", 10.0));
            cancelBtn.setOnMouseClicked(e -> {
                Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmAlert.setTitle("Termin stornieren");
                confirmAlert.setHeaderText("Termin stornieren");
                confirmAlert.setContentText("Möchten Sie diesen Termin wirklich stornieren?");

                confirmAlert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        String result = SingletonAppointmentSystem.getInstance().cancelAppointment(app);

                        if (result.equals("Erfolgreich")) {
                            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                            successAlert.setTitle("Erfolg");
                            successAlert.setHeaderText(null);
                            successAlert.setContentText("Termin wurde erfolgreich storniert.");
                            successAlert.showAndWait();
                            loadAppointmentsList();
                        }
                    }
                });
            });

            appointmentPane.getChildren().addAll(patientName, appointmentInfo, cancelBtn);
            appointmentsPane.getChildren().add(appointmentPane);
            i++;
        }
    }

    private void initializeBookingForm() {
        if (patientNameLabel != null) {
            patientNameLabel.setText(user.getFirstName() + " " + user.getLastName());
        }

        if (specializationComboBox != null) {
            specializationComboBox.setItems(FXCollections.observableArrayList(SingletonAppointmentSystem.getInstance().getSpecializations()));

            specializationComboBox.setOnAction(event -> {
                if (doctorComboBox != null) {
                    doctorComboBox.setItems(FXCollections.observableArrayList(SingletonAppointmentSystem.getInstance().getDoctorsBySpecialization(specializationComboBox.getValue())));
                    doctorComboBox.setOnAction(doctorEvent -> {
                        selectedDoctor = findDoctorByName(doctorComboBox.getValue());
                        if (selectedDoctor != null) {
                            showDoctorProfileInPanel(selectedDoctor);
                        }
                        updateDateComboBox();
                    });
                }
            });
        }
    }

    private void updateDateComboBox() {
        if (dateComboBox != null && doctorComboBox.getValue() != null) {
            dateComboBox.setItems(FXCollections.observableArrayList(SingletonAppointmentSystem.getInstance().getDates(doctorComboBox.getValue())));
            dateComboBox.setOnAction(event -> {
                if (timeComboBox != null) {
                    timeComboBox.setItems(FXCollections.observableArrayList(SingletonAppointmentSystem.getInstance().getTime(doctorComboBox.getValue(), dateComboBox.getValue())));
                }
            });
        }
    }

    private User findDoctorByName(String doctorName) {
        for (User doctor : SingletonAppointmentSystem.getInstance().getUsers("Doctor")) {
            if ((doctor.getFirstName() + " " + doctor.getLastName()).equals(doctorName)) {
                return doctor;
            }
        }
        return null;
    }

    @FXML
    private void bookAppointmentFromDashboard() {
        if (doctorComboBox.getValue() == null || dateComboBox.getValue() == null || timeComboBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warnung");
            alert.setHeaderText("Fehlende Informationen");
            alert.setContentText("Bitte wählen Sie einen Arzt, ein Datum und eine Uhrzeit aus.");
            alert.showAndWait();
            return;
        }

        String response = SingletonAppointmentSystem.getInstance().bookAppointment(
                doctorComboBox.getValue(),
                dateComboBox.getValue(),
                timeComboBox.getValue()
        );

        if (response.equals("Success")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erfolg");
            alert.setHeaderText("Termin gebucht");
            alert.setContentText("Ihr Termin wurde erfolgreich gebucht!");
            alert.showAndWait();

            specializationComboBox.setValue(null);
            doctorComboBox.setItems(FXCollections.observableArrayList());
            dateComboBox.setItems(FXCollections.observableArrayList());
            timeComboBox.setItems(FXCollections.observableArrayList());

            if (doctorProfilePane != null) {
                doctorProfilePane.getChildren().clear();
            }

            loadAppointmentsList();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Buchung fehlgeschlagen");
            alert.setContentText(response);
            alert.showAndWait();
        }
    }

    private void showDoctorProfileInPanel(User doctor) {
        if (doctorProfilePane == null) return;

        doctorProfilePane.getChildren().clear();

        Label nameLabel = createLabel("Dr. " + doctor.getFirstName() + " " + doctor.getLastName(), 10, 10, 18, "#5e9f5a", true);
        Label usernameLabel = createLabel("Benutzername: " + doctor.getUsername(), 10, 70, 12, "#ffffff", false);
        Label emailLabel = createLabel("E-Mail: " + doctor.getEmail(), 10, 95, 12, "#ffffff", false);
        Label genderLabel = createLabel("Geschlecht: " + doctor.getGender(), 10, 120, 12, "#ffffff", false);
        Label dobLabel = createLabel("Geburtsdatum: " + new SimpleDateFormat("MM-dd-yyyy").format(doctor.getDob()), 10, 145, 12, "#ffffff", false);

        int doctorIndex = SingletonAppointmentSystem.getInstance().getUsers("Doctor").indexOf(doctor);
        String specializ = doctorIndex == 0 ? "Allgemeinmediziner" : doctorIndex == 1 ? "Kardiologe" : "Neurologe";
        Label specializationLabel = createLabel("Fachrichtung: " + specializ, 10, 45, 12, "#ffffff", false);

        doctorProfilePane.getChildren().addAll(nameLabel, usernameLabel, emailLabel, genderLabel, dobLabel, specializationLabel);
    }

    private void showEditProfileInPanel() {
        if (doctorProfilePane == null) return;

        doctorProfilePane.getChildren().clear();

        Label titleLabel = createLabel("Profil bearbeiten", 10, 7, 18, "#5e9f5a", true);
        Label firstNameLabel = createLabel("Vorname:", 10, 50, 12, "#ffffff", true);
        TextField firstNameField = new TextField(user.getFirstName());
        firstNameField.setLayoutX(120.0);
        firstNameField.setLayoutY(45.0);
        firstNameField.setPrefHeight(25.0);
        firstNameField.setPrefWidth(200.0);
        firstNameField.setStyle("-fx-background-color: white; -fx-border-color: #343e7c; -fx-background-radius: 5; -fx-border-radius: 5;");

        Label lastNameLabel = createLabel("Nachname:", 10, 85, 12, "#ffffff", true);
        TextField lastNameField = new TextField(user.getLastName());
        lastNameField.setLayoutX(120.0);
        lastNameField.setLayoutY(80.0);
        lastNameField.setPrefHeight(25.0);
        lastNameField.setPrefWidth(200.0);
        lastNameField.setStyle("-fx-background-color: white; -fx-border-color: #343e7c; -fx-background-radius: 5; -fx-border-radius: 5;");

        Label emailLabel = createLabel("E-Mail:", 10, 120, 12, "#ffffff", true);
        TextField emailField = new TextField(user.getEmail());
        emailField.setLayoutX(120.0);
        emailField.setLayoutY(115.0);
        emailField.setPrefHeight(25.0);
        emailField.setPrefWidth(200.0);
        emailField.setStyle("-fx-background-color: white; -fx-border-color: #343e7c; -fx-background-radius: 5; -fx-border-radius: 5;");

        Label dobLabel = createLabel("Geburtsdatum:", 10, 155, 12, "#ffffff", true);
        DatePicker dobPicker = new DatePicker();
        dobPicker.setLayoutX(120.0);
        dobPicker.setLayoutY(150.0);
        dobPicker.setPrefHeight(25.0);
        dobPicker.setPrefWidth(200.0);
        dobPicker.setValue(user.getDob().toLocalDate());
        dobPicker.setStyle("-fx-background-color: white; -fx-border-color: #343e7c; -fx-background-radius: 5; -fx-border-radius: 5;");

        Button saveBtn = new Button("Speichern");
        saveBtn.setLayoutX(120.0);
        saveBtn.setLayoutY(190.0);
        saveBtn.setPrefHeight(30.0);
        saveBtn.setPrefWidth(120.0);
        saveBtn.setStyle("-fx-background-color: #5e9f5a; -fx-background-radius: 5; -fx-border-radius: 5;");
        saveBtn.setTextFill(Color.WHITE);
        saveBtn.setFont(new Font("System Bold", 9.0));
        saveBtn.setOnMouseClicked(e -> saveProfileChanges(firstNameField.getText(), lastNameField.getText(), emailField.getText(), dobPicker.getValue()));

        Button cancelBtn = new Button("Abbrechen");
        cancelBtn.setLayoutX(250.0);
        cancelBtn.setLayoutY(190.0);
        cancelBtn.setPrefHeight(30.0);
        cancelBtn.setPrefWidth(70.0);
        cancelBtn.setStyle("-fx-background-color: #d50830; -fx-background-radius: 5; -fx-border-radius: 5;");
        cancelBtn.setTextFill(Color.WHITE);
        cancelBtn.setFont(new Font("System Bold", 9.0));
        cancelBtn.setOnMouseClicked(e -> showCurrentProfileInPanel());

        doctorProfilePane.getChildren().addAll(titleLabel, firstNameLabel, firstNameField, lastNameLabel, lastNameField,
                emailLabel, emailField, dobLabel, dobPicker, saveBtn, cancelBtn);
    }

    private void saveProfileChanges(String firstName, String lastName, String email, java.time.LocalDate dob) {
        String response = SingletonAppointmentSystem.getInstance().editProfile(
                user.getId(), firstName, lastName, java.sql.Date.valueOf(dob), email
        );

        if (response.equals("Erfolgreich")) {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setDob(java.sql.Date.valueOf(dob));
            refreshDashboardData();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erfolgreich");
            alert.setHeaderText("Profil wurde Erfolgreich bearbeitet");
            alert.showAndWait();
            showCurrentProfileInPanel();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Das Update konnte nicht durchgeführt werden");
            alert.setContentText(response);
            alert.showAndWait();
        }
    }

    private void refreshDashboardData() {
        firstNameLabel1.setText(user.getFirstName());
        lastNameLabel1.setText(user.getLastName());
        emailLabel1.setText(user.getEmail());
        dobLabel1.setText(new SimpleDateFormat("MM-dd-yyyy").format(user.getDob()));
    }

    private void showCurrentProfileInPanel() {
        if (doctorProfilePane == null) return;

        doctorProfilePane.getChildren().clear();

        Label titleLabel = createLabel("Mein Profil", 10, 7, 18, "#5e9f5a", true);
        Label nameLabel = createLabel("Name: " + user.getFirstName() + " " + user.getLastName(), 10, 40, 12, "#ffffff", false);
        Label emailLabel = createLabel("E-Mail: " + user.getEmail(), 10, 65, 12, "#ffffff", false);
        Label dobLabel = createLabel("Geburtsdatum: " + new SimpleDateFormat("MM-dd-yyyy").format(user.getDob()), 10, 90, 12, "#ffffff", false);
        Label genderLabel = createLabel("Geschlecht: " + user.getGender(), 10, 115, 12, "#ffffff", false);
        Label roleLabel = createLabel("Rolle: " + user.getRole(), 10, 140, 12, "#ffffff", false);

        doctorProfilePane.getChildren().addAll(titleLabel, nameLabel, emailLabel, dobLabel, genderLabel, roleLabel);
    }

    @FXML
    private void handleLogoutClicked(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(AppointmentSystem.class.getResource("/org.example.arzttermin/View/login.fxml"));
            Parent summaryRoot = loader.load();
            Stage stage = (Stage) rootPane.getScene().getWindow();
            Scene scene = new Scene(summaryRoot, 1920, 1024);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Hilfsmethode zum Erstellen von Labels
    private Label createLabel(String text, double x, double y, double fontSize, String colorHex, boolean bold) {
        Label label = new Label(text);
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setTextFill(Color.web(colorHex));
        label.setFont(new Font(bold ? "System Bold" : "System", fontSize));
        return label;
    }
}
