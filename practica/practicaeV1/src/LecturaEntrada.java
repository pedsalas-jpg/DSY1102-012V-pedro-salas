import java.util.Scanner;

public class LecturaEntrada {

    private Scanner scanner;

    public LecturaEntrada() {
        this.scanner = new Scanner(System.in);
    }

    public String leerTextoNoVacio(String mensaje) {
        String texto = "";
        while (texto.trim().isEmpty()) {
            System.out.print(mensaje);
            texto = scanner.nextLine();
            if (texto.trim().isEmpty()) {
                System.out.println("Error: El texto no puede estar vacío.");
            }
        }
        return texto;
    }

    public double leerDoublePositivo(String mensaje) {
        double valor = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            try {
                valor = Double.parseDouble(entrada);
                if (valor <= 0) {
                    System.out.println("Error: El valor debe ser mayor que cero.");
                } else {
                    valido = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada no válida. Debe ingresar un valor numérico.");
            }
        }
        return valor;
    }

    public double leerDoubleEnRango(String mensaje, double minimo, double maximo) {
        double valor = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            try {
                valor = Double.parseDouble(entrada);
                if (valor < minimo || valor > maximo) {
                    System.out.println("Error: El valor debe estar entre " + minimo + " y " + maximo + ".");
                } else {
                    valido = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada no válida. Debe ingresar un valor numérico.");
            }
        }
        return valor;
    }

    public int leerEnteroEnRango(String mensaje, int minimo, int maximo) {
        int valor = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            try {
                valor = Integer.parseInt(entrada);
                if (valor < minimo || valor > maximo) {
                    System.out.println("Error: Opción fuera de rango (" + minimo + " - " + maximo + ").");
                } else {
                    valido = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada no válida. Debe ingresar un número entero.");
            }
        }
        return valor;
    }
}