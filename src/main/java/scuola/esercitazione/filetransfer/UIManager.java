package scuola.esercitazione.filetransfer;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UIManager {

    static public Stage primaryStage;
    static public Peer user;

    @FXML
    public TextField targetIPTextArea;
    @FXML
    public Button startFileTransmissionButton;
    @FXML
    public Label pusherFilePathLabel;
    @FXML
    public Label pullerIPLabel;

    // UI MENU
    @FXML
    private void selectedPuller() {
        try {
            UIManager.user = new Puller();
            renderPuller();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void selectedPusher() {
        try {
            UIManager.user = new Pusher();
            renderPusher();
        } catch (Exception e) {
        }
    }

    @FXML
    private void backToMainMenuButtonClicked() {
        user.sock.close();
        UIManager.user = null;
        renderMainMenu();
    }

    // UI PUSHER
    @FXML
    private void selectFileButtonClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            String fileAbsolutePath = selectedFile.getAbsolutePath();
            String fileName = selectedFile.getName();
            ((Pusher) UIManager.user).setFile(fileAbsolutePath, fileName);
            pusherFilePathLabel.setText(fileName);

            if (((Pusher) UIManager.user).targetAquired() && ((Pusher) UIManager.user).isFileSelected()) {
                startFileTransmissionButton.setDisable(false);
            }
        }
    }

    @FXML
    private void selectTargetButtonClicked() {
        String targetIP = targetIPTextArea.getText();

        try {
            UIManager.user.connect(InetAddress.getByName(targetIP), Puller.PROTOCOL_DEFAULT_PORT);
        } catch (UnknownHostException e) {
        }

        if (((Pusher) UIManager.user).targetAquired() && ((Pusher) UIManager.user).isFileSelected()) {
            startFileTransmissionButton.setDisable(false);
        }
    }

    @FXML
    private void startFileTransmissionButtonClicked() {
        UIManager.user.startSession();
    }

    // UI PULLER
    @FXML
    private void startListeningButtonClicked() {
        UIManager.user.startSession();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Puller.fxml"));
        Parent root;

        try {

            root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            InetAddress localHost = InetAddress.getLocalHost();
            ((Label) root.lookup("#pullerIPLabel"))
                    .setText(localHost.getHostAddress() + " : " + Puller.PROTOCOL_DEFAULT_PORT);

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    private void renderPusher() {

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

}
