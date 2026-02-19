package sv.ferreteria.gestorferreteria.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import sv.ferreteria.gestorferreteria.utils.AlertUtil;

import java.io.IOException;

public class IndexController {

    @FXML private StackPane contentArea;

    @FXML
    private void showProductos() {
        loadView("/sv/ferreteria/gestorferreteria/ui/productos/ProductosView.fxml");
    }

    @FXML
    private void showProveedores() {
        loadView("/sv/ferreteria/gestorferreteria/ui/proveedores/ProveedoresView.fxml");
    }

    @FXML
    private void showVentas() {
        loadView("/sv/ferreteria/gestorferreteria/ui/ventas/VentasView.fxml");
    }


    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent view = loader.load();

            contentArea.getChildren().setAll(view);

        } catch (IOException e) {
            e.printStackTrace();
            AlertUtil.mostrarError("No se pudo cargar la vista: " + fxmlPath + "\nError: " + e.getMessage());
        } catch (NullPointerException e) {
            AlertUtil.mostrarError("Error: No se encontró el archivo FXML en la ruta: " + fxmlPath);
        }
    }
}