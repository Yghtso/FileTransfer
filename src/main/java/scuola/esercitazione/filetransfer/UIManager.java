package scuola.esercitazione.filetransfer;

import javafx.fxml.FXML;
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

    }

    @FXML
    private void selectTargetButtonClicked() {
        
    }



    // Render delle diverse scene
    private void renderMainMenu() {
        
    }

    private void renderPuller() {

    }

    private void renderPusher() {

    }

}
