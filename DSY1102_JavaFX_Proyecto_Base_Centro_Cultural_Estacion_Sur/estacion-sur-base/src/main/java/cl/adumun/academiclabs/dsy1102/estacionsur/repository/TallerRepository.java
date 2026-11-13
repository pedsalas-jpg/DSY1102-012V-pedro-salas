package cl.adumun.academiclabs.dsy1102.estacionsur.repository;

import cl.adumun.academiclabs.dsy1102.estacionsur.model.Taller;
import java.io.IOException;
import java.util.List;

public interface TallerRepository {
    List<Taller> cargar() throws IOException;
    void guardar(List<Taller> talleres) throws IOException;
}

