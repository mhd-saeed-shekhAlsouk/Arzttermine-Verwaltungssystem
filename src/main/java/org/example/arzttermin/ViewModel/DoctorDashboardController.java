package org.example.arzttermin.ViewModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.arzttermin.AppointmentSystem;
import org.example.arzttermin.Model.Appointment;
import org.example.arzttermin.Model.SingletonAppointmentSystem;
import org.example.arzttermin.Model.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DoctorDashboardController {

    @FXML private AnchorPane rootPane;
    @FXML private AnchorPane patientInfoPane;
    @FXML private AnchorPane appointmentsPane;

    @FXML private Label doctorNameLabel;
    @FXML private Label doctorEmailLabel;
    @FXML private Label specializationLabel;
    @FXML private Label genderLabel;
    @FXML private Label dobLabel;

    @FXML private TextField nameFilterField;
    @FXML private DatePicker dateFilterPicker;

    private User doctor;
    private User selectedPatient;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /* ===================== INITIALIZATION ===================== */

    @FXML
    public void initialize() {
        nameFilterField.textProperty().addListener((o, a, b) -> filterAppointments());
        dateFilterPicker.valueProperty().addListener((o, a, b) -> filterAppointments());
    }

    public void loadDetails(User doctorUser, AnchorPane pane) {
        this.doctor = doctorUser;
        loadDoctorProfile();
        showDefaultPatientInfo();
        filterAppointments();
    }

    /* ===================== DOCTOR PROFILE ===================== */
    private void loadDoctorProfile() {
        doctorNameLabel.setText("Dr. " + doctor.getFirstName() + " " + doctor.getLastName());
        doctorEmailLabel.setText(doctor.getEmail());
        genderLabel.setText(doctor.getGender());
        dobLabel.setText(new SimpleDateFormat("dd-MM-yyyy").format(doctor.getDob()));

        int index = SingletonAppointmentSystem.getInstance()
                .getUsers("Doctor").indexOf(doctor);

        specializationLabel.setText(
                index == 0 ? "Allgemeinmediziner" :
                        index == 1 ? "Kardiologe" : "Neurologe"
        );
    }

    /* ===================== APPOINTMENTS ===================== */

    private void filterAppointments() {
        List<Appointment> filtered = SingletonAppointmentSystem.getInstance()
                .getAppointments()
                .stream()
                .filter(this::matchesFilter)
                .toList();

        showAppointments(filtered);
    }

    private boolean matchesFilter(Appointment app) {
        String name = nameFilterField.getText().toLowerCase();

        boolean matchesName =
                name.isEmpty() ||
                        app.getPatient().getFirstName().toLowerCase().contains(name) ||
                        app.getPatient().getLastName().toLowerCase().contains(name);

        LocalDate selectedDate = dateFilterPicker.getValue();
        if (selectedDate == null) return matchesName;

        try {
            LocalDate appointmentDate =
                    LocalDate.parse(app.getDate(), FORMATTER);
            return matchesName && appointmentDate.equals(selectedDate);
        } catch (Exception e) {
            return false;
        }
    }

    private void showAppointments(List<Appointment> appointments) {
        appointmentsPane.getChildren().clear();

        if (appointments.isEmpty()) {
            appointmentsPane.getChildren().add(
                    createLabel("Keine Termine gefunden", 10, 20, 12, "#ffffff", false)
            );
            return;
        }

        for (int i = 0; i < appointments.size(); i++) {
            appointmentsPane.getChildren().add(
                    createAppointmentPane(appointments.get(i), i)
            );
        }
    }

    private AnchorPane createAppointmentPane(Appointment app, int index) {
        AnchorPane pane = new AnchorPane();
        pane.setLayoutX(5);
        pane.setLayoutY(5 + index * 75);
        pane.setPrefSize(350, 70);
        pane.setStyle("""
                -fx-background-color: #232323;
                -fx-border-color: #5e9f5a;
                -fx-border-radius: 5;
                -fx-background-radius: 5;
                """);

        Label name = createLabel(
                "Patient: " + app.getPatient().getFirstName() + " " + app.getPatient().getLastName(),
                10, 5, 15, "#5e9f5a", true
        );

        Label dateTime = createLabel(
                "Datum: " + app.getDate() + " | Zeit: " + app.getTime(),
                10, 24, 11, "#ffffff", false
        );

        Button profileBtn = createButton(
                "Profil", 170, 35, "#5e9f5a",
                e -> showPatientProfile(app.getPatient())
        );

        Button cancelBtn = createButton(
                "Stornieren", 255, 35, "#dc3545",
                e -> cancelAppointment(app)
        );

        pane.getChildren().addAll(name, dateTime, profileBtn, cancelBtn);
        return pane;
    }

    /* ===================== PATIENT PROFILE ===================== */

    private void showPatientProfile(User patient) {
        patientInfoPane.getChildren().clear();
        selectedPatient = patient;

        patientInfoPane.getChildren().addAll(
                createLabel("Patient Profil", 10, 10, 18, "#5e9f5a", true),
                createLabel("Name: " + patient.getFirstName() + " " + patient.getLastName(), 10, 40, 14, "#ffffff", false),
                createLabel("E-Mail: " + patient.getEmail(), 10, 65, 14, "#ffffff", false),
                createLabel("Geschlecht: " + patient.getGender(), 10, 90, 14, "#ffffff", false),
                createLabel("Geburtsdatum: " +
                        new SimpleDateFormat("dd-MM-yyyy").format(patient.getDob()), 10, 115, 14, "#ffffff", false),
                createLabel("Rolle: " + patient.getRole(), 10, 140, 14, "#ffffff", false)
        );
    }

    private void showDefaultPatientInfo() {
        patientInfoPane.getChildren().clear();
        patientInfoPane.getChildren().addAll(
                createLabel("Patient Information", 10, 10, 18, "#5e9f5a", true),
                createLabel("Wählen Sie einen Termin aus, um Patientdaten zu sehen",
                        10, 50, 12, "#ffffff", false)
        );
    }

    /* ===================== APPOINTMENT CANCEL ===================== */

    private void cancelAppointment(Appointment appointment) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Möchten Sie diesen Termin wirklich stornieren?",
                ButtonType.OK, ButtonType.CANCEL);

        confirm.showAndWait().ifPresent(btn -> {
            if (btn == ButtonType.OK) {
                String result = SingletonAppointmentSystem.getInstance()
                        .cancelAppointment(appointment);

                Alert alert = result.equals("Erfolgreich")
                        ? new Alert(Alert.AlertType.INFORMATION, "Termin wurde storniert")
                        : new Alert(Alert.AlertType.ERROR, result);

                alert.showAndWait();
                filterAppointments();
            }
        });
    }

    /* ===================== LOGOUT ===================== */

    @FXML
    private void handleLogoutClicked() {
        try {
            Parent root = FXMLLoader.load(
                    AppointmentSystem.class.getResource(
                            "/org.example.arzttermin/View/login.fxml"));
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(new Scene(root, 1600, 920));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* ===================== UI HELPERS ===================== */

    private Label createLabel(String text, double x, double y, int size, String color, boolean bold) {
        Label label = new Label(text);
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setFont(new Font(bold ? "System Bold" : "System", size));
        label.setTextFill(Color.web(color));
        return label;
    }

    private Button createButton(String text, double x, double y, String color,
                                javafx.event.EventHandler<javafx.scene.input.MouseEvent> action) {
        Button btn = new Button(text);
        btn.setLayoutX(x);
        btn.setLayoutY(y);
        btn.setPrefSize(70, 18);
        btn.setStyle("-fx-background-color: " + color + "; -fx-background-radius: 5;");
        btn.setTextFill(Color.WHITE);
        btn.setFont(new Font("System Bold", 11));
        btn.setOnMouseClicked(action);
        return btn;
    }
}
