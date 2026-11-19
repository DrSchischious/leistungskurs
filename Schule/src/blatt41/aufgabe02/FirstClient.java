package blatt41.aufgabe02;

import blatt41.aufgabe01.MessageHandler;

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

        MessageHandler mh = new MessageHandler(client);
        String[] received = mh.receiveMessage();
        mh.checkProtocol(received,"GRE");


        mh.sendMessage("GRE","Hallo");

        received = mh.receiveMessage();
        mh.checkProtocol(received,"QST");

        Scanner sc = new Scanner(System.in);
        String k = sc.nextLine();

        mh.sendMessage("ANS",k);

        received = mh.receiveMessage();
        mh.checkProtocol(received,"FIN");

        mh.sendMessage("FIN","Danke.");

        System.out.println("Kommunikation erfolgreich beendet.");

        sc.close();
        client.close();

    }
}
