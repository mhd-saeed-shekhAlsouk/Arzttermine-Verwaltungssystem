package org.example.arzttermin;

import org.example.arzttermin.Model.SingletonAppointmentSystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;

public class AppointmentSystem extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SingletonAppointmentSystem.getInstance();

        FXMLLoader fxmlLoader = new FXMLLoader(AppointmentSystem.class.getResource("/org.example.arzttermin/View/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 800);
        stage.setTitle("PraxPlan");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}