package scuola.esercitazione.filetransfer;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.print.attribute.standard.Media;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class UIManager {

    static public Stage primaryStage;
    static public Peer user;

    public Timeline TimelinePulsating;

    Label labelTitolo;

    @FXML
    public TextField targetIPTextArea;
    @FXML
    public Button startFileTransmissionButton;
    @FXML
    public Label pusherFilePathLabel;
    @FXML
    public Label pullerIPLabel;
    @FXML
    private Label LabelTitolo;
    @FXML
    private SplitPane SpltPaneMenù;
    @FXML
    private Button PullerButton;
    @FXML
    private Button PusherButton;
    @FXML
    private Pane PaneMenù;


    // UI MENU
    @FXML
    private void selectedPuller() {
        try {
            if(TimelinePulsating != null)
                TimelinePulsating.stop();
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
            if(TimelinePulsating != null)
                TimelinePulsating.stop();
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

    public void PulsatingText(){

        labelTitolo = (Label) UIManager.primaryStage.getScene().getRoot().lookup("#LabelTitolo");

        TimelinePulsating = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(labelTitolo.scaleXProperty(), 1), new KeyValue(labelTitolo.scaleYProperty(), 1)),
            new KeyFrame(Duration.seconds(0.5), new KeyValue(labelTitolo.scaleXProperty(), 1.5), new KeyValue(labelTitolo.scaleYProperty(), 1.5)),
            new KeyFrame(Duration.seconds(1), new KeyValue(labelTitolo.scaleXProperty(), 1), new KeyValue(labelTitolo.scaleYProperty(), 1))
        );
        TimelinePulsating.setCycleCount(Timeline.INDEFINITE);
        TimelinePulsating.setAutoReverse(true);
        TimelinePulsating.play();

    }

    public void StartAnimation(){

        LabelTitolo.setLayoutY(-200);

        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(600), 
                new KeyValue(LabelTitolo.layoutYProperty(), 160, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(900), 
                new KeyValue(LabelTitolo.layoutYProperty(), 100, Interpolator.EASE_IN)),
            new KeyFrame(Duration.millis(1200), 
                new KeyValue(LabelTitolo.layoutYProperty(), 150, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(1500), 
                new KeyValue(LabelTitolo.layoutYProperty(), 120, Interpolator.EASE_IN)),
            new KeyFrame(Duration.millis(1700), 
                new KeyValue(LabelTitolo.layoutYProperty(), 150, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(1850), 
                new KeyValue(LabelTitolo.layoutYProperty(), 140, Interpolator.EASE_IN)),
            new KeyFrame(Duration.millis(2000), 
                new KeyValue(LabelTitolo.layoutYProperty(), 150, Interpolator.EASE_IN))
        );

        timeline.play();
        
        PauseTransition pause = new PauseTransition(Duration.seconds(3)); 
            pause.setOnFinished(event2 -> {
                timeline.stop();
        });
        
        pause.play();

    }

    @FXML
    void ActivateSplitPane(MouseEvent event) {

        PaneMenù.setOnMouseClicked(null);

        labelTitolo = (Label) UIManager.primaryStage.getScene().getRoot().lookup("#LabelTitolo");
        PullerButton.setLayoutX(-100);
        PullerButton.setLayoutY(272);
        PusherButton.setLayoutX(1100);
        PusherButton.setLayoutY(272);

        Timeline Timeline = new Timeline(
            new KeyFrame(Duration.millis(600), 
                new KeyValue(labelTitolo.layoutYProperty(), 5, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(1200), 
                new KeyValue(labelTitolo.layoutYProperty(), 20, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(1200), event1 -> {
                    PullerButton.setVisible(true);
                }),
                new KeyFrame(Duration.millis(1200), event1 -> {
                    PusherButton.setVisible(true);
                }),
            new KeyFrame(Duration.millis(1600), 
                new KeyValue(PullerButton.layoutXProperty(), 100, Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(1600), 
                new KeyValue(PusherButton.layoutXProperty(), 600, Interpolator.EASE_OUT))
        );
        
        Timeline.play();

        PauseTransition pause = new PauseTransition(Duration.seconds(3)); 
            pause.setOnFinished(event2 -> {
                Timeline.stop();
        });

        pause.play();
        

    }
}
