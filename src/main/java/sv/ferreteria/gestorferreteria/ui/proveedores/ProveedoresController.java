package sv.ferreteria.gestorferreteria.ui.proveedores;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sv.ferreteria.gestorferreteria.models.Proveedor;
import sv.ferreteria.gestorferreteria.services.ProveedorService;
import sv.ferreteria.gestorferreteria.utils.AlertUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProveedoresController implements Initializable { // Se agregó implements Initializable

    @FXML
    private TableView<Proveedor> tableProveedor;
    @FXML
    private TableColumn<Proveedor, Integer> colId;
    @FXML
    private TableColumn<Proveedor, String> colNombre;
    @FXML
    private TableColumn<Proveedor, String> colTelefono;
    @FXML
    private TableColumn<Proveedor, String> colEmail;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtEmail;

    private final ProveedorService proveedorService = new ProveedorService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTabla();
        cargarProveedores();
    }

    private void configurarTabla() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void cargarProveedores() {
        try {
            List<Proveedor> proveedores = proveedorService.obtenerProveedor();
            tableProveedor.setItems(FXCollections.observableArrayList(proveedores));
        } catch (Exception e) {
            AlertUtil.mostrarError("Error al cargar proveedores: " + e.getMessage());
        }
    }

    @FXML
    private void onAgregar() {
        try {
            String nombre = txtNombre.getText().trim();
            String telefono = txtTelefono.getText().trim();
            String email = txtEmail.getText().trim();

            if (nombre.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
                AlertUtil.mostrarError("Todos los campos son obligatorios.");
                return;
            }

            Proveedor proveedor = new Proveedor();
            proveedor.setNombre(nombre);
            proveedor.setTelefono(telefono);
            proveedor.setEmail(email);

            proveedorService.agregarProveedor(proveedor);
            AlertUtil.mostrarInfo("Proveedor agregado correctamente");
            limpiarCampos();
            cargarProveedores();
        } catch (Exception e) {
            AlertUtil.mostrarError("Error al guardar: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtTelefono.clear();
        txtEmail.clear();
    }
}