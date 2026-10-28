package com.example.nuevoproyecto;

import com.example.nuevoproyecto.model.MensajeFuturo;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class HelloController {

    @FXML
    private TableView<MensajeFuturo> tablaMensajes;

    @FXML
    private TableColumn<MensajeFuturo, String> colAutor;

    @FXML
    private TableColumn<MensajeFuturo, String> colTexto;

    @FXML
    private TableColumn<MensajeFuturo, String> colPlazo;
    @FXML
    private final ObservableList<MensajeFuturo> mensajes = FXCollections.observableArrayList();

    @FXML
    protected void initialize() {

        colAutor.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getAutor()));

        colTexto.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getTexto()));

        colPlazo.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getPlazoAnios() + " años"));

        tablaMensajes.setItems(mensajes);

        mensajes.add(new MensajeFuturo("Valentina", "Nunca dejes de perseguir tus sueños.", 5));
        mensajes.add(new MensajeFuturo("Carlos", "Recuerda de dónde comenzaste.", 10));
    }
}

