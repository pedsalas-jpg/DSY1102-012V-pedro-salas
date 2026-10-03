public class EntradaGeneral extends Entrada {

    public EntradaGeneral(String codigo, String nombreEvento, double precioBase) {
        super(codigo, nombreEvento, precioBase);
    }

    @Override
    public double calcularPrecioFinal() {
        return getPrecioBase();
    }
}