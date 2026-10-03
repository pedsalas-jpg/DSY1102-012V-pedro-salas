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
                System.out.println("El texto no puede estar vacío.");
            }
        }
        return texto;
    }

    public double leerDoublePositivo(String mensaje) {
        double valor = 0;
        while (valor <= 0) {
            System.out.print(mensaje);
            if (scanner.hasNextDouble()) {
                valor = scanner.nextDouble();
                if (valor <= 0) {
                    System.out.println("El valor debe ser mayor que cero.");
                }
            } else {
                System.out.println("Debe ingresar un número válido.");
                scanner.next();
            }
        }
        scanner.nextLine();
        return valor;
    }

    public double leerDoubleEnRango(String mensaje, double minimo, double maximo) {
        double valor = minimo - 1;
        while (valor < minimo || valor > maximo) {
            System.out.print(mensaje);
            if (scanner.hasNextDouble()) {
                valor = scanner.nextDouble();
                if (valor < minimo || valor > maximo) {
                    System.out.println("El valor debe estar entre " + minimo + " y " + maximo + ".");
                }
            } else {
                System.out.println("Debe ingresar un número válido.");
                scanner.next();
            }
        }
        scanner.nextLine();
        return valor;
    }

    public int leerEnteroEnRango(String mensaje, int minimo, int maximo) {
        int valor = minimo - 1;
        while (valor < minimo || valor > maximo) {
            System.out.print(mensaje);
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                if (valor < minimo || valor > maximo) {
                    System.out.println("Opción fuera de rango (" + minimo + " - " + maximo + ").");
                }
            } else {
                System.out.println("Debe ingresar un número entero válido.");
                scanner.next();
            }
        }
        scanner.nextLine();
        return valor;
    }
}
