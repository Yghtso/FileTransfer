package scuola.esercitazione.filetransfer;

import java.io.FileInputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.file.Path;
import java.security.MessageDigest;

public abstract class Peer {

    protected DatagramSocket sock;

    public Peer() throws Exception {
        this.sock = new DatagramSocket();
    }

    public Peer(int port) throws Exception {
        this.sock = new DatagramSocket(port);
    }

    public abstract boolean getHandshake();

    public abstract void startSession();

    public void connect(InetAddress addr, int port) {
        sock.connect(addr, port);
    }

    protected static byte[] hashFile(Path filePath) {
        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            try (FileInputStream fis = new FileInputStream(filePath.toFile())) {
                byte[] buffer = new byte[8192];
                int bytesRead;
    
                while ((bytesRead = fis.read(buffer)) != -1) {
                    digest.update(buffer, 0, bytesRead);
                }
            }

            return digest.digest();

        } catch (Exception e) {
            return null;
        }
    }
}
