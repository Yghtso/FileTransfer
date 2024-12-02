package scuola.esercitazione.filetransfer;

public class Puller extends Peer {

    public static final int PROTOCOL_DEFAULT_PORT = 61100;

    public Puller() throws Exception {
        super(Puller.PROTOCOL_DEFAULT_PORT);
    }

    @Override
    public void startSession() {
        Thread receiverThread = new Thread(() -> {
            getHandshake();
            System.out.println("Handshake avvenuta");
        });
    }

    @Override
    public void getHandshake() {

    }
}
