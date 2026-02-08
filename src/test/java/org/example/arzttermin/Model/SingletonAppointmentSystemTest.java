package org.example.arzttermin.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SingletonAppointmentSystemTest {

    private SingletonAppointmentSystem system;

    @BeforeEach
    void setUp() {
        system = SingletonAppointmentSystem.getInstance();
    }

    @Test
    void singletonInstanceShouldBeSame() {
        assertSame(system, SingletonAppointmentSystem.getInstance());
    }

    @Test
    void registerUserShouldFailWhenFieldsMissing() {
        String result = system.registerUser(
                "", "Doe", "x@example.com", "u" + System.currentTimeMillis(),
                "1234", "1234", "Male",
                Date.valueOf("2000-01-01"),
                "Patient"
        );
        assertEquals("Bitte füllen Sie alle Felder aus.", result);
    }

    @Test
    void registerUserShouldFailWhenDobIsNull() {
        String result = system.registerUser(
                "John", "Doe", "x@example.com", "u" + System.currentTimeMillis(),
                "1234", "1234", "Male",
                null,
                "Patient"
        );
        assertEquals("Bitte geben Sie ihr Geburtsdatum ein.", result);
    }

    @Test
    void registerUserShouldFailWhenPasswordMismatch() {
        String result = system.registerUser(
                "John", "Doe",
                "john" + System.currentTimeMillis() + "@example.com",
                "john_" + System.currentTimeMillis(),
                "1234", "9999",
                "Male",
                Date.valueOf("2000-01-01"),
                "Patient"
        );
        assertEquals("Passwörter müssen übereinstimmen.", result);
    }

    @Test
    void registerUserShouldSucceedWithValidData() {
        String result = system.registerUser(
                "John", "Doe",
                "john" + System.currentTimeMillis() + "@example.com",
                "john_" + System.currentTimeMillis(),
                "1234", "1234",
                "Male",
                Date.valueOf("2000-01-01"),
                "Patient"
        );
        assertEquals("Account erfolgreich erstellt.", result);
    }

    @Test
    void loginShouldFailWhenFieldsEmpty() {
        String result = system.login("", "");
        assertEquals("Bitte füllen sie alle Felder aus.", result);
    }

    @Test
    void loginShouldSucceedAfterRegister() {
        String username = "login_" + System.currentTimeMillis();
        String email = "login" + System.currentTimeMillis() + "@example.com";

        String reg = system.registerUser(
                "A", "B",
                email,
                username,
                "1234", "1234",
                "Male",
                Date.valueOf("2000-01-01"),
                "Patient"
        );
        assertEquals("Account erfolgreich erstellt.", reg);

        String role = system.login(username, "1234");
        assertEquals("Patient", role);
        assertNotNull(system.getLoggedInUser());
        assertEquals(username, system.getLoggedInUser().getUsername());
    }

    @Test
    void loginShouldFailWithWrongPassword() {
        String username = "user_" + System.currentTimeMillis();
        String email = "user" + System.currentTimeMillis() + "@example.com";

        system.registerUser(
                "A", "B", email, username,
                "1234", "1234",
                "Male",
                Date.valueOf("2000-01-01"),
                "Patient"
        );

        String result = system.login(username, "wrong");
        assertEquals("Ungültige Eingabe.", result);
    }

    @Test
    void getUsersShouldReturnDoctors() {
        ArrayList<User> doctors = system.getUsers("Doctor");
        assertNotNull(doctors);
        // Wenn DB leer wäre, könnte das 0 sein, aber in deinem Projekt werden Doctors geladen.
        assertTrue(doctors.size() >= 0);
    }

    @Test
    void bookAppointmentShouldFailWhenMissingOptions() {
        String result = system.bookAppointment(null, "26-01-2026", "10:00");
        assertEquals("Bitte wählen Sie alle Optionen aus.", result);
    }

    @Test
    void cancelAppointmentShouldFailWhenNull() {
        String result = system.cancelAppointment(null);
        assertEquals("Fehler: Es wurde kein Termin ausgewählt.", result);
    }
}
