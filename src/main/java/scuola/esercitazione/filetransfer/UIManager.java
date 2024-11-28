package scuola.esercitazione.filetransfer;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UIManager {

    static Stage primaryStage;
    


    // UI MENU
    @FXML
    private void selectedPuller() {
        renderPuller();
    }

    @FXML
    private void selectedPusher() {
        renderPusher();
    }
    
    @FXML
    private void backToMainMenuButtonClicked() {
        renderMainMenu();
    }



    // UI PUSHER
    @FXML
    private void selectFileButtonClicked() {
        String fileAbsolutePath = null;
        String fileName = null;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            fileAbsolutePath = selectedFile.getAbsolutePath();
            fileName = selectedFile.getName();
        }
    }

    @FXML
    private void selectTargetButtonClicked() {
        
    }

    @FXML
    private void startFileTransmissionButtonClicked() {

    }

    // UI PULLER
    @FXML
    private void startListeningButtonClicked() {
        
    }


    // Render delle diverse scene
    private void renderMainMenu() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
        Parent root;

        try {

            root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

        } catch (IOException e) {

            e.printStackTrace();
        
        }
    }

    private void renderPuller() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Pusher.fxml"));
        Parent root;

        try {

            root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

        } catch (IOException e) {

            e.printStackTrace();
        
        }
    }

    private void renderPusher() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Puller.fxml"));
        Parent root;

        try {

            root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

        } catch (IOException e) {

            e.printStackTrace();
        
        }
    }

}
