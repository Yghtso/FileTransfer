package scuola.esercitazione.filetransfer;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.security.MessageDigest;
import scuola.esercitazione.Packet;

public abstract class Peer {

    protected DatagramSocket sock;
    protected InetAddress selfAddress;
    public static final int PROTOCOL_DEFAULT_PORT = 61100;

    public Peer(int port) throws Exception {
        this.sock = new DatagramSocket(port);
        try {   this.selfAddress = InetAddress.getLocalHost();  }
        catch (UnknownHostException e) {  e.printStackTrace();  }
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

    protected boolean sendPacket(Packet packet) {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    
        try {
  
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
            objectStream.writeObject(packet);
            objectStream.flush();
            byte[] data = byteStream.toByteArray();

            DatagramPacket udpPacket = new DatagramPacket(data, data.length);
            sock.send(udpPacket);
            return true;
  
        } catch (IOException e) {
            return false;
        }
    }
}
