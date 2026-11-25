package cl.adumun.academiclabs.dsy1102.estacionsur;

import cl.adumun.academiclabs.dsy1102.estacionsur.model.*;
import cl.adumun.academiclabs.dsy1102.estacionsur.repository.TallerJsonRepository;
import cl.adumun.academiclabs.dsy1102.estacionsur.repository.TallerRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class TallerController {

    @FXML private ComboBox<String> cmbModalidad;
    @FXML private TextField txtCodigo, txtNombre, txtDuracion, txtCupos, txtSala, txtPlataforma;
    @FXML private CheckBox chkMateriales, chkGrabacion;

    @FXML private TableView<Taller> tblTalleres;
    @FXML private TableColumn<Taller, String> colModalidad, colNombre, colDuracion, colCupos, colArancel;

    @FXML private Label lblMensaje;

    private final TallerRepository repository = new TallerJsonRepository();
    private final ObservableList<Taller> lista = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cmbModalidad.getItems().addAll("Presencial", "Virtual");
        cambiarCampos(null);
        cmbModalidad.setOnAction(e -> cambiarCampos(cmbModalidad.getValue()));

        colModalidad.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().tipo()));
        colNombre.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombre()));
        colDuracion.setCellValueFactory(d -> new SimpleStringProperty(String.valueOf(d.getValue().getDuracionHoras())));
        colCupos.setCellValueFactory(d -> new SimpleStringProperty(String.valueOf(d.getValue().getCupos())));
        colArancel.setCellValueFactory(d -> new SimpleStringProperty("$" + d.getValue().calcularArancel()));

        // Cargar datos guardados al iniciar
        try {
            lista.addAll(repository.cargar());
        } catch (IOException e) {
            lblMensaje.setText("Error al cargar archivos JSON: " + e.getMessage());
        }

        tblTalleres.setItems(lista);
    }

    private void cambiarCampos(String mod) {
        boolean presencial = "Presencial".equals(mod);
        boolean virtual = "Virtual".equals(mod);

        txtSala.setDisable(!presencial);
        chkMateriales.setDisable(!presencial);
        if (!presencial) {
            txtSala.clear();
            chkMateriales.setSelected(false);
        }

        txtPlataforma.setDisable(!virtual);
        chkGrabacion.setDisable(!virtual);
        if (!virtual) {
            txtPlataforma.clear();
            chkGrabacion.setSelected(false);
        }
    }

    @FXML
    private void registrar() {
        String mod = cmbModalidad.getValue();
        if (mod == null) {
            lblMensaje.setText("Error: Debes seleccionar una modalidad (Presencial o Virtual).");
            return;
        }

        String cod = txtCodigo.getText().trim();
        String nom = txtNombre.getText().trim();
        String durTxt = txtDuracion.getText().trim();
        String cupTxt = txtCupos.getText().trim();

        if (cod.isEmpty() || nom.isEmpty() || durTxt.isEmpty() || cupTxt.isEmpty()) {
            lblMensaje.setText("Error: Completa código, nombre, duración y cupos antes de registrar.");
            return;
        }

        int duracion;
        try {
            duracion = Integer.parseInt(durTxt);
        } catch (NumberFormatException e) {
            lblMensaje.setText("Error: La duración debe ser un número entero válido.");
            return;
        }

        int cupos;
        try {
            cupos = Integer.parseInt(cupTxt);
        } catch (NumberFormatException e) {
            lblMensaje.setText("Error: Los cupos deben ser un número entero válido.");
            return;
        }

        try {
            if (mod.equals("Presencial")) {
                String sala = txtSala.getText().trim();
                if (sala.isEmpty()) {
                    lblMensaje.setText("Error: Debes ingresar la sala para un taller presencial.");
                    return;
                }
                lista.add(new TallerPresencial(cod, nom, duracion, cupos, sala, chkMateriales.isSelected()));
            } else {
                String plataforma = txtPlataforma.getText().trim();
                if (plataforma.isEmpty()) {
                    lblMensaje.setText("Error: Debes ingresar la plataforma para un taller virtual.");
                    return;
                }
                lista.add(new TallerVirtual(cod, nom, duracion, cupos, plataforma, chkGrabacion.isSelected()));
            }

            // Guardar automáticamente en los JSON
            repository.guardar(lista);

            lblMensaje.setText("Taller '" + nom + "' registrado y guardado.");
            cmbModalidad.setValue(null);
            txtCodigo.clear();
            txtNombre.clear();
            txtDuracion.clear();
            txtCupos.clear();
            cambiarCampos(null);

        } catch (IllegalArgumentException e) {
            lblMensaje.setText("Error de validación: " + e.getMessage());
        } catch (IOException e) {
            lblMensaje.setText("Taller agregado, pero falló al guardar JSON: " + e.getMessage());
        }
    }

    @FXML
    private void eliminar() {
        Taller elegido = tblTalleres.getSelectionModel().getSelectedItem();
        if (elegido == null) {
            lblMensaje.setText("Aviso: Debes seleccionar un taller de la tabla para eliminar.");
            return;
        }

        lista.remove(elegido);
        try {
            repository.guardar(lista);
            lblMensaje.setText("Taller '" + elegido.getNombre() + "' eliminado.");
        } catch (IOException e) {
            lblMensaje.setText("Eliminado en pantalla, pero falló al actualizar JSON: " + e.getMessage());
        }
    }
}
