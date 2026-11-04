module com.example.nuevoproyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    // Permitir a JavaFX acceder a las vistas y controladores
    opens com.example.nuevoproyecto to javafx.fxml;
    opens com.example.nuevoproyecto.model to javafx.fxml, com.fasterxml.jackson.databind;
    opens com.example.nuevoproyecto.dao to javafx.fxml, com.fasterxml.jackson.databind;

    // Exportar paquetes para el correcto funcionamiento del proyecto
    exports com.example.nuevoproyecto;
    exports com.example.nuevoproyecto.model;
    exports com.example.nuevoproyecto.dao;
}
