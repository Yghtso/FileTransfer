package scuola.esercitazione.filetransfer;

import java.io.FileInputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
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

    private static MessageDigest hashFile(Path filePath) {
        String hash = new String();

        MessageDigest digest = MessageDigest.getInstance(algorithm);

        try (FileInputStream fis = new FileInputStream(filePath.toFile())) {
            byte[] buffer = new byte[1024]; // Buffer size
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
        }

        byte[] checksumBytes = digest.digest();

        return hash;
    }
}
