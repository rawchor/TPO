package zad1.Client;

import zad1.transaction.ClientRequest;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Client extends GUI {
    private static ServerSocket serverSocketReceived;
    public static final String info = " [CLIENT]: ";
    public static ClientRequest clientRequest;
    private static String data;

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{

        InetAddress host = InetAddress.getLocalHost();
        int port = Integer.parseInt(args[0]);

        Thread guiThread = new Thread(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Translator");
                frame.getContentPane().add(new GUI().getUI());
                frame.setSize(400, 300);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        guiThread.start();

        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        setLangCode(null);
        setWord(null);
        setDataReceived(null);

        // sending the object to main server
        while (true) {

            if (guiThread.isAlive()) {

            }else if(guiThread.isInterrupted()){
                guiThread.start();
            }

            while (getWord()==null && GUI.getLangCode()==null) {
                guiThread.sleep(150);
            }

            socket = new Socket(host.getHostName(), port);

            oos = new ObjectOutputStream(socket.getOutputStream());

            clientRequest = new ClientRequest(getWord(), getLangCode(), socket.getLocalPort());
            clientRequest.setHost(host);
            oos.writeObject(clientRequest);
            oos.close();

            if (socket.isBound()) {
                socket.close();
            }
            // waiting for responce from server
            System.out.println(info + "waiting for data on port : " + clientRequest.getPort());

            serverSocketReceived = new ServerSocket(clientRequest.getPort());

            Socket socketReceived = serverSocketReceived.accept();

            ObjectInputStream oiss = new ObjectInputStream(socketReceived.getInputStream());

            data = (String) oiss.readObject();

            oiss.close();

            setDataReceived(data);

            Thread.sleep(150);


            if (socketReceived.isBound()) {
                socketReceived.close();
            }

            setWord(null);
            setLangCode(null);
            setDataReceived(null);
        }
    }
}
