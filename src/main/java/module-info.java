module org.example.arzttermin {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires javafx.graphics;
    requires jbcrypt;

    opens org.example.arzttermin to javafx.fxml;
    opens org.example.arzttermin.ViewModel to javafx.fxml;
    exports org.example.arzttermin.ViewModel;
    exports org.example.arzttermin;
}