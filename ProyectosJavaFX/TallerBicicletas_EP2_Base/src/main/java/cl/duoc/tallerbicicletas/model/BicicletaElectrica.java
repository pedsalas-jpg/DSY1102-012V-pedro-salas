package cl.duoc.tallerbicicletas.model;

/**
 * Bicicleta eléctrica atendida en el taller. Participa en el programa de garantía extendida.
 * Costo de mantención: base $45.000; si la batería no está certificada, recargo del 25%.
 */
public class BicicletaElectrica extends Bicicleta implements ConGarantiaExtendida {

    private static final double COSTO_BASE = 45000;
    private static final double RECARGO_BATERIA_NO_CERTIFICADA = 0.25;

    private int autonomiaKm;
    private boolean bateriaCertificada;
    private boolean garantiaExtendida;

    /**
     * Constructor sin parámetros, requerido para reconstruir objetos.
     */
    public BicicletaElectrica() {
    }

    public BicicletaElectrica(String codigo, int anioFabricacion, double peso, int autonomiaKm, boolean bateriaCertificada) {
        super(codigo, anioFabricacion, peso);
        setAutonomiaKm(autonomiaKm);
        setBateriaCertificada(bateriaCertificada);
        this.garantiaExtendida = false;
    }

    public int getAutonomiaKm() {
        return autonomiaKm;
    }

    public void setAutonomiaKm(int autonomiaKm) {
        this.autonomiaKm = autonomiaKm;
    }

    public boolean isBateriaCertificada() {
        return bateriaCertificada;
    }

    public void setBateriaCertificada(boolean bateriaCertificada) {
        this.bateriaCertificada = bateriaCertificada;
    }

    @Override
    public boolean tieneGarantiaExtendida() {
        return garantiaExtendida;
    }

    @Override
    public void activarGarantiaExtendida() {
        this.garantiaExtendida = true;
    }

    @Override
    public double calcularCostoMantencion() {
        if (bateriaCertificada) {
            return COSTO_BASE;
        }
        return COSTO_BASE + COSTO_BASE * RECARGO_BATERIA_NO_CERTIFICADA;
    }

    @Override
    public String getTipo() {
        return "Bicicleta Eléctrica";
    }
}
