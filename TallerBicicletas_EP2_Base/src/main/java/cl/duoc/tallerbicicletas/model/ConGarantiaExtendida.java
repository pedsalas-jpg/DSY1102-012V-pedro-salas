package cl.duoc.tallerbicicletas.model;

/**
 * Contrato que cumplen las bicicletas que participan en el programa de
 * garantía extendida del taller.
 */
public interface ConGarantiaExtendida {

    /**
     * Indica si la bicicleta tiene una garantía extendida activa.
     *
     * @return true si la garantía extendida está activa
     */
    boolean tieneGarantiaExtendida();

    /**
     * Activa la garantía extendida de la bicicleta.
     */
    void activarGarantiaExtendida();
}
