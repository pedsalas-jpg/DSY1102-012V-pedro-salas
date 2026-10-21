module com.example.reservarecintosfx {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.reservarecintosfx to javafx.fxml;
    exports com.example.reservarecintosfx;

    // Estos permisos permiten a JavaFX acceder a tus controladores y datos
    opens com.example.reservarecintosfx.controller to javafx.fxml;
    exports com.example.reservarecintosfx.controller;
    exports com.example.reservarecintosfx.model;
}