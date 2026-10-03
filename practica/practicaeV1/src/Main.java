import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        LecturaEntrada lector = new LecturaEntrada();
        EventPass eventPass = new EventPass();

        int opcion;
        do {
            System.out.println("\n=== EVENTPASS - SISTEMA DE GESTIÓN (ENTREGA FINAL) ===");
            System.out.println("1. Registrar nueva entrada");
            System.out.println("2. Listar todas las entradas");
            System.out.println("3. Listar solo entradas disponibles");
            System.out.println("4. Ver lista de eventos registrados (únicos)");
            System.out.println("5. Buscar entrada por código");
            System.out.println("6. Consultar precio con descuento");
            System.out.println("7. Vender entrada por código");
            System.out.println("8. Consultar reembolso por código");
            System.out.println("9. Salir");

            opcion = lector.leerEnteroEnRango("Seleccione una opción: ", 1, 9);

            switch (opcion) {
                case 1:
                    System.out.println("\n--- REGISTRO DE ENTRADA ---");
                    String codigo = lector.leerTextoNoVacio("Ingrese el código de la entrada: ");

                    if (eventPass.existeCodigo(codigo)) {
                        System.out.println("Error: El código '" + codigo + "' ya se encuentra registrado.");
                        break;
                    }

                    System.out.println("Tipos de Entrada:");
                    System.out.println("  1. General");
                    System.out.println("  2. VIP (+30% recargo, permite reembolso)");
                    System.out.println("  3. Streaming (+10% recargo, permite reembolso)");
                    int tipo = lector.leerEnteroEnRango("Seleccione tipo (1-3): ", 1, 3);

                    String nombreEvento = lector.leerTextoNoVacio("Ingrese el nombre del evento: ");
                    double precioBase = lector.leerDoublePositivo("Ingrese el precio base (> 0): ");

                    if (eventPass.registrarEntrada(tipo, codigo, nombreEvento, precioBase)) {
                        System.out.println("¡Entrada registrada con éxito!");
                    } else {
                        System.out.println("No se pudo registrar la entrada.");
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
                    System.out.println("\n--- EVENTOS REGISTRADOS (SIN REPETICIÓN) ---");
                    Set<String> eventos = eventPass.getEventos();
                    if (eventos.isEmpty()) {
                        System.out.println("No hay eventos registrados.");
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
                        System.out.println("Error: No se encontró la entrada con el código '" + codBuscar + "'.");
                    }
                    break;

                case 6:
                    System.out.println("\n--- CONSULTA DE PRECIO CON DESCUENTO ---");
                    String codDesc = lector.leerTextoNoVacio("Ingrese el código de la entrada: ");
                    Entrada eDesc = eventPass.buscarPorCodigo(codDesc);
                    if (eDesc != null) {
                        double descuento = lector.leerDoubleEnRango("Ingrese porcentaje de descuento (0-100): ", 0, 100);
                        System.out.println("Precio final con " + descuento + "% de descuento: $" + eDesc.calcularPrecioFinal(descuento));
                    } else {
                        System.out.println("Error: No se encontró la entrada con el código '" + codDesc + "'.");
                    }
                    break;

                case 7:
                    System.out.println("\n--- VENTA DE ENTRADA ---");
                    String codVender = lector.leerTextoNoVacio("Ingrese el código de la entrada a vender: ");
                    Entrada eVender = eventPass.buscarPorCodigo(codVender);
                    if (eVender == null) {
                        System.out.println("Error: La entrada con el código '" + codVender + "' no existe.");
                    } else if (!eVender.isDisponible()) {
                        System.out.println("Operación rechazada: La entrada ya fue vendida previamente.");
                    } else {
                        if (eventPass.venderEntrada(codVender)) {
                            System.out.println("¡Venta realizada con éxito!");
                        }
                    }
                    break;

                case 8:
                    System.out.println("\n--- CONSULTA DE REEMBOLSO ---");
                    String codReembolso = lector.leerTextoNoVacio("Ingrese el código de la entrada: ");
                    System.out.println(eventPass.consultarReembolso(codReembolso));
                    break;

                case 9:
                    System.out.println("Saliendo de la aplicación...");
                    break;
            }
        } while (opcion != 9);
    }

    private static void mostrarDetalleEntrada(Entrada e) {
        System.out.println("[" + e.getClass().getSimpleName() + "] Código: " + e.getCodigo()
                + " | Evento: " + e.getNombreEvento()
                + " | Precio Base: $" + e.getPrecioBase()
                + " | Precio Final: $" + e.calcularPrecioFinal()
                + " | Estado: " + (e.isDisponible() ? "Disponible" : "Vendida"));
    }
}