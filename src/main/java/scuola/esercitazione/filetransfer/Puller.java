package scuola.esercitazione.filetransfer;

public class Puller extends Peer {

    public static final int PROTOCOL_DEFAULT_PORT = 61100;

    public Puller() throws Exception {
        super(Puller.PROTOCOL_DEFAULT_PORT);
    }

    @Override
    public void startSession() {
        Thread receiverThread = new Thread(() -> {
            boolean handShakeObtained = getHandshake();

            if (handShakeObtained) {

            } else {
                System.out.println("Handshake failed");
            }
        });

        receiverThread.start();
    }

    @Override
    public void getHandshake() {

    }
}
