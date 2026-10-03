public class Main {
    public static void main(String[] args) {
        Entrada entrada = new Entrada("EVT-1001", "Concierto Rock", 25000.0);

        System.out.println("--- DATOS INICIALES ---");
        System.out.println("Código: " + entrada.getCodigo());
        System.out.println("Evento: " + entrada.getNombreEvento());
        System.out.println("Precio Base: $" + entrada.getPrecioBase());
        System.out.println("Precio Final: $" + entrada.calcularPrecioFinal());
        System.out.println("¿Disponible?: " + entrada.isDisponible());

        System.out.println("\n--- PRIMER INTENTO DE VENTA ---");
        boolean primeraVenta = entrada.vender();
        System.out.println("¿Se vendió con éxito?: " + primeraVenta);
        System.out.println("¿Sigue disponible?: " + entrada.isDisponible());

        System.out.println("\n--- SEGUNDO INTENTO DE VENTA ---");
        boolean segundaVenta = entrada.vender();
        System.out.println("¿Se vendió con éxito?: " + segundaVenta);
    }
}