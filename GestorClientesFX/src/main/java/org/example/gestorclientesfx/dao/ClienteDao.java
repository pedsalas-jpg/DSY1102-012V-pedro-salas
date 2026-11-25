package org.example.gestorclientesfx.dao;

import org.example.gestorclientesfx.model.Cliente;
import java.util.List;

public interface ClienteDao {
    List<Cliente> listarTodos();
    void insertar(Cliente cliente);
    void actualizar(Cliente cliente);
    void eliminar(int id);
}

