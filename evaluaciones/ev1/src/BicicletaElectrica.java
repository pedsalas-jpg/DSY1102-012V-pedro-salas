public class BicicletaElectrica extends Bicicleta{

    public BicicletaElectrica(String codigoBicicleta, String anioFabricacion, double pesoKg) {
        super(codigoBicicleta, anioFabricacion ,pesoKg );
    }
    public double calcularPrecioFinal() {
        return getPrecio();
    }
}
