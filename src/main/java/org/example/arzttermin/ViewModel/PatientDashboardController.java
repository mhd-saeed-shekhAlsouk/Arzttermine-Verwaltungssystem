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

    public void loadDetails(User u, AnchorPane pane){
        user = u;
//        rootPane = pane;
        if (SingletonAppointmentSystem.getInstance().getLoggedInUser().getId() != u.getId()){
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


        // Load doctors list
        loadDoctorsList();

        // Load appointments list
        loadAppointmentsList();

        // Initialize booking form
        initializeBookingForm();

        // Show current profile in middle panel
        showCurrentProfileInPanel();
    }

    @FXML
    private void editProfile(){
        showEditProfileInPanel();
    }


    @FXML
    private void loginButtonClicked(){
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

    private void loadDoctorsList() {
        if (doctorsPane == null) return;

        doctorsPane.getChildren().clear();
        int i = 0;
        for (User doctor : SingletonAppointmentSystem.getInstance().getUsers("Doctor")) {
            Pane doctorPane = new Pane();
            doctorPane.setLayoutX(0.0);
            doctorPane.setLayoutY(5.0 + (i * 50));
            doctorPane.setPrefHeight(45.0);
            doctorPane.setPrefWidth(413.0);
            doctorPane.setStyle("-fx-background-color: #232323; -fx-border-color: #5e9f5a; -fx-border-radius: 5; -fx-background-radius: 5;");

            Label doctorName = new Label(doctor.getFirstName() + " " + doctor.getLastName());
            doctorName.setLayoutX(10.0);
            doctorName.setLayoutY(8.0);
            doctorName.setTextFill(Color.web("#5e9f5a"));
            doctorName.setFont(new Font("System Bold", 14.0));

            Label doctorInfo = new Label(i == 0 ? "General Physician" : i == 1 ? "Cardiologist" : "Neurologist");
            doctorInfo.setLayoutX(10.0);
            doctorInfo.setLayoutY(25.0);
            doctorInfo.setTextFill(Color.web("#ffffff"));
            doctorInfo.setFont(new Font("System", 10.0));

            Button viewProfileBtn = new Button("Profil");
            viewProfileBtn.setLayoutX(320.0);
            viewProfileBtn.setLayoutY(10.0);
            viewProfileBtn.setMnemonicParsing(false);
            viewProfileBtn.setPrefHeight(25.0);
            viewProfileBtn.setPrefWidth(80.0);
            viewProfileBtn.setStyle("-fx-background-radius: 15; -fx-background-color: #5e9f5a;");
            viewProfileBtn.setTextFill(Color.WHITE);
            viewProfileBtn.setFont(new Font("System Bold", 10.0));
            viewProfileBtn.setOnMouseClicked(e -> {
                showDoctorProfileInPanel(doctor);
            });

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
            appointmentPane.setLayoutY(5.0 + (i * 50));
            appointmentPane.setPrefHeight(45.0);
            appointmentPane.setPrefWidth(454.0);
            appointmentPane.setStyle("-fx-background-color: #232323; -fx-border-color: #5e9f5a; -fx-border-radius: 5; -fx-background-radius: 5;");

            Label doctorName = new Label(app.getDoctor().getFirstName() + " " + app.getDoctor().getLastName());
            doctorName.setLayoutX(10.0);
            doctorName.setLayoutY(8.0);
            doctorName.setTextFill(Color.web("#5e9f5a"));
            doctorName.setFont(new Font("System Bold", 14.0));

            Label appointmentInfo = new Label("Datum: " + app.getDate() + " | Uhrzeit: " + app.getTime());

            appointmentInfo.setLayoutX(10.0);
            appointmentInfo.setLayoutY(25.0);
            appointmentInfo.setTextFill(Color.web("#ffffff"));
            appointmentInfo.setFont(new Font("System", 10.0));

            Button cancelBtn = new Button("Stornieren");
            cancelBtn.setLayoutX(370.0);
            cancelBtn.setLayoutY(10.0);
            cancelBtn.setMnemonicParsing(false);
            cancelBtn.setPrefHeight(25.0);
            cancelBtn.setPrefWidth(70.0);
            cancelBtn.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 5; -fx-background-radius: 15;");
            cancelBtn.setTextFill(Color.web("#5e9f5a"));
            cancelBtn.setFont(new Font("System Bold", 10.0));
            cancelBtn.setOnMouseClicked(e -> {
                String response = SingletonAppointmentSystem.getInstance().cancelAppointment(app);
                if (response.equals("Erfolgreich")) {
                    loadAppointmentsList();
                }
            });


            appointmentPane.getChildren().addAll(doctorName, appointmentInfo, cancelBtn);
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

            // Formular zurücksetzen
            specializationComboBox.setValue(null);
            doctorComboBox.setItems(FXCollections.observableArrayList());
            dateComboBox.setItems(FXCollections.observableArrayList());
            timeComboBox.setItems(FXCollections.observableArrayList());

            // Arztprofil-Bereich leeren
            if (doctorProfilePane != null) {
                doctorProfilePane.getChildren().clear();
            }

            // Terminliste aktualisieren
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

        // Doctor Name
        Label nameLabel = new Label("Dr. " + doctor.getFirstName() + " " + doctor.getLastName());
        nameLabel.setLayoutX(10.0);
        nameLabel.setLayoutY(10.0);
        nameLabel.setTextFill(Color.web("#5e9f5a"));
        nameLabel.setFont(new Font("System Bold", 17.0));

        // Username
        Label usernameLabel = new Label("Benutzername: " + doctor.getUsername());
        usernameLabel.setLayoutX(10.0);
        usernameLabel.setLayoutY(70.0);
        usernameLabel.setTextFill(Color.web("#ffffff"));
        usernameLabel.setFont(new Font("System Bold", 12.0));

        // Email
        Label emailLabel = new Label("E-Mail: " + doctor.getEmail());
        emailLabel.setLayoutX(10.0);
        emailLabel.setLayoutY(95.0);
        emailLabel.setTextFill(Color.web("#ffffff"));
        emailLabel.setFont(new Font("System Bold", 12.0));

        // Gender
        Label genderLabel = new Label("Geschlecht: " + doctor.getGender());
        genderLabel.setLayoutX(10.0);
        genderLabel.setLayoutY(120.0);
        genderLabel.setTextFill(Color.web("#ffffff"));
        genderLabel.setFont(new Font("System Bold", 12.0));

        // DOB
        Label dobLabel = new Label("Geburtsdatum: " + new SimpleDateFormat("MM-dd-yyyy").format(doctor.getDob()));
        dobLabel.setLayoutX(10.0);
        dobLabel.setLayoutY(145.0);
        dobLabel.setTextFill(Color.web("#ffffff"));
        dobLabel.setFont(new Font("System Bold", 12.0));


        // Specialization (based on index)
        int doctorIndex = SingletonAppointmentSystem.getInstance().getUsers("Doctor").indexOf(doctor);
        String specializ = doctorIndex == 0 ? "Allgemeinmediziner" :
                doctorIndex == 1 ? "Kardiologe" : "Neurologe";
        Label specializationLabel = new Label("Fachrichtung: " + specializ);
        specializationLabel.setLayoutX(10.0);
        specializationLabel.setLayoutY(45.0);
        specializationLabel.setFont(new Font("System Bold", 12.0));
        specializationLabel.setTextFill(Color.web("#ffffff"));

        doctorProfilePane.getChildren().addAll(
                nameLabel, usernameLabel, emailLabel,
                genderLabel, dobLabel, specializationLabel
        );
    }


    private void showEditProfileInPanel() {
        if (doctorProfilePane == null) return;

        doctorProfilePane.getChildren().clear();

        // Title
        Label titleLabel = new Label("Profil bearbeiten");
        titleLabel.setLayoutX(10.0);
        titleLabel.setLayoutY(10.0);
        titleLabel.setTextFill(Color.web("#5e9f5a"));
        titleLabel.setFont(new Font("System Bold", 16.0));

        // First Name
        Label firstNameLabel = new Label("Vorname:");
        firstNameLabel.setLayoutX(10.0);
        firstNameLabel.setLayoutY(50.0);
        firstNameLabel.setTextFill(Color.web("#ffffff"));
        firstNameLabel.setFont(new Font("System Bold", 12.0));

        TextField firstNameField = new TextField(user.getFirstName());
        firstNameField.setLayoutX(120.0);
        firstNameField.setLayoutY(45.0);
        firstNameField.setPrefHeight(25.0);
        firstNameField.setPrefWidth(200.0);
        firstNameField.setStyle("-fx-background-color: white; -fx-border-color: #343e7c; -fx-background-radius: 5; -fx-border-radius: 5;");

        // Last Name
        Label lastNameLabel = new Label("Nachname:");
        lastNameLabel.setLayoutX(10.0);
        lastNameLabel.setLayoutY(85.0);
        lastNameLabel.setTextFill(Color.web("#ffffff"));
        lastNameLabel.setFont(new Font("System Bold", 12.0));

        TextField lastNameField = new TextField(user.getLastName());
        lastNameField.setLayoutX(120.0);
        lastNameField.setLayoutY(80.0);
        lastNameField.setPrefHeight(25.0);
        lastNameField.setPrefWidth(200.0);
        lastNameField.setStyle("-fx-background-color: white; -fx-border-color: #343e7c; -fx-background-radius: 5; -fx-border-radius: 5;");

        // Email
        Label emailLabel = new Label("E-Mail:");
        emailLabel.setLayoutX(10.0);
        emailLabel.setLayoutY(120.0);
        emailLabel.setTextFill(Color.web("#ffffff"));
        emailLabel.setFont(new Font("System Bold", 12.0));

        TextField emailField = new TextField(user.getEmail());
        emailField.setLayoutX(120.0);
        emailField.setLayoutY(115.0);
        emailField.setPrefHeight(25.0);
        emailField.setPrefWidth(200.0);
        emailField.setStyle("-fx-background-color: white; -fx-border-color: #343e7c; -fx-background-radius: 5; -fx-border-radius: 5;");

        // DOB
        Label dobLabel = new Label("Geburtsdatum:");
        dobLabel.setLayoutX(10.0);
        dobLabel.setLayoutY(155.0);
        dobLabel.setTextFill(Color.web("#ffffff"));
        dobLabel.setFont(new Font("System Bold", 12.0));

        DatePicker dobPicker = new DatePicker();
        dobPicker.setLayoutX(120.0);
        dobPicker.setLayoutY(150.0);
        dobPicker.setPrefHeight(25.0);
        dobPicker.setPrefWidth(200.0);
        dobPicker.setValue(user.getDob().toLocalDate());
        dobPicker.setStyle("-fx-background-color: white; -fx-border-color: #343e7c; -fx-background-radius: 5; -fx-border-radius: 5;");

        // Save Button
        Button saveBtn = new Button("Speichern");
        saveBtn.setLayoutX(120.0);
        saveBtn.setLayoutY(190.0);
        saveBtn.setPrefHeight(30.0);
        saveBtn.setPrefWidth(120.0);
        saveBtn.setStyle("-fx-background-color: #5e9f5a; -fx-background-radius: 5; -fx-border-radius: 5;");
        saveBtn.setTextFill(Color.WHITE);
        saveBtn.setFont(new Font("System Bold", 9.0));
        saveBtn.setOnMouseClicked(e -> {
            saveProfileChanges(firstNameField.getText(), lastNameField.getText(),
                    emailField.getText(), dobPicker.getValue());
        });

        // Cancel Button
        Button cancelBtn = new Button("Abbrechen");
        cancelBtn.setLayoutX(250.0);
        cancelBtn.setLayoutY(190.0);
        cancelBtn.setPrefHeight(30.0);
        cancelBtn.setPrefWidth(70.0);
        cancelBtn.setStyle("-fx-background-color: #d50830; -fx-background-radius: 5; -fx-border-radius: 5;");
        cancelBtn.setTextFill(Color.WHITE);
        cancelBtn.setFont(new Font("System Bold", 9.0));
        cancelBtn.setOnMouseClicked(e -> {
            // Show current profile in panel
            showCurrentProfileInPanel();
        });

        doctorProfilePane.getChildren().addAll(
                titleLabel, firstNameLabel, firstNameField, lastNameLabel, lastNameField,
                emailLabel, emailField, dobLabel, dobPicker, saveBtn, cancelBtn
        );
    }




    private void saveProfileChanges(String firstName, String lastName, String email, java.time.LocalDate dob) {
        String response = SingletonAppointmentSystem.getInstance().editProfile(
                user.getId(), firstName, lastName, java.sql.Date.valueOf(dob), email
        );

        if (response.equals("Success")) {
            // Update the user object
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setDob(java.sql.Date.valueOf(dob));

            // Refresh dashboard data
            refreshDashboardData();

            // Show success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Profile Updated");
            alert.setContentText("Your profile has been successfully updated!");
            alert.showAndWait();

            // Show current profile in panel
            showCurrentProfileInPanel();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Update Failed");
            alert.setContentText(response);
            alert.showAndWait();
        }
    }



    private void refreshDashboardData() {
        // Refresh profile information
        firstNameLabel1.setText(user.getFirstName());
        lastNameLabel1.setText(user.getLastName());
        emailLabel1.setText(user.getEmail());
        dobLabel1.setText(new SimpleDateFormat("MM-dd-yyyy").format(user.getDob()));

    }

    private void showCurrentProfileInPanel() {
        if (doctorProfilePane == null) return;

        doctorProfilePane.getChildren().clear();

        // Show current user profile in the middle panel
        Label titleLabel = new Label("Mein Profil");
        titleLabel.setLayoutX(10.0);
        titleLabel.setLayoutY(10.0);
        titleLabel.setTextFill(Color.web("#5e9f5a"));
        titleLabel.setFont(new Font("System Bold", 16.0));

        // Profile details
        Label nameLabel = new Label("Name: " + user.getFirstName() + " " + user.getLastName());
        nameLabel.setLayoutX(10.0);
        nameLabel.setLayoutY(40.0);
        nameLabel.setTextFill(Color.web("#ffffff"));
        nameLabel.setFont(new Font("System Bold", 12.0));

        Label emailLabel = new Label("E-Mail: " + user.getEmail());
        emailLabel.setLayoutX(10.0);
        emailLabel.setLayoutY(65.0);
        emailLabel.setTextFill(Color.web("#ffffff"));
        emailLabel.setFont(new Font("System", 12.0));

        Label dobLabel = new Label("Geburtsdatum:: " + new SimpleDateFormat("MM-dd-yyyy").format(user.getDob()));
        dobLabel.setLayoutX(10.0);
        dobLabel.setLayoutY(90.0);
        dobLabel.setTextFill(Color.web("#ffffff"));
        dobLabel.setFont(new Font("System", 12.0));

        Label genderLabel = new Label("Geschlecht: " + user.getGender());
        genderLabel.setLayoutX(10.0);
        genderLabel.setLayoutY(115.0);
        genderLabel.setTextFill(Color.web("#ffffff"));
        genderLabel.setFont(new Font("System", 12.0));

        Label roleLabel = new Label("Rolle: " + user.getRole());
        roleLabel.setLayoutX(10.0);
        roleLabel.setLayoutY(140.0);
        roleLabel.setTextFill(Color.web("#ffffff"));
        roleLabel.setFont(new Font("System", 12.0));

        doctorProfilePane.getChildren().addAll(
                titleLabel, nameLabel, emailLabel, dobLabel, genderLabel, roleLabel
        );
    }

    @FXML
    private void handleLogoutClicked(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(AppointmentSystem.class.getResource("/org.example.arzttermin/View/login.fxml"));
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
