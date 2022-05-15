package il.client;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import il.client.SimpleClient;
import javafx.stage.StageStyle;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.io.IOException;

public class App extends Application {
    private SimpleClient client = null;
    @Override
    public void start(Stage stage) throws IOException {
        try{
            client = SimpleClient.getClient();
            client.openConnection();

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainPage.fxml"));
            Parent mainLayout = fxmlLoader.load();
            Scene scene = new Scene(mainLayout, 1280, 720);
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            stage.setTitle("Catalog");
            stage.setScene(scene);
            stage.initStyle(StageStyle.DECORATED);
            stage.show();
        }
        catch (Exception e){
            client.closeConnection();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}