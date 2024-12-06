package scuola.esercitazione.filetransfer;

import java.net.InetAddress;

public class Pusher extends Peer {

    private String fileAbsolutePath;
    private String fileName;

    public Pusher() throws Exception {
        super();
    }

    public void setFile(String fileAbsolutePath, String fileName) {
        this.fileAbsolutePath = fileAbsolutePath;
        this.fileName = fileName;
    }

    public void lockTarget(InetAddress addr, int port) {
        super.sock.connect(addr, port);
    }

    public boolean targetAquired() {
        return super.sock.isConnected();
    }

    public boolean isFileSelected() {
        return this.fileAbsolutePath != null;
    }

    @Override
    public void startSession() {
        Thread transmitterThread = new Thread(() -> {
            this.file = new File(this.fileAbsolutePath);
            boolean handShakeObtained = getHandshake();

            if (handShakeObtained) {

            } else {
                System.out.println("Handshake failed");
            }
        });

        transmitterThread.start();
    }

    @Override
    public void getHandshake() {

    }
}
