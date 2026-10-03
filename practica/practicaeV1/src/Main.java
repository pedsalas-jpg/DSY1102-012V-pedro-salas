public class Main {

    public static void main(String[] args) {
        LecturaEntrada lector = new LecturaEntrada();
        EventPass eventPass = new EventPass();

        System.out.println("=== REGISTRO DE ENTRADA (EVENTPASS) ===");
        System.out.println("1. Entrada General");
        System.out.println("2. Entrada VIP (+30% recargo)");
        System.out.println("3. Entrada Streaming (+10% recargo)");
        int tipo = lector.leerEnteroEnRango("Seleccione el tipo de entrada: ", 1, 3);

        String codigo = lector.leerTextoNoVacio("Ingrese el código de la entrada: ");
        String nombreEvento = lector.leerTextoNoVacio("Ingrese el nombre del evento: ");
        double precioBase = lector.leerDoublePositivo("Ingrese el precio base (> 0): ");

        eventPass.registrarEntrada(tipo, codigo, nombreEvento, precioBase);

        int opcion;
        do {
            System.out.println("\n=== MENÚ EVENTPASS ===");
            System.out.println("1. Consultar datos de la entrada");
            System.out.println("2. Consultar precio con descuento");
            System.out.println("3. Consultar reembolso");
            System.out.println("4. Vender entrada");
            System.out.println("5. Salir");

            opcion = lector.leerEnteroEnRango("Seleccione una opción: ", 1, 5);

            switch (opcion) {
                case 1:
                    Entrada entrada = eventPass.getEntrada();
                    System.out.println("\n--- DATOS DE LA ENTRADA ---");
                    System.out.println("Tipo: " + entrada.getClass().getSimpleName());
                    System.out.println("Código: " + entrada.getCodigo());
                    System.out.println("Evento: " + entrada.getNombreEvento());
                    System.out.println("Precio Base: $" + entrada.getPrecioBase());
                    System.out.println("Precio Final: $" + entrada.calcularPrecioFinal());
                    System.out.println("Estado: " + (entrada.isDisponible() ? "Disponible" : "Vendida"));
                    break;

                case 2:
                    double descuento = lector.leerDoubleEnRango("Ingrese el porcentaje de descuento (0 a 100): ", 0, 100);
                    double precioConDescuento = eventPass.consultarPrecioConDescuento(descuento);
                    System.out.println("Precio final con " + descuento + "% de descuento: $" + precioConDescuento);
                    break;

                case 3:
                    System.out.println("\n--- CONSULTA DE REEMBOLSO ---");
                    System.out.println(eventPass.consultarReembolso());
                    break;

                case 4:
                    if (eventPass.venderEntrada()) {
                        System.out.println("¡Venta realizada con éxito!");
                    } else {
                        System.out.println("Operación rechazada: La entrada ya no está disponible.");
                    }
                    break;

                case 5:
                    System.out.println("Saliendo de la aplicación...");
                    break;
            }
        } while (opcion != 5);
    }
}