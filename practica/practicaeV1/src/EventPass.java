import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * CONTRASTE Y LIMITACIÓN DEL ARREGLO ESTÁTICO (Entrada[] de tamaño 3):
 * Un arreglo como 'Entrada[] arreglo = new Entrada[3];' posee un tamaño fijo definido al instanciarse.
 * Presenta las siguientes limitaciones frente a una List (ArrayList):
 * 1. Capacidad rígida: Si se intenta registrar un 4to elemento, se lanza 'ArrayIndexOutOfBoundsException'.
 * 2. Control manual de posiciones: Exige llevar un índice o verificar posiciones nulas en recorridos con 'for'.
 * 3. En contraste, 'List<Entrada>' ajusta su capacidad dinámicamente y permite agregar elementos sin límite predefinido.
 */
public class EventPass {

    private List<Entrada> entradas;
    private Set<String> eventos;
    private Map<String, Entrada> entradasPorCodigo;

    public EventPass() {
        this.entradas = new ArrayList<>();
        this.eventos = new HashSet<>();
        this.entradasPorCodigo = new HashMap<>();
    }

    public boolean existeCodigo(String codigo) {
        return entradasPorCodigo.containsKey(codigo);
    }

    public boolean registrarEntrada(int tipo, String codigo, String nombreEvento, double precioBase) {
        if (existeCodigo(codigo)) {
            return false;
        }

        Entrada nuevaEntrada;
        switch (tipo) {
            case 1:
                nuevaEntrada = new EntradaGeneral(codigo, nombreEvento, precioBase);
                break;
            case 2:
                nuevaEntrada = new EntradaVip(codigo, nombreEvento, precioBase);
                break;
            case 3:
                nuevaEntrada = new EntradaStreaming(codigo, nombreEvento, precioBase);
                break;
            default:
                return false;
        }

        entradas.add(nuevaEntrada);
        eventos.add(nombreEvento);
        entradasPorCodigo.put(codigo, nuevaEntrada);
        return true;
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    public List<Entrada> obtenerEntradasDisponibles() {
        List<Entrada> disponibles = new ArrayList<>();
        for (Entrada e : entradas) {
            if (e.isDisponible()) {
                disponibles.add(e);
            }
        }
        return disponibles;
    }

    public Set<String> getEventos() {
        return eventos;
    }

    public Entrada buscarPorCodigo(String codigo) {
        return entradasPorCodigo.get(codigo);
    }

    public boolean venderEntrada(String codigo) {
        Entrada e = buscarPorCodigo(codigo);
        if (e != null) {
            return e.vender();
        }
        return false;
    }

    public String consultarReembolso(String codigo) {
        Entrada e = buscarPorCodigo(codigo);
        if (e == null) {
            return "No existe una entrada registrada con el código ingresado.";
        }
        if (e instanceof Reembolsable) {
            Reembolsable reembolsable = (Reembolsable) e;
            return "Monto de reembolso disponible: $" + reembolsable.calcularMontoReembolso();
        } else {
            return "Este tipo de entrada (General) no admite reembolso.";
        }
    }

    // Demostración técnica del uso de arreglo temporal de tamaño 3
    public void probarArregloFijo() {
        Entrada[] arregloFijo = new Entrada[3];
        for (int i = 0; i < arregloFijo.length; i++) {
            if (arregloFijo[i] != null) {
                System.out.println(arregloFijo[i].getCodigo());
            }
        }
    }
}