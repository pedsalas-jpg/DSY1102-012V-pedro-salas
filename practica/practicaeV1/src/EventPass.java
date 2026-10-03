public class EventPass {

    private Entrada entrada;

    public void registrarEntrada(int tipo, String codigo, String nombreEvento, double precioBase) {
        switch (tipo) {
            case 1:
                this.entrada = new EntradaGeneral(codigo, nombreEvento, precioBase);
                break;
            case 2:
                this.entrada = new EntradaVip(codigo, nombreEvento, precioBase);
                break;
            case 3:
                this.entrada = new EntradaStreaming(codigo, nombreEvento, precioBase);
                break;
        }
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

    public String consultarReembolso() {
        if (entrada == null) {
            return "No hay entrada registrada.";
        }
        if (entrada instanceof Reembolsable) {
            Reembolsable reembolsable = (Reembolsable) entrada;
            return "Monto de reembolso disponible: $" + reembolsable.calcularMontoReembolso();
        } else {
            return "Este tipo de entrada (General) no admite reembolso.";
        }
    }
}