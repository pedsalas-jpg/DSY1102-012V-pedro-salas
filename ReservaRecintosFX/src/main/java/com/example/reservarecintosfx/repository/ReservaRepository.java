package com.example.reservarecintosfx.repository;

import com.example.reservarecintosfx.model.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class ReservaRepository {
    private final ObservableList<Reserva> reservas = FXCollections.observableArrayList();

    public ObservableList<Reserva> obtenerTodas() { return reservas; }

    public boolean estaOcupado(String recinto, String hora) {
        return reservas.stream().anyMatch(r ->
                r.getRecinto().equalsIgnoreCase(recinto.trim())
                        && r.getHora().equals(hora));
    }

    public void registrar(Reserva reserva) { reservas.add(reserva); }
}
