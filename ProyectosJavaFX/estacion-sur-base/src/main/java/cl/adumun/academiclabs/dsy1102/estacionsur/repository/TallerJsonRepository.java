package cl.adumun.academiclabs.dsy1102.estacionsur.repository;

import cl.adumun.academiclabs.dsy1102.estacionsur.model.Taller;
import cl.adumun.academiclabs.dsy1102.estacionsur.model.TallerPresencial;
import cl.adumun.academiclabs.dsy1102.estacionsur.model.TallerVirtual;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TallerJsonRepository implements TallerRepository {

    private final File archivoPresenciales = new File("talleres_presenciales.json");
    private final File archivoVirtuales = new File("talleres_virtuales.json");
    private final ObjectMapper mapper;

    public TallerJsonRepository() {
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public List<Taller> cargar() throws IOException {
        List<Taller> todos = new ArrayList<>();

        if (archivoPresenciales.exists() && archivoPresenciales.length() > 0) {
            List<TallerPresencial> presenciales = mapper.readValue(
                    archivoPresenciales, new TypeReference<List<TallerPresencial>>() {});
            todos.addAll(presenciales);
        }

        if (archivoVirtuales.exists() && archivoVirtuales.length() > 0) {
            List<TallerVirtual> virtuales = mapper.readValue(
                    archivoVirtuales, new TypeReference<List<TallerVirtual>>() {});
            todos.addAll(virtuales);
        }

        return todos;
    }

    @Override
    public void guardar(List<Taller> talleres) throws IOException {
        List<TallerPresencial> presenciales = new ArrayList<>();
        List<TallerVirtual> virtuales = new ArrayList<>();

        for (Taller t : talleres) {
            if (t instanceof TallerPresencial tp) {
                presenciales.add(tp);
            } else if (t instanceof TallerVirtual tv) {
                virtuales.add(tv);
            }
        }

        mapper.writeValue(archivoPresenciales, presenciales);
        mapper.writeValue(archivoVirtuales, virtuales);
    }
}
