package sv.ferreteria.gestorferreteria.ui.productos;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import sv.ferreteria.gestorferreteria.models.Producto;
import sv.ferreteria.gestorferreteria.models.Proveedor;
import sv.ferreteria.gestorferreteria.services.ProductoService;
import sv.ferreteria.gestorferreteria.services.ProveedorService;
import sv.ferreteria.gestorferreteria.utils.AlertUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProductosController implements Initializable {

    @FXML private TableView<Producto> tableProductos;
    @FXML private TableColumn<Producto, Integer> colId;
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Double> colPrecio;
    @FXML private TableColumn<Producto, Integer> colStock;
    @FXML private TableColumn<Producto, String> colProveedor;

    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtStock;
    @FXML private ComboBox<Proveedor> comboProveedor;

    private final ProductoService productoService = new ProductoService();
    private final ProveedorService proveedorService = new ProveedorService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTabla();
        configurarComboBox();
        cargarDatosIniciales();
    }

    private void configurarTabla() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        colProveedor.setCellValueFactory(cellData -> {
            Proveedor proveedor = cellData.getValue().getProveedor();
            String nombre = (proveedor != null) ? proveedor.getNombre() : "N/A";
            return new SimpleStringProperty(nombre);
        });
    }

    private void configurarComboBox() {
        comboProveedor.setConverter(new StringConverter<Proveedor>() {
            @Override
            public String toString(Proveedor proveedor) {
                return (proveedor != null) ? proveedor.getNombre() : "";
            }

            @Override
            public Proveedor fromString(String string) {
                return null;
            }
        });
    }

    private void cargarDatosIniciales() {
        try {
            List<Proveedor> listaProv = proveedorService.obtenerProveedor();
            comboProveedor.setItems(FXCollections.observableArrayList(listaProv));

            actualizarTablaProductos();
        } catch (Exception e) {
            AlertUtil.mostrarError("Error de carga: " + e.getMessage());
        }
    }

    private void actualizarTablaProductos() {
        try {
            List<Producto> productos = productoService.obtenerProductos();
            tableProductos.setItems(FXCollections.observableArrayList(productos));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onAgregar() {
        try {
            if (txtNombre.getText().isEmpty() || comboProveedor.getValue() == null) {
                AlertUtil.mostrarError("El nombre y el proveedor son obligatorios.");
                return;
            }

            Proveedor seleccionado = comboProveedor.getValue();

            Producto nuevo = new Producto();
            nuevo.setNombre(txtNombre.getText().trim());
            nuevo.setPrecio(Double.parseDouble(txtPrecio.getText()));
            nuevo.setStock(Integer.parseInt(txtStock.getText()));
            nuevo.setDescripcion("");

            nuevo.setIdProveedor(seleccionado.getId());

            productoService.agregarProducto(nuevo);

            AlertUtil.mostrarInfo("Producto guardado con éxito.");
            limpiarCampos();
            actualizarTablaProductos();

        } catch (NumberFormatException e) {
            AlertUtil.mostrarError("Precio y Stock deben ser números válidos.");
        } catch (Exception e) {
            AlertUtil.mostrarError("Error al guardar: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtPrecio.clear();
        txtStock.clear();
        comboProveedor.getSelectionModel().clearSelection();
    }
}