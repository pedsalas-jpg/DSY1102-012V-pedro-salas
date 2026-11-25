package cl.duoc.tallerbicicletas.controller;

import cl.duoc.tallerbicicletas.model.Bicicleta;
import cl.duoc.tallerbicicletas.model.BicicletaElectrica;
import cl.duoc.tallerbicicletas.model.BicicletaMontanya;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class TallerController {

    @FXML private ComboBox<String> cmbTipo;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtAnio;
    @FXML private TextField txtPeso;

    @FXML private TextField txtAutonomia;
    @FXML private CheckBox chkBateriaCertificada;
    @FXML private CheckBox chkGarantiaExtendida;

    @FXML private TextField txtSuspensiones;

    @FXML private TableView<FilaTabla> tblBicicletas;
    @FXML private TableColumn<FilaTabla, String> colTipo;
    @FXML private TableColumn<FilaTabla, String> colCodigo;
    @FXML private TableColumn<FilaTabla, Integer> colAnio;
    @FXML private TableColumn<FilaTabla, Double> colPeso;
    @FXML private TableColumn<FilaTabla, String> colCosto;

    @FXML private Label lblMensaje;

    private ArrayList<Bicicleta> listaBicicletas = new ArrayList<>();
    private ObservableList<FilaTabla> filasTabla = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cmbTipo.getItems().add("Bicicleta Eléctrica");
        cmbTipo.getItems().add("Bicicleta de Montaña");

        deshabilitarCamposEspecificos();

        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colAnio.setCellValueFactory(new PropertyValueFactory<>("anio"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        colCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));

        tblBicicletas.setItems(filasTabla);
    }

    @FXML
    public void cambiarTipo() {
        String tipo = cmbTipo.getValue();
        deshabilitarCamposEspecificos();

        if (tipo == null) {
            return;
        }

        if (tipo.equals("Bicicleta Eléctrica")) {
            txtAutonomia.setDisable(false);
            chkBateriaCertificada.setDisable(false);
            chkGarantiaExtendida.setDisable(false);
        } else if (tipo.equals("Bicicleta de Montaña")) {
            txtSuspensiones.setDisable(false);
        }
    }

    @FXML
    public void registrar() {
        try {
            String tipo = cmbTipo.getValue();
            String codigo = txtCodigo.getText().trim();
            int anio = Integer.parseInt(txtAnio.getText().trim());
            double peso = Double.parseDouble(txtPeso.getText().trim());

            Bicicleta bici = null;

            if (tipo != null && tipo.equals("Bicicleta Eléctrica")) {
                int autonomia = Integer.parseInt(txtAutonomia.getText().trim());
                boolean certificada = chkBateriaCertificada.isSelected();
                boolean garantia = chkGarantiaExtendida.isSelected();

                BicicletaElectrica electrica = new BicicletaElectrica(codigo, anio, peso, autonomia, certificada);
                if (garantia) {
                    electrica.activarGarantiaExtendida();
                }
                bici = electrica;

            } else if (tipo != null && tipo.equals("Bicicleta de Montaña")) {
                int suspensiones = Integer.parseInt(txtSuspensiones.getText().trim());
                bici = new BicicletaMontanya(codigo, anio, peso, suspensiones);
            }

            if (bici != null) {
                listaBicicletas.add(bici);
                actualizarTabla();
                limpiarFormulario();
                mostrarMensaje(bici.getCodigo() + " (" + tipo + ") registrada correctamente.", true);
            }

        } catch (Exception e) {
            mostrarMensaje("Dato no válido: " + e.getMessage(), false);
        }
    }

    @FXML
    public void eliminar() {
        int filaSeleccionada = tblBicicletas.getSelectionModel().getSelectedIndex();

        if (filaSeleccionada == -1) {
            mostrarMensaje("Debe seleccionar una bicicleta de la tabla para eliminarla.", false);
            return;
        }

        Bicicleta eliminada = listaBicicletas.remove(filaSeleccionada);
        actualizarTabla();
        mostrarMensaje(eliminada.getCodigo() + " eliminada correctamente.", true);
    }

    private void actualizarTabla() {
        filasTabla.clear();
        for (Bicicleta b : listaBicicletas) {
            String tipoTexto = "Bicicleta de Montaña";
            if (b instanceof BicicletaElectrica) {
                tipoTexto = "Bicicleta Eléctrica";
            }

            String costoTexto = String.format("$%,.0f", b.calcularCostoMantencion()).replace(",", ".");
            FilaTabla fila = new FilaTabla(tipoTexto, b.getCodigo(), b.getAnioFabricacion(), b.getPeso(), costoTexto);
            filasTabla.add(fila);
        }
    }

    private void deshabilitarCamposEspecificos() {
        txtAutonomia.setDisable(true);
        chkBateriaCertificada.setDisable(true);
        chkGarantiaExtendida.setDisable(true);
        txtSuspensiones.setDisable(true);
    }

    private void limpiarFormulario() {
        cmbTipo.setValue(null);
        txtCodigo.clear();
        txtAnio.clear();
        txtPeso.clear();
        txtAutonomia.clear();
        chkBateriaCertificada.setSelected(false);
        chkGarantiaExtendida.setSelected(false);
        txtSuspensiones.clear();
        deshabilitarCamposEspecificos();
    }

    private void mostrarMensaje(String texto, boolean esExito) {
        if (esExito) {
            lblMensaje.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
        } else {
            lblMensaje.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        }
        lblMensaje.setText(texto);
    }

    public static class FilaTabla {
        private String tipo;
        private String codigo;
        private int anio;
        private double peso;
        private String costo;

        public FilaTabla(String tipo, String codigo, int anio, double peso, String costo) {
            this.tipo = tipo;
            this.codigo = codigo;
            this.anio = anio;
            this.peso = peso;
            this.costo = costo;
        }

        public String getTipo() { return tipo; }
        public String getCodigo() { return codigo; }
        public int getAnio() { return anio; }
        public double getPeso() { return peso; }
        public String getCosto() { return costo; }
    }
}

