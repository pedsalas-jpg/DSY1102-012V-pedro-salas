package com.example.reservarecintosfx.model;

public final class Reserva {
    private final String responsable;
    private final String recinto;
    private final String hora;

    public Reserva(String responsable, String recinto, String hora) {
        this.responsable = responsable;
        this.recinto = recinto;
        this.hora = hora;
    }

    public String getResponsable() { return responsable; }
    public String getRecinto() { return recinto; }
    public String getHora() { return hora; }
}
