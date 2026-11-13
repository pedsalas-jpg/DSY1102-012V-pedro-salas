package com.example.nuevoproyecto.dao;

import com.example.nuevoproyecto.model.MensajeFuturo;
import java.io.IOException;
import java.util.List;

public interface MensajeDao {
    List<MensajeFuturo> listar() throws IOException;
    void guardar(List<MensajeFuturo> mensajes) throws IOException;
}
