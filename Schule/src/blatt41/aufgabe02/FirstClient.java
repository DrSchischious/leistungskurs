package blatt41.aufgabe02;

import blatt41.aufgabe01.Nachricht;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class FirstClient {

    int port;
    String zielAdresse;

    public FirstClient(int port, String zielAdresse) {
        this.port = port;
        this.zielAdresse = zielAdresse;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setZielAdresse(String zielAdresse) {
        this.zielAdresse = zielAdresse;
    }

    public void connect() throws IOException {
        Socket socket = new Socket(zielAdresse, port);

        communicate(socket);
    }

    public void communicate(Socket client) throws IOException {
        Nachricht n = new Nachricht();
        Nachricht m = n.receiveMessage(client);
        if (!m.getProtokoll().equals("GRE")) {
            client.close();
            return;
        }


        n.setMessage("GRE","Hallo");
        n.sendMessage(client);

        m = n.receiveMessage(client);
        if (!m.getProtokoll().equals("QST")) {
            client.close();
            return;
        }
        System.out.println(m.prepareMessage());

        Scanner sc = new Scanner(System.in);
        String k = sc.nextLine();

        n.setMessage("ANS",k);
        n.sendMessage(client);

        m = n.receiveMessage(client);
        if (!m.getProtokoll().equals("FIN")) {
            client.close();
            return;
        }

        System.out.println(m.prepareMessage());

        n.setMessage("FIN","Danke.");

        System.out.println("Kommunikation erfolgreich beendet.");
        sc.close();
        client.close();

    }
}
