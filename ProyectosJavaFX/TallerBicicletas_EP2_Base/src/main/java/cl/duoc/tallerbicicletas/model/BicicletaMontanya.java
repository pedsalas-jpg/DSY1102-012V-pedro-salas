package cl.duoc.tallerbicicletas.model;

/**
 * Bicicleta de montaña atendida en el taller.
 * Costo de mantención: base $30.000; si la cantidad de suspensiones supera 1, recargo del 15%.
 */
public class BicicletaMontanya extends Bicicleta {

    private static final double COSTO_BASE = 30000;
    private static final double RECARGO_SUSPENSIONES = 0.15;
    private static final int LIMITE_SUSPENSIONES = 1;

    private int suspensiones;

    /**
     * Constructor sin parámetros, requerido para reconstruir objetos.
     */
    public BicicletaMontanya() {
    }

    public BicicletaMontanya(String codigo, int anioFabricacion, double peso, int suspensiones) {
        super(codigo, anioFabricacion, peso);
        setSuspensiones(suspensiones);
    }

    public int getSuspensiones() {
        return suspensiones;
    }

    public void setSuspensiones(int suspensiones) {
        this.suspensiones = suspensiones;
    }

    @Override
    public double calcularCostoMantencion() {
        if (suspensiones > LIMITE_SUSPENSIONES) {
            return COSTO_BASE + COSTO_BASE * RECARGO_SUSPENSIONES;
        }
        return COSTO_BASE;
    }

    @Override
    public String getTipo() {
        return "Bicicleta de Montaña";
    }
}
