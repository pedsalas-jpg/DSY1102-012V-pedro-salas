package com.example.reservarecintosfx.controller;

import com.example.reservarecintosfx.model.Reserva;
import com.example.reservarecintosfx.repository.ReservaRepository;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FormController {
    @FXML private TextField campoResponsable;
    @FXML private ComboBox<String> selectorRecinto;
    @FXML private ComboBox<String> selectorHora;
    @FXML private Label mensajeError;
    private ReservaRepository repositorio;
    private Runnable volverAlListado;

    @FXML private void initialize() {
        selectorRecinto.getItems().addAll(
                "Cancha fútbol 1", "Cancha fútbol 2", "Gimnasio");
        for (int h = 8; h < 22; h++) {
            selectorHora.getItems().add(String.format("%02d:00", h));
        }
    }

    public void configurar(ReservaRepository repositorio, Runnable volverAlListado) {
        this.repositorio = repositorio;
        this.volverAlListado = volverAlListado;
    }

    @FXML private void guardar() {
        String responsable = campoResponsable.getText().trim();
        String recinto = selectorRecinto.getValue();
        String hora = selectorHora.getValue();

        if (responsable.isBlank()) {
            mensajeError.setText("Ingresa un responsable");
            return;
        }
        if (recinto == null || hora == null) {
            mensajeError.setText("Selecciona recinto y horario");
            return;
        }
        if (repositorio.estaOcupado(recinto, hora)) {
            mensajeError.setText("El recinto ya está reservado a esa hora");
            return;
        }
        repositorio.registrar(new Reserva(responsable, recinto, hora));
        volverAlListado.run();
    }

    @FXML private void volver() { volverAlListado.run(); }
}
