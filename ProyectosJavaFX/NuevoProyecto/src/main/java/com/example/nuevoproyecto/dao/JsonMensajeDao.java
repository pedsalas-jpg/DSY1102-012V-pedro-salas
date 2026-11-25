package com.example.nuevoproyecto.dao;

import com.example.nuevoproyecto.model.MensajeFuturo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonMensajeDao implements MensajeDao {

    private final Path archivo;
    private final ObjectMapper mapper = new ObjectMapper();

    public JsonMensajeDao(Path archivo) {
        this.archivo = archivo;
    }

    @Override
    public List<MensajeFuturo> listar() throws IOException {
        if (Files.notExists(archivo)) {
            return new ArrayList<>();
        }
        return mapper.readValue(archivo.toFile(), new TypeReference<List<MensajeFuturo>>() {});
    }

    @Override
    public void guardar(List<MensajeFuturo> mensajes) throws IOException {
        Path directorio = archivo.toAbsolutePath().getParent();
        if (directorio != null) {
            Files.createDirectories(directorio);
        }
        mapper.writerWithDefaultPrettyPrinter().writeValue(archivo.toFile(), mensajes);
    }
}


