public class EntradaVip extends Entrada implements Reembolsable {

    public EntradaVip(String codigo, String nombreEvento, double precioBase) {
        super(codigo, nombreEvento, precioBase);
    }

    @Override
    public double calcularPrecioFinal() {
        return getPrecioBase() * 1.30;
    }

    @Override
    public double calcularMontoReembolso() {
        return calcularPrecioFinal() * 0.80;
    }
}