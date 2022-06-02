package zad1;

import zad1.languageServers.DeLanguageServer;
import zad1.languageServers.EngLanguageServer;
import zad1.languageServers.RuLanguageServer;
import zad1.transaction.AvailablePort;
import zad1.transaction.ClientRequest;
import zad1.transaction.TranslatingRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class PROXYServer {
    private static ServerSocket serverSocket;
    private static int port;
    public static int newPort = AvailablePort.findFreePort();
    private static final String info = " [SERVER MAIN]: ";

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        InetAddress host = InetAddress.getLocalHost();
        port = Integer.parseInt(args[0]);
        serverSocket = new ServerSocket(port);

        while (true) {

            System.out.println(info + "waiting for Client request on port " + port);

            Socket socket = serverSocket.accept();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ClientRequest clientRequest = null;
            clientRequest = (ClientRequest) ois.readObject();
            ois.close();
            System.out.println(info + "data received on " + port + " port");

            if (clientRequest.getLanguageCode().equals("en")) {

                Thread serverThread = new Thread(() -> {
                    try {
                        new EngLanguageServer().start(newPort);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });

                serverThread.start();
                sendToLangServ(clientRequest,host.getHostName());

            }else if (clientRequest.getLanguageCode().equals("de")) {

                Thread serverThread = new Thread(() -> {
                    try {
                        new DeLanguageServer().start(newPort);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });

                serverThread.start();
                sendToLangServ(clientRequest, host.getHostName());

            }else if (clientRequest.getLanguageCode().equals("ru")) {

                Thread serverThread = new Thread(() -> {
                    try {
                        new RuLanguageServer().start(newPort);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });

                serverThread.start();
                sendToLangServ(clientRequest, host.getHostName());

            }else {
                /*Socket socket_returnData = new Socket(host.getHostName(), clientRequest.getPort());
                System.out.println(info + "I do not recognize language code, sending data to client  " + socket_returnData.getPort());
                ObjectOutputStream objos = new ObjectOutputStream(socket_returnData.getOutputStream());
                objos.writeObject("I do not recognize language code");
                objos.close();

                if (socket_returnData.isBound()) {
                    socket_returnData.close();
                }*/
            }
        }
    }

    public static void sendToLangServ(ClientRequest cr, String hostName ) throws IOException {
        Socket socketLang = new Socket(hostName, newPort);
        System.out.println(info + "data sent on " + newPort + " port");
        TranslatingRequest tr = new TranslatingRequest(cr.getWordToTranslate(),cr.getHost().getAddress(),cr.getPort());
        ObjectOutputStream oos = new ObjectOutputStream(socketLang.getOutputStream());
        oos.writeObject(tr);
        oos.close();
    }
}
