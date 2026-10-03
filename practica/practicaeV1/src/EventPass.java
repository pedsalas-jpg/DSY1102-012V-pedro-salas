import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        try {
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
        } catch (IllegalArgumentException e) {
            System.out.println("Error de dominio: " + e.getMessage());
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
            return "Error: No existe ninguna entrada registrada con el código '" + codigo + "'.";
        }
        if (e instanceof Reembolsable) {
            Reembolsable reembolsable = (Reembolsable) e;
            return "Monto de reembolso disponible: $" + reembolsable.calcularMontoReembolso();
        } else {
            return "Incompatible: La Entrada General no admite reembolso.";
        }
    }
}