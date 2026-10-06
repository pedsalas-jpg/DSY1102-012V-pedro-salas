import java.util.List;

public class Main {

    public static void main(String[] args) {
        GestorTallerBicicletas gestor = new GestorTallerBicicletas();

        // 1. Instanciación de objetos oficiales
        BicicletaElectrica e1 = new BicicletaElectrica("BIC-E01", 2023, 22.5, 60.0, false);
        BicicletaElectrica e2 = new BicicletaElectrica("BIC-E02", 2022, 24.0, 45.0, true);
        BicicletaMontanya m1 = new BicicletaMontanya("BIC-M01", 2021, 13.5, 2);
        BicicletaMontanya m2 = new BicicletaMontanya("BIC-M02", 2020, 12.0, 1);

        // 2. Activación de garantía extendida en BIC-E01
        ((ConGarantiaExtendida) e1).activarGarantiaExtendida();

        // 3. Registro de objetos con salida oficial
        gestor.registrarBicicleta(e1);
        gestor.registrarBicicleta(e2);
        gestor.registrarBicicleta(m1);
        gestor.registrarBicicleta(m2);

        System.out.println();

        // 4. Demostración de Búsqueda Institucional
        System.out.println("=== BUSQUEDA POR CODIGO: \"BIC-E01\" ===");
        List<Bicicleta> resultadosBicE01 = gestor.buscarPorCodigo("BIC-E01");
        for (Bicicleta b : resultadosBicE01) {
            if (b instanceof BicicletaElectrica elec) {
                System.out.println("Tipo: Bicicleta Eléctrica | Código: " + elec.getCodigoBicicleta() +
                        " | Año: " + elec.getAnioFabricacion() + " | Peso: " + elec.getPesoKg() + " kg | Autonomia: " +
                        (int) elec.getAutonomiaKm() + " km | Batería certificada: " + (elec.isBateriaCertificada() ? "Sí" : "No"));
                System.out.println("  Garantia extendida: " + (elec.tieneGarantiaExtendidaActiva() ? "Si" : "No") +
                        " | Costo mantención: $" + (long) elec.calcularCostoMantencion());
            }
        }
        System.out.println("---");
        System.out.println();

        // 5. Demostración de Listado Oficial
        gestor.listarBicicletas();
        System.out.println();

        // 6. Menú Interactivo
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== MENÚ DEL TALLER ===");
            System.out.println("1. Listar todas las bicicletas");
            System.out.println("2. Buscar bicicleta por código");
            System.out.println("3. Simular costo con descuento (Sobrecarga)");
            System.out.println("4. Salir");

            int opcion = LecturaEntrada.leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> gestor.listarBicicletas();
                case 2 -> {
                    String codigo = LecturaEntrada.leerTexto("Ingrese el código a buscar: ");
                    List<Bicicleta> resultados = gestor.buscarPorCodigo(codigo);
                    if (resultados.isEmpty()) {
                        System.out.println("No se encontraron bicicletas con el código: " + codigo);
                    } else {
                        for (Bicicleta b : resultados) {
                            System.out.println(b + " | Costo Normal: $" + b.calcularCostoMantencion());
                        }
                    }
                }
                case 3 -> {
                    String codigo = LecturaEntrada.leerTexto("Ingrese el código de la bicicleta: ");
                    List<Bicicleta> resultados = gestor.buscarPorCodigo(codigo);
                    if (resultados.isEmpty()) {
                        System.out.println("No se encontró la bicicleta ingresada.");
                    } else {
                        Bicicleta bici = resultados.get(0);
                        boolean porcentajeValido = false;
                        while (!porcentajeValido) {
                            double descuento = LecturaEntrada.leerDouble("Ingrese porcentaje de descuento (0 a 100): ");
                            try {
                                double costoConDescuento = bici.calcularCostoMantencion(descuento);
                                System.out.println("Costo Normal: $" + bici.calcularCostoMantencion());
                                System.out.println("Costo con " + descuento + "% de descuento: $" + costoConDescuento);
                                porcentajeValido = true;
                            } catch (IllegalArgumentException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                    }
                }
                case 4 -> {
                    System.out.println("¡Gracias por utilizar el sistema del taller!");
                    continuar = false;
                }
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}
