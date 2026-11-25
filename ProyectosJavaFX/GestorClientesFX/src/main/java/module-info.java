module org.example.gestorclientesfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.example.gestorclientesfx to javafx.fxml;
    opens org.example.gestorclientesfx.controller to javafx.fxml;
    opens org.example.gestorclientesfx.model to javafx.base;

    exports org.example.gestorclientesfx;
}

