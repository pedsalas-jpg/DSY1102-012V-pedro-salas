public abstract class Bicicleta {

    private String codigoBicicleta;
    private int anioFabricacion;
    private double pesoKg;

    public Bicicleta(String codigoBicicleta, int anioFabricacion, double pesoKg) {
        setCodigoBicicleta(codigoBicicleta);
        setAnioFabricacion(anioFabricacion);
        setPesoKg(pesoKg);
    }

    public String getCodigoBicicleta() {
        return codigoBicicleta;
    }

    public void setCodigoBicicleta(String codigoBicicleta) {
        if (codigoBicicleta == null || codigoBicicleta.trim().isEmpty()) {
            throw new IllegalArgumentException("El código es obligatorio y no puede estar vacío.");
        }
        this.codigoBicicleta = codigoBicicleta;
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

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        if (pesoKg <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor que cero.");
        }
        this.pesoKg = pesoKg;
    }

    // Método abstracto: obliga a cada subclase a implementar su propia lógica
    public abstract double calcularCostoMantencion();

    // Sobrecarga de método: mismo nombre, diferentes parámetros
    public double calcularCostoMantencion(double porcentajeDescuento) {
        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            throw new IllegalArgumentException("El porcentaje de descuento debe estar entre 0 y 100.");
        }
        double costoNormal = calcularCostoMantencion();
        return costoNormal * (1.0 - (porcentajeDescuento / 100.0));
    }

    @Override
    public String toString() {
        return "Código: " + codigoBicicleta + ", Año: " + anioFabricacion;
    }
}



