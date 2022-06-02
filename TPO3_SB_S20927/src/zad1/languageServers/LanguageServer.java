package zad1.languageServers;
import zad1.transaction.TranslatingRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class LanguageServer  implements LanguageServerTemplate{
    private ServerSocket serverSocket;
    private final String info = " [SERVER LANG]: ";
    static TranslatingRequest translatingRequest = null;
    String translatedWord;

    public void start(int port ) throws IOException, ClassNotFoundException {

        InetAddress host = InetAddress.getLocalHost();

        System.out.println(info + "awaiting data on " + port + " port ");

            serverSocket = new ServerSocket(port);

            // Waiting for proxy to respond
            Socket socket = serverSocket.accept();
            if (serverSocket.isBound()) {
                System.out.println(info + "data received on " + port + " port");
            }

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            translatingRequest = (TranslatingRequest) ois.readObject();

            if(notFoundInDictionary()) {
                if(translatingRequest.getWordToTranslate().isEmpty()) {
                    translatedWord = "NOT ENTERED";
                }else {
                    translatedWord = "NOT FOUND";
                }
            }else {
                translatedWord = getWordFromDictionary(translatingRequest.getWordToTranslate());

            }

            ois.close();


        System.out.println(info + "sending data to " + translatingRequest.getPort() + " port ");

        // Sending the object with translated word to client
        while (true) {
            Socket returnInfo = new Socket(host.getHostName(), translatingRequest.getPort());
            ObjectOutputStream oos = new ObjectOutputStream(returnInfo.getOutputStream());
            oos.writeObject(translatedWord);
            oos.close();
            returnInfo.close();
            serverSocket.close();
            System.out.println(info + "word to translate: " + translatingRequest.getWordToTranslate() + ", translated word: " + translatedWord);
            System.out.println(info + "data sent, shutting down");

            if (returnInfo.isBound()) {
                break;
            }
        }
    }

    abstract public String getWordFromDictionary(String wordToTranslate) throws IOException;

    private boolean notFoundInDictionary() throws IOException {
        if (getWordFromDictionary(translatingRequest.getWordToTranslate())==null) {
            return true;
        }else {
            return false;
        }
    }
}