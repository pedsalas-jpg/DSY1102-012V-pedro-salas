public abstract class Entrada {

    private String codigo;
    private String nombreEvento;
    private double precioBase;
    private boolean disponible;

    public Entrada(String codigo, String nombreEvento, double precioBase) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código es obligatorio y no puede estar vacío.");
        }
        if (nombreEvento == null || nombreEvento.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del evento es obligatorio y no puede estar vacío.");
        }
        if (precioBase <= 0) {
            throw new IllegalArgumentException("El precio base debe ser mayor que cero.");
        }
        this.codigo = codigo;
        this.nombreEvento = nombreEvento;
        this.precioBase = precioBase;
        this.disponible = true;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setNombreEvento(String nombreEvento) {
        if (nombreEvento != null && !nombreEvento.trim().isEmpty()) {
            this.nombreEvento = nombreEvento;
        }
    }

    public void setPrecioBase(double precioBase) {
        if (precioBase > 0) {
            this.precioBase = precioBase;
        }
    }

    public abstract double calcularPrecioFinal();

    public double calcularPrecioFinal(double porcentajeDescuento) {
        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            return calcularPrecioFinal();
        }
        double precioConRecargo = calcularPrecioFinal();
        return precioConRecargo - (precioConRecargo * porcentajeDescuento / 100.0);
    }

    public boolean vender() {
        if (disponible) {
            disponible = false;
            return true;
        }
        return false;
    }
}