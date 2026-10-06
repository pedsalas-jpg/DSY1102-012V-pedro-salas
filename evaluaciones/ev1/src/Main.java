import java.util.List;

public class Main {

    public static void main(String[] args) {
        GestorTallerBicicletas gestor = new GestorTallerBicicletas();

        BicicletaElectrica e1 = new BicicletaElectrica("BIC-E01", 2023, 22.5, 60.0, false);
        BicicletaElectrica e2 = new BicicletaElectrica("BIC-E02", 2022, 24.0, 45.0, true);
        BicicletaMontanya m1 = new BicicletaMontanya("BIC-M01", 2021, 13.5, 2);
        BicicletaMontanya m2 = new BicicletaMontanya("BIC-M02", 2020, 12.0, 1);

        ConGarantiaExtendida garantiable1 = e1;
        garantiable1.activarGarantiaExtendida();

        gestor.registrarBicicleta(e1);
        gestor.registrarBicicleta(e2);
        gestor.registrarBicicleta(m1);
        gestor.registrarBicicleta(m2);

        List<Bicicleta> resultadosBusqueda = gestor.buscarPorCodigo("BIC-E01");

        for (Bicicleta bicicleta : resultadosBusqueda) {
            System.out.println("Tipo Concreto: " + bicicleta.getClass().getSimpleName());
            System.out.println("Datos Comunes: " + bicicleta.toString() + " | Peso: " + bicicleta.getPesoKg() + " kg");

            if (bicicleta instanceof BicicletaElectrica) {
                BicicletaElectrica elec = (BicicletaElectrica) bicicleta;
                System.out.println("Datos Específicos: Autonomía " + elec.getAutonomiaKm() + " km | Batería Certificada: " + (elec.isBateriaCertificada() ? "Sí" : "No"));
                System.out.println("Estado Garantía Extendida: " + (elec.tieneGarantiaExtendidaActiva() ? "Activa" : "Inactiva"));
            } else if (bicicleta instanceof BicicletaMontanya) {
                BicicletaMontanya mont = (BicicletaMontanya) bicicleta;
                System.out.println("Datos Específicos: Suspensiones: " + mont.getCantidadSuspensiones());
            }

            System.out.println("Costo de Mantención: $" + bicicleta.calcularCostoMantencion());
        }

        gestor.listarBicicletas();
    }
}
