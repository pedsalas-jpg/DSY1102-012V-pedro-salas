public class EntradaStreaming extends Entrada implements Reembolsable {

    public EntradaStreaming(String codigo, String nombreEvento, double precioBase) {
        super(codigo, nombreEvento, precioBase);
    }

    @Override
    public double calcularPrecioFinal() {
        return getPrecioBase() * 1.10;
    }

    @Override
    public double calcularMontoReembolso() {
        return calcularPrecioFinal() * 0.90;
    }
}