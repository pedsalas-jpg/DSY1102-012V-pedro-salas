package org.example.gestorclientesfx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.gestorclientesfx.dao.ClienteDao;
import org.example.gestorclientesfx.dao.ClienteDaoSqlite;
import org.example.gestorclientesfx.model.Cliente;

public class ClienteController {

    @FXML private TableView<Cliente> tablaClientes;
    @FXML private TableColumn<Cliente, Integer> colId;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colCorreo;
    @FXML private TableColumn<Cliente, String> colCiudad;

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtCiudad;

    private final ClienteDao clienteDao = new ClienteDaoSqlite();
    private final ObservableList<Cliente> datos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));

        tablaClientes.setItems(datos);
        cargarClientes();
    }

    private void cargarClientes() {
        datos.setAll(clienteDao.listarTodos());
    }

    @FXML
    private void seleccionarCliente() {
        Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            txtId.setText(String.valueOf(seleccionado.getId()));
            txtNombre.setText(seleccionado.getNombre());
            txtCorreo.setText(seleccionado.getCorreo());
            txtCiudad.setText(seleccionado.getCiudad());
        }
    }

    @FXML
    private void guardarCliente() {
        Cliente cliente = new Cliente(
                0,
                txtNombre.getText().trim(),
                txtCorreo.getText().trim(),
                txtCiudad.getText().trim()
        );
        clienteDao.insertar(cliente);
        cargarClientes();
        limpiarFormulario();
    }

    @FXML
    private void actualizarCliente() {
        if (!txtId.getText().isEmpty()) {
            Cliente cliente = new Cliente(
                    Integer.parseInt(txtId.getText()),
                    txtNombre.getText().trim(),
                    txtCorreo.getText().trim(),
                    txtCiudad.getText().trim()
            );
            clienteDao.actualizar(cliente);
            cargarClientes();
            limpiarFormulario();
        }
    }

    @FXML
    private void eliminarCliente() {
        Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            clienteDao.eliminar(seleccionado.getId());
            cargarClientes();
            limpiarFormulario();
        }
    }

    private void limpiarFormulario() {
        txtId.clear();
        txtNombre.clear();
        txtCorreo.clear();
        txtCiudad.clear();
    }
}


