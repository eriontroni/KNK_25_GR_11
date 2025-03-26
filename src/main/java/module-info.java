module java {
    requires javafx.controls;
    requires javafx.fxml;

    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.knk_25 to javafx.fxml;
    exports com.example.knk_25;
}