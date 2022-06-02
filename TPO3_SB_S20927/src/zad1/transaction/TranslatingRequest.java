package zad1.transaction;

import java.io.Serializable;
import java.util.Arrays;

public class TranslatingRequest implements Serializable {

    private int port;
    private String wordToTranslate;
    private byte[] clientAddress;

    public TranslatingRequest(String wordToTranslate, byte[] clientAdress, int port) {
        this.port = port;
        this.wordToTranslate = wordToTranslate;
        this.clientAddress = clientAdress;
    }

    public int getPort() {
        return port;
    }

    public String getWordToTranslate() {
        return wordToTranslate;
    }

    @Override
    public String toString() {

        return "TranslatingRequest{" +
                "port=" + port +
                ", wordToTranslate='" + wordToTranslate + '\'' +
                ", clientAdress=" + Arrays.toString(clientAddress) +
                '}';
    }
}
