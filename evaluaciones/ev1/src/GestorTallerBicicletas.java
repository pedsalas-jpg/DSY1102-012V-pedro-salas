import java.util.ArrayList;
import java.util.List;

public class GestorTallerBicicletas {

    private List<Bicicleta> bicicletas;

    public GestorTallerBicicletas() {
        this.bicicletas = new ArrayList<>();
    }

    public boolean registrarBicicleta(Bicicleta bicicleta) {
        if (bicicleta == null) {
            System.out.println("Error: No se puede registrar una bicicleta nula.");
            return false;
        }
        boolean registrado = bicicletas.add(bicicleta);
        if (registrado) {
            System.out.println("Bicicleta registrada correctamente con el código: " + bicicleta.getCodigoBicicleta());
        }
        return registrado;
    }

    public List<Bicicleta> buscarPorCodigo(String criterio) {
        List<Bicicleta> resultados = new ArrayList<>();
        if (criterio == null || criterio.trim().isEmpty()) {
            return resultados;
        }

        for (Bicicleta bicicleta : bicicletas) {
            if (bicicleta.getCodigoBicicleta().equalsIgnoreCase(criterio.trim())) {
                resultados.add(bicicleta);
            }
        }
        return resultados;
    }

    public void listarBicicletas() {
        if (bicicletas.isEmpty()) {
            System.out.println("No hay bicicletas registradas en el taller.");
            return;
        }

        System.out.println("=== LISTADO DE BICICLETAS EN TALLER ===");
        for (Bicicleta bicicleta : bicicletas) {
            // Polimorfismo: se llama a calcularCostoMantencion() desde la referencia base Bicicleta
            System.out.println(bicicleta.toString() + " | Costo Mantención: $" + bicicleta.calcularCostoMantencion());
        }
    }
}
