package scuola.esercitazione.filetransfer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Paths;
import scuola.esercitazione.Packet;
import java.io.File;

public class Pusher extends Peer {

    private String fileAbsolutePath;
    private String fileName;
    private File file;

    public Pusher() throws Exception {
        super(Peer.PROTOCOL_DEFAULT_PORT);
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
                System.out.println("Handshake succes");
            } else {
                System.out.println("Handshake failed");
            }
        });

        transmitterThread.start();
    }

    @Override
    public boolean getHandshake() {
        try {
            sock.setSoTimeout(5000);

            Packet synPacket = new Packet(
                Peer.hashFile(Paths.get(fileAbsolutePath)),
                file.length(),
                fileName,
                true,
                false,
                null,
                this.selfAddress
            );
            
            boolean successSubmition;
            do {
                successSubmition = sendPacket(synPacket); 
            } while (!successSubmition);

        } catch (Exception e) {
            
        }
        return false;
    }
}
