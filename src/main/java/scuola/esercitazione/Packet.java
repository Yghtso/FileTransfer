package scuola.esercitazione;

import java.net.InetAddress;

public class Packet {

    private byte[] fileCheckSum;
    private long fileNumberBytes;
    private String fileName;
    private boolean syn;
    private boolean synAck;
    private byte[] filePayload;
    private InetAddress senderAddress;

    public Packet(byte[] fileCheckSum, long fileNumberBytes, String fileName, boolean syn, boolean synAck, byte[] filePayload, InetAddress senderAddress) {
        this.fileCheckSum = fileCheckSum;
        this.fileNumberBytes = fileNumberBytes;
        this.fileName = fileName;
        this.syn = syn;
        this.synAck = synAck;
        this.filePayload = filePayload;
        this.senderAddress = senderAddress;
    }
}
