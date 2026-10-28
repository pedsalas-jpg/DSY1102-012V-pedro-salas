module com.example.nuevoproyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.nuevoproyecto to javafx.fxml;
    exports com.example.nuevoproyecto;
}