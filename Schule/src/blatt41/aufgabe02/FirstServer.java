package blatt41.aufgabe02;

import blatt41.aufgabe01.MessageHandler;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FirstServer {
    int port;

    public FirstServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(this.port);
        Socket client = serverSocket.accept();

        communicate(client);
    }

    public void communicate(Socket client) throws IOException {
        MessageHandler mh = new MessageHandler(client);

        mh.sendMessage("GRE", "Hallo");

        String[] received = mh.receiveMessage();
        mh.checkProtocol(received, "GRE");

        mh.sendMessage("QST","Wie geht es dir?");

        received = mh.receiveMessage();
        mh.checkProtocol(received, "ANS");

        mh.sendMessage("FIN","Danke");

        received = mh.receiveMessage();
        mh.checkProtocol(received, "FIN");

        System.out.println("Kommunikation erfolgreich beendet.");
        client.close();


    }
}
