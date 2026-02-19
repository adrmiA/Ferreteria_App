package sv.ferreteria.gestorferreteria.ui.ventas;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import sv.ferreteria.gestorferreteria.models.Producto;
import sv.ferreteria.gestorferreteria.models.Venta;
import sv.ferreteria.gestorferreteria.services.ProductoService;
import sv.ferreteria.gestorferreteria.services.VentaService;
import sv.ferreteria.gestorferreteria.utils.AlertUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class VentasController implements Initializable {

    @FXML private ComboBox<Producto> cbProducto;
    @FXML private TextField txtCantidad;
    @FXML private TableView<Venta> tableVentas;

    @FXML private TableColumn<Venta, Integer> colId;
    @FXML private TableColumn<Venta, String> colProducto; // Columna para el nombre
    @FXML private TableColumn<Venta, String> colFecha;
    @FXML private TableColumn<Venta, Integer> colCantidad;
    @FXML private TableColumn<Venta, Double> colTotal;

    private final VentaService ventaService = new VentaService();
    private final ProductoService productoService = new ProductoService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTabla();
        configurarComboProducto();
        cargarDatos();
    }

    private void configurarTabla() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadVendida"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        // LÓGICA ANIDADA (Igual a proveedores)
        colProducto.setCellValueFactory(cellData -> {
            Producto p = cellData.getValue().getProducto();
            String nombre = (p != null) ? p.getNombre() : "N/A";
            return new SimpleStringProperty(nombre);
        });
    }

    private void configurarComboProducto() {
        cbProducto.setConverter(new StringConverter<Producto>() {
            @Override
            public String toString(Producto p) {
                return p == null ? "" : p.getNombre() + " (Stock: " + p.getStock() + ")";
            }
            @Override
            public Producto fromString(String s) { return null; }
        });
    }

    private void cargarDatos() {
        try {
            // Cargar productos para el combo
            cbProducto.setItems(FXCollections.observableArrayList(productoService.obtenerProductos()));
            // Cargar historial de ventas
            tableVentas.setItems(FXCollections.observableArrayList(ventaService.obtenerVentas()));
        } catch (Exception e) {
            AlertUtil.mostrarError("Error al cargar datos: " + e.getMessage());
        }
    }

    @FXML
    private void onRegistrarVenta() {
        Producto seleccionado = cbProducto.getValue();
        String cantStr = txtCantidad.getText();

        if (seleccionado == null || cantStr.isEmpty()) {
            AlertUtil.mostrarError("Debe seleccionar un producto e ingresar la cantidad.");
            return;
        }

        try {
            int cantidad = Integer.parseInt(cantStr);

            // Validación local de stock (Antes de ir al servidor)
            if (cantidad > seleccionado.getStock()) {
                AlertUtil.mostrarError("¡Stock insuficiente! Solo quedan " + seleccionado.getStock() + " unidades.");
                return;
            }

            // Llamada al API
            ventaService.registrarVenta(seleccionado.getId(), cantidad);

            AlertUtil.mostrarInfo("Venta registrada con éxito.");

            // Limpiar e hidratar la UI
            txtCantidad.clear();
            cargarDatos(); // Recarga productos (para actualizar stock en el combo) y ventas

        } catch (NumberFormatException e) {
            AlertUtil.mostrarError("La cantidad debe ser un número válido.");
        } catch (Exception e) {
            // Aquí capturamos el "Stock insuficiente" o "Producto no encontrado" que viene del API
            AlertUtil.mostrarError("No se pudo realizar la venta: " + e.getMessage());
        }
    }
}