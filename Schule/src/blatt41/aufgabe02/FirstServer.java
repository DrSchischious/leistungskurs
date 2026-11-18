package blatt41.aufgabe02;

import blatt41.aufgabe01.Nachricht;

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
        Nachricht n = new Nachricht("GRE","Hallo");
        n.sendMessage(client);

        Nachricht m = n.receiveMessage(client);
        if (!m.getProtokoll().equals("GRE")) {
            client.close();
            return;
        }
        System.out.println(m.prepareMessage());

        n.setMessage("QST","Wie geht es dir?");
        n.sendMessage(client);

        m = n.receiveMessage(client);
        if (!m.getProtokoll().equals("ANS")) {
            client.close();
            return;
        }

        System.out.println(m.prepareMessage());

        n.setMessage("FIN","Danke.");

        m = n.receiveMessage(client);
        if (!m.getProtokoll().equals("FIN")) {
            client.close();
            return;
        }
        System.out.println("Kommunikation erfolgreich beendet.");
        client.close();


    }
}
