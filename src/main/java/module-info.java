module java {
    requires javafx.controls;
    requires javafx.fxml;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;



    opens App to javafx.graphics, javafx.fxml;
    opens Controllers to javafx.fxml;
    opens Models to javafx.base;

    exports App;
}