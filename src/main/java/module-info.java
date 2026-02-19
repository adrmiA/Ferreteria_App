module sv.ferreteria.gestorferreteria {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;

    opens sv.ferreteria.gestorferreteria.ui.productos to javafx.fxml;
    opens sv.ferreteria.gestorferreteria.ui.proveedores to javafx.fxml;
    opens sv.ferreteria.gestorferreteria.ui.ventas to javafx.fxml;
    opens sv.ferreteria.gestorferreteria.models to javafx.base, com.google.gson;
    opens sv.ferreteria.gestorferreteria to javafx.fxml;

    exports sv.ferreteria.gestorferreteria;
    exports sv.ferreteria.gestorferreteria.ui;
    opens sv.ferreteria.gestorferreteria.ui to javafx.fxml;
}
