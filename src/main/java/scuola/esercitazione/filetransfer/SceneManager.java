package scuola.esercitazione.filetransfer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {

    private Map<String, Parent> scenes = new HashMap<>();

    public void loadScenes() throws IOException {
        scenes.put("MainMenù", loadScene("/fxml/MainMenu.fxml"));
        scenes.put("Puller", loadScene("/fxml/Puller.fxml"));
        scenes.put("Pusher", loadScene("/fxml/Pusher.fxml"));
        
    }

    private Parent loadScene(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        return loader.load();
    }

    public void switchToScene(String sceneName, Stage stage) {
        Parent root = scenes.get(sceneName);
        if (root != null) {
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } else {
            System.out.println("Scene non trovata: " + sceneName);
        }
    }
}
