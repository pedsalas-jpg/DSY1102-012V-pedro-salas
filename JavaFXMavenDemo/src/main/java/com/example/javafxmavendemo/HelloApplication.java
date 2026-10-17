package com.example.javafxmavendemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Esta línea es la magia: busca tu archivo hello-view.fxml y lo carga
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        // Creamos la escena usando lo que cargó el FXML
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);

        stage.setTitle("Mi app con Scene Builder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}