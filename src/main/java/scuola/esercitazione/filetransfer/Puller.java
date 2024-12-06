package scuola.esercitazione.filetransfer;

public class Puller extends Peer {

    public Puller() throws Exception {
        super(Peer.PROTOCOL_DEFAULT_PORT);
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
    public boolean getHandshake() {
        // TODO : fare sock.connect(addr);
        // quando si riceve l' indirizzo dell altro peer
        return false;
    }
}
