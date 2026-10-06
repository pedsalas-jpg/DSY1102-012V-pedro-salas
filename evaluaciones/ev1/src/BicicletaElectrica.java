public class BicicletaElectrica extends Bicicleta {

    private double autonomiaKm;
    private boolean bateriaCertificada;

    public BicicletaElectrica(String codigoBicicleta, int anioFabricacion, double pesoKg, double autonomiaKm, boolean bateriaCertificada) {
        super(codigoBicicleta, anioFabricacion, pesoKg);
        setAutonomiaKm(autonomiaKm);
        setBateriaCertificada(bateriaCertificada);
    }

    public double getAutonomiaKm() {
        return autonomiaKm;
    }

    public void setAutonomiaKm(double autonomiaKm) {
        if (autonomiaKm <= 0) {
            throw new IllegalArgumentException("La autonomía debe ser mayor que cero.");
        }
        this.autonomiaKm = autonomiaKm;
    }

    public boolean isBateriaCertificada() {
        return bateriaCertificada;
    }

    public void setBateriaCertificada(boolean bateriaCertificada) {
        this.bateriaCertificada = bateriaCertificada;
    }

    @Override
    public double calcularCostoMantencion() {
        double costoBase = 45000.0;
        if (!bateriaCertificada) {
            costoBase += costoBase * 0.25;
        }
        return costoBase;
    }
}
