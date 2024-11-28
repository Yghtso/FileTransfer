package scuola.esercitazione.filetransfer;

import javafx.fxml.FXML;

public class UIManager {
    
    @FXML
    private void selectedPuller() {
        System.out.println("Ora sei il puller");
    }

    @FXML
    private void selectedPusher() {
        System.out.println("Ora sei il pusher");
    }
}
