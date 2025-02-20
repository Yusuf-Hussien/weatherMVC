module com.example.weather {
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;
    requires javafx.web;
    requires java.net.http;

    opens com.example.weather to javafx.fxml;
    exports com.example.weather;
}