package scuola.esercitazione.filetransfer;

import java.net.DatagramSocket;
import java.net.InetAddress;

public abstract class Peer {

    protected DatagramSocket sock;

    public Peer() throws Exception {
        this.sock = new DatagramSocket();
    }

    public Peer(int port) throws Exception {
        this.sock = new DatagramSocket(port);
    }

    public abstract void getHandshake();

    public abstract void startSession();

    public void connect(InetAddress addr, int port) {
        sock.connect(addr, port);
    }
}
