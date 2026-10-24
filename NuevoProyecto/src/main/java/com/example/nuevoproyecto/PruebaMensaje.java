package com.example.nuevoproyecto;

import com.example.nuevoproyecto.model.MensajeFuturo;

import java.util.ArrayList;
import java.util.List;

public class PruebaMensaje {
    static List<MensajeFuturo>mensajeFuturo;
    public static void main(String[] args) {

        mensajeFuturo = new ArrayList<>();

        mensajeFuturo.add(new MensajeFuturo(
                "pedro",
                "exito.",
                5
        ));

        for (MensajeFuturo mensajeFuturo : mensajeFuturo) {
            System.out.println("Autor: " + mensajeFuturo.getAutor());
            System.out.println("Mensaje: " + mensajeFuturo.getTexto());
            System.out.println("Leer dentro de: "
                    + mensajeFuturo.getPlazoAnios() + " años");


        }
    }
}
