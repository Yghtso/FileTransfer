package scuola.esercitazione.filetransfer;

import java.net.DatagramSocket;
import java.net.InetAddress;

public abstract class Peer {
    
    static final int BYTES_TRASFER_PORT = 61100;
    private DatagramSocket commandsSocket;
    private DatagramSocket rawFilesSocket;

    public abstract void startHandshake();

    public void connect(InetAddress addr, int port) {
        commandsSocket.connect(addr, port);
        rawFilesSocket.connect(addr, BYTES_TRASFER_PORT);
    }
}
