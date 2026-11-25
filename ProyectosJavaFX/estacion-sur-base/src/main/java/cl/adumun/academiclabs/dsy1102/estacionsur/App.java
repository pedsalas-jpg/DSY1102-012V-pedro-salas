package cl.adumun.academiclabs.dsy1102.estacionsur;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/cl/adumun/academiclabs/dsy1102/estacionsur/taller-view.fxml"));
        stage.setScene(new Scene(loader.load(), 880, 550));
        stage.setTitle("Gestión de Talleres - Estación Sur");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}


