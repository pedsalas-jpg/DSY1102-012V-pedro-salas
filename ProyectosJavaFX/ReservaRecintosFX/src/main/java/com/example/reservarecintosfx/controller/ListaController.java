package com.example.reservarecintosfx.controller;

import com.example.reservarecintosfx.model.Reserva;
import com.example.reservarecintosfx.repository.ReservaRepository;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListaController {
    @FXML private TableView<Reserva> tablaReservas;
    @FXML private TableColumn<Reserva, String> colResponsable;
    @FXML private TableColumn<Reserva, String> colRecinto;
    @FXML private TableColumn<Reserva, String> colHora;
    private Runnable abrirFormulario;

    public void configurar(ReservaRepository repositorio, Runnable abrirFormulario) {
        this.abrirFormulario = abrirFormulario;
        colResponsable.setCellValueFactory(c ->
                new ReadOnlyStringWrapper(c.getValue().getResponsable()));
        colRecinto.setCellValueFactory(c ->
                new ReadOnlyStringWrapper(c.getValue().getRecinto()));
        colHora.setCellValueFactory(c ->
                new ReadOnlyStringWrapper(c.getValue().getHora()));
        tablaReservas.setItems(repositorio.obtenerTodas());
    }

    @FXML private void abrirFormulario() { abrirFormulario.run(); }
}
