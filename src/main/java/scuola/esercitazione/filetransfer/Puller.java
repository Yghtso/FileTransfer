package scuola.esercitazione.filetransfer;

import scuola.esercitazione.Packet;

public class Puller extends Peer {

    public Puller() throws Exception {
        super(Peer.PROTOCOL_DEFAULT_PORT);
    }

    @Override
    public void startSession() {
        Thread receiverThread = new Thread(() -> {
            boolean handShakeObtained = getHandshake();

            if (handShakeObtained) {
                System.out.println("Handshake succes");
            } else {
                System.out.println("Handshake failed");
            }
        });

        receiverThread.start();
    }

    @Override
    public boolean getHandshake() {
        try {
            sock.setSoTimeout(5000);

            Packet synPacket;
            do {
                synPacket = receivePacket();
            } while (synPacket == null);
            
            return true;
        } catch (Exception e) {
            
        }
        return false;
    }
}
