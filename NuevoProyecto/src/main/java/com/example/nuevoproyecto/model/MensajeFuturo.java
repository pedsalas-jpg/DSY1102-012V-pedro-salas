package com.example.nuevoproyecto.model;

import javafx.application.Application;

public class MensajeFuturo {
    private String autor;
    private String texto;
    private int plazoAnios;

    public MensajeFuturo(String autor,
                         String texto, int plazoAnios) {
        this.autor = autor;
        this.texto = texto;
        this.plazoAnios = plazoAnios;
    }
    // A continuación, los getters
    public String getAutor() {
        return autor;
    }

    public String getTexto() {
        return texto;
    }

    public int getPlazoAnios() {
        return plazoAnios;
    }
}

