package sv.ferreteria.gestorferreteria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/sv/ferreteria/gestorferreteria/ui/IndexView.fxml")
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("Gestor de Ferretería");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
