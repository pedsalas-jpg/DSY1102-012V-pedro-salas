public class BicicletaMontanya extends Bicicleta {

    private int cantidadSuspensiones;

    public BicicletaMontanya(String codigoBicicleta, int anioFabricacion, double pesoKg, int cantidadSuspensiones) {
        super(codigoBicicleta, anioFabricacion, pesoKg);
        setCantidadSuspensiones(cantidadSuspensiones);
    }

    public int getCantidadSuspensiones() {
        return cantidadSuspensiones;
    }

    public void setCantidadSuspensiones(int cantidadSuspensiones) {
        if (cantidadSuspensiones < 0) {
            throw new IllegalArgumentException("La cantidad de suspensiones no puede ser negativa.");
        }
        this.cantidadSuspensiones = cantidadSuspensiones;
    }

    @Override
    public double calcularCostoMantencion() {
        double costoBase = 30000.0;
        if (cantidadSuspensiones > 1) {
            costoBase += costoBase * 0.15;
        }
        return costoBase;
    }
}