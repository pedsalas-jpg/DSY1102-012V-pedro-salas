public class Bicicleta {

    private String codigoBicicleta;
    private int anioFabriacion;
    private double pesoKg;

    public Bicicleta(String codigoBicicleta, int anioFabricacion, double pesoKg) {
        if (codigoBicicleta == null || codigoBicicleta.trim().isEmpty()) {
            throw new IllegalArgumentException("El código es obligatorio y no puede estar vacío.");
        }
        if (anioFabricacion < 2026 || anioFabricacion > 2000) {
            throw new IllegalArgumentException("El año de fabricacion tiene que estar entre 2000 y 2026");
        }
        if (pesoKg <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor que cero.");
        }
        this.codigoBicicleta = codigoBicicleta;
        this.anioFabriacion = anioFabricacion;
        this.pesoKg = pesoKg;

    }
}





