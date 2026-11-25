package cl.duoc.tallerbicicletas.model;

/**
 * Representa a cualquier bicicleta recibida en el taller.
 * Define los datos comunes y delega en cada tipo de bicicleta el cálculo del
 * costo de mantención.
 */
public abstract class Bicicleta {

    private String codigo;
    private int anioFabricacion;
    private double peso;

    /**
     * Constructor sin parámetros, requerido para reconstruir objetos.
     */
    protected Bicicleta() {
    }

    public Bicicleta(String codigo, int anioFabricacion, double peso) {
        setCodigo(codigo);
        setAnioFabricacion(anioFabricacion);
        setPeso(peso);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede ser nulo ni vacío.");
        }
        this.codigo = codigo.trim();
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setAnioFabricacion(int anioFabricacion) {
        if (anioFabricacion < 2000 || anioFabricacion > 2026) {
            throw new IllegalArgumentException("El año de fabricación debe estar entre 2000 y 2026.");
        }
        this.anioFabricacion = anioFabricacion;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor que cero.");
        }
        this.peso = peso;
    }

    /**
     * Calcula el costo de mantención según las reglas propias de cada tipo de bicicleta.
     *
     * @return costo de la mantención en pesos
     */
    public abstract double calcularCostoMantencion();

    /**
     * Retorna el nombre del tipo de bicicleta (por ejemplo, "Bicicleta Eléctrica" o "Bicicleta de Montaña").
     *
     * @return tipo de bicicleta
     */
    public abstract String getTipo();

    @Override
    public String toString() {
        return "Código: " + codigo + " | Año: " + anioFabricacion;
    }
}
