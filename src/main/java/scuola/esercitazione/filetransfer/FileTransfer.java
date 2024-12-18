package scuola.esercitazione.filetransfer;

import java.lang.ModuleLayer.Controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FileTransfer extends Application {

    public SceneManager sceneManager = new SceneManager();
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("FileTransfer");
        primaryStage.show();
        primaryStage.setResizable(false);

        UIManager.primaryStage = primaryStage;

        sceneManager.loadScenes();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
