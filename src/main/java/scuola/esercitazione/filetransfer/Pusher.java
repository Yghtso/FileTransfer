package scuola.esercitazione.filetransfer;

import java.io.FileInputStream;

public class Pusher extends Peer {

    String fileAbsolutePath;
    String fileName;

    public Pusher() throws Exception {
        super();
    }

    public void setFile(String fileAbsolutePath, String fileName) {
        this.fileAbsolutePath = fileAbsolutePath;
        this.fileName = fileName;
    }

    @Override
    public void startSession() {
        Thread transmitterThread = new Thread(() -> {
            getHandshake();
            System.out.println("Handshake avvenuta");
        });
    }

    @Override
    public void getHandshake() {

    }
}
