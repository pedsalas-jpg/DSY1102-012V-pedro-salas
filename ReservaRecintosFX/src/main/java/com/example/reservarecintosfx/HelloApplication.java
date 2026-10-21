package com.example.reservarecintosfx;

import com.example.reservarecintosfx.controller.FormController;
import com.example.reservarecintosfx.controller.ListaController;
import com.example.reservarecintosfx.repository.ReservaRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private final ReservaRepository repositorio = new ReservaRepository();
    private Stage stage;

    @Override public void start(Stage stage) throws IOException {
        this.stage = stage;
        stage.setTitle("Arriendo de recintos deportivos");
        mostrarListado();
        stage.show();
    }

    private void mostrarListado() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                HelloApplication.class.getResource("lista-view.fxml"));
        Parent root = loader.load();
        ListaController controller = loader.getController();
        controller.configurar(repositorio, this::irAFormulario);
        stage.setScene(new Scene(root));
    }

    private void mostrarFormulario() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                HelloApplication.class.getResource("form-view.fxml"));
        Parent root = loader.load();
        FormController controller = loader.getController();
        controller.configurar(repositorio, this::irAListado);
        stage.setScene(new Scene(root));
    }

    private void irAFormulario() {
        try { mostrarFormulario(); }
        catch (IOException e) { throw new IllegalStateException("Carga formulario", e); }
    }

    private void irAListado() {
        try { mostrarListado(); }
        catch (IOException e) { throw new IllegalStateException("Carga listado", e); }
    }

    public static void main(String[] args) { launch(args); }
}