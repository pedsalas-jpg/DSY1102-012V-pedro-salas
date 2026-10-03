import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        LecturaEntrada lector = new LecturaEntrada();
        EventPass eventPass = new EventPass();

        int opcion;
        do {
            System.out.println("\n=== SARA - EVENTPASS (GESTIÓN DE ENTRADAS) ===");
            System.out.println("1. Registrar nueva entrada");
            System.out.println("2. Listar todas las entradas");
            System.out.println("3. Listar solo entradas disponibles");
            System.out.println("4. Ver lista de eventos registrados (sin duplicados)");
            System.out.println("5. Buscar entrada por código");
            System.out.println("6. Vender entrada por código");
            System.out.println("7. Consultar reembolso por código");
            System.out.println("8. Salir");

            opcion = lector.leerEnteroEnRango("Seleccione una opción: ", 1, 8);

            switch (opcion) {
                case 1:
                    System.out.println("\n--- REGISTRO DE ENTRADA ---");
                    String codigo = lector.leerTextoNoVacio("Ingrese el código de la entrada: ");

                    if (eventPass.existeCodigo(codigo)) {
                        System.out.println("Error: El código '" + codigo + "' ya se encuentra registrado.");
                        break;
                    }

                    System.out.println("Tipos disponibles:");
                    System.out.println("  1. General");
                    System.out.println("  2. VIP (+30% recargo)");
                    System.out.println("  3. Streaming (+10% recargo)");
                    int tipo = lector.leerEnteroEnRango("Seleccione tipo (1-3): ", 1, 3);

                    String nombreEvento = lector.leerTextoNoVacio("Ingrese el nombre del evento: ");
                    double precioBase = lector.leerDoublePositivo("Ingrese el precio base (> 0): ");

                    if (eventPass.registrarEntrada(tipo, codigo, nombreEvento, precioBase)) {
                        System.out.println("¡Entrada registrada con éxito!");
                    } else {
                        System.out.println("Error al registrar la entrada.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- LISTADO DE TODAS LAS ENTRADAS ---");
                    List<Entrada> todas = eventPass.getEntradas();
                    if (todas.isEmpty()) {
                        System.out.println("No hay entradas registradas.");
                    } else {
                        for (Entrada e : todas) {
                            mostrarDetalleEntrada(e);
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--- ENTRADAS DISPONIBLES ---");
                    List<Entrada> disponibles = eventPass.obtenerEntradasDisponibles();
                    if (disponibles.isEmpty()) {
                        System.out.println("No hay entradas disponibles para la venta.");
                    } else {
                        for (Entrada e : disponibles) {
                            mostrarDetalleEntrada(e);
                        }
                    }
                    break;

                case 4:
                    System.out.println("\n--- NOMBRES DE EVENTOS REGISTRADOS (SIN REPETICIÓN) ---");
                    Set<String> eventos = eventPass.getEventos();
                    if (eventos.isEmpty()) {
                        System.out.println("No hay eventos registrados aún.");
                    } else {
                        for (String evento : eventos) {
                            System.out.println("- " + evento);
                        }
                    }
                    break;

                case 5:
                    System.out.println("\n--- BÚSQUEDA POR CÓDIGO ---");
                    String codBuscar = lector.leerTextoNoVacio("Ingrese el código a buscar: ");
                    Entrada encontrada = eventPass.buscarPorCodigo(codBuscar);
                    if (encontrada != null) {
                        mostrarDetalleEntrada(encontrada);
                    } else {
                        System.out.println("No se encontró ninguna entrada con el código: " + codBuscar);
                    }
                    break;

                case 6:
                    System.out.println("\n--- VENTA DE ENTRADA ---");
                    String codVender = lector.leerTextoNoVacio("Ingrese el código de la entrada a vender: ");
                    if (eventPass.venderEntrada(codVender)) {
                        System.out.println("¡Venta realizada con éxito!");
                    } else {
                        System.out.println("Operación rechazada: Código inexistente o la entrada ya estaba vendida.");
                    }
                    break;

                case 7:
                    System.out.println("\n--- CONSULTA DE REEMBOLSO ---");
                    String codReembolso = lector.leerTextoNoVacio("Ingrese el código de la entrada: ");
                    System.out.println(eventPass.consultarReembolso(codReembolso));
                    break;

                case 8:
                    System.out.println("Saliendo de la aplicación...");
                    break;
            }
        } while (opcion != 8);
    }

    private static void mostrarDetalleEntrada(Entrada e) {
        System.out.println("[" + e.getClass().getSimpleName() + "] Código: " + e.getCodigo()
                + " | Evento: " + e.getNombreEvento()
                + " | Precio Base: $" + e.getPrecioBase()
                + " | Precio Final: $" + e.calcularPrecioFinal()
                + " | Estado: " + (e.isDisponible() ? "Disponible" : "Vendida"));
    }
}