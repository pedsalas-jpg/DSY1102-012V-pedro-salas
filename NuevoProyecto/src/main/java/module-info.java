module com.example.nuevoproyecto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.nuevoproyecto to javafx.fxml;
    exports com.example.nuevoproyecto;
}