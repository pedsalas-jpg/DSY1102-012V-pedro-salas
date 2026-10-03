public class EventPass {

    private Entrada entrada;

    public void registrarEntrada(String codigo, String nombreEvento, double precioBase) {
        this.entrada = new Entrada(codigo, nombreEvento, precioBase);
    }

    public Entrada getEntrada() {
        return entrada;
    }

    public double consultarPrecioConDescuento(double porcentaje) {
        if (entrada == null) {
            return 0;
        }
        return entrada.calcularPrecioFinal(porcentaje);
    }

    public boolean venderEntrada() {
        if (entrada == null) {
            return false;
        }
        return entrada.vender();
    }
}