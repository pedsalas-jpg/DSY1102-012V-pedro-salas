import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== REGISTRO DE ENTRADA (EVENTPASS) ===");

        String codigo = "";
        while (codigo.trim().isEmpty()) {
            System.out.print("Ingrese el código de la entrada: ");
            codigo = scanner.nextLine();
            if (codigo.trim().isEmpty()) {
                System.out.println("El código no puede estar vacío.");
            }
        }

        String nombreEvento = "";
        while (nombreEvento.trim().isEmpty()) {
            System.out.print("Ingrese el nombre del evento: ");
            nombreEvento = scanner.nextLine();
            if (nombreEvento.trim().isEmpty()) {
                System.out.println("El nombre del evento no puede estar vacío.");
            }
        }

        double precioBase = 0;
        while (precioBase <= 0) {
            System.out.print("Ingrese el precio base (> 0): ");
            if (scanner.hasNextDouble()) {
                precioBase = scanner.nextDouble();
                if (precioBase <= 0) {
                    System.out.println("El precio debe ser mayor que cero.");
                }
            } else {
                System.out.println("Debe ingresar un valor numérico válido.");
                scanner.next();
            }
        }

        Entrada entrada = new Entrada(codigo, nombreEvento, precioBase);

        int opcion = 0;
        do {
            System.out.println("\n=== MENÚ EVENTPASS ===");
            System.out.println("1. Consultar datos de la entrada");
            System.out.println("2. Consultar precio con descuento");
            System.out.println("3. Vender entrada");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("\n--- DATOS DE LA ENTRADA ---");
                        System.out.println("Código: " + entrada.getCodigo());
                        System.out.println("Evento: " + entrada.getNombreEvento());
                        System.out.println("Precio Base: $" + entrada.getPrecioBase());
                        System.out.println("Precio Normal: $" + entrada.calcularPrecioFinal());
                        System.out.println("Estado: " + (entrada.isDisponible() ? "Disponible" : "Vendida"));
                        break;

                    case 2:
                        double descuento = -1;
                        while (descuento < 0 || descuento > 100) {
                            System.out.print("Ingrese el porcentaje de descuento (0 a 100): ");
                            if (scanner.hasNextDouble()) {
                                descuento = scanner.nextDouble();
                                if (descuento < 0 || descuento > 100) {
                                    System.out.println("El descuento debe estar entre 0 y 100.");
                                }
                            } else {
                                System.out.println("Debe ingresar un valor numérico válido.");
                                scanner.next();
                            }
                        }
                        double precioConDescuento = entrada.calcularPrecioFinal(descuento);
                        System.out.println("Precio final con " + descuento + "% de descuento: $" + precioConDescuento);
                        break;

                    case 3:
                        if (entrada.vender()) {
                            System.out.println("¡Venta realizada con éxito!");
                        } else {
                            System.out.println("Operación rechazada: La entrada ya no está disponible.");
                        }
                        break;

                    case 4:
                        System.out.println("Saliendo de la aplicación...");
                        break;

                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                        break;
                }
            } else {
                System.out.println("Debe ingresar un número entero válido.");
                scanner.next();
            }
        } while (opcion != 4);

        scanner.close();
    }
}