package scuola.esercitazione.filetransfer;

import java.io.FileInputStream;

public class Pusher extends Peer{
    
    FileInputStream fileToSend;

    public Pusher(FileInputStream fileToSend) throws Exception {
        super();
        this.fileToSend = fileToSend;
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
