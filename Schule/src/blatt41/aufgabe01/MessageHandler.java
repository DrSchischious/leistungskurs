package blatt41.aufgabe01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageHandler {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private int mode;

    private final int SHOW_NOTHING = 1;
    private final int SHOW_RECEIVED = 2;
    private final int SHOW_SENT = 3;
    private final int SHOW_ALL = 4;

    public MessageHandler() {
        this.socket = null;
        this.out = null;
        this.in = null;
        this.mode = SHOW_ALL;
    }

    public MessageHandler(Socket socket) throws IOException {
        this.setSocket(socket);
        this.mode = SHOW_ALL;
    }

    public void setMode(int mode) {
        if (mode < SHOW_NOTHING || mode > SHOW_ALL) {
            throw new IllegalArgumentException("Invalid mode");
        }
        this.mode = mode;
    }

    private String prepareMessage(String protokoll, String message) {
        if (!protokoll.equals("")) {
            String msg = protokoll + "|" + message;
            return msg;
        } else {
            return null;
        }
    }

    private String getProtokoll(String msg) {
        if (isValid(msg)) {
            String[] s = msg.split("|");
            return s[0];
        } else {
            return null;
        }
    }

    private String getNachricht(String msg) {
        if (isValid(msg)) {
            String[] s = msg.split("|");
            if (s.length == 2) {
                return s[1];
            } else {
                return "";
            }
        } else {
            return null;
        }
    }

    private boolean isValid(String message) {
        if (message.indexOf("|") > 0) {
            return true;
        } else {
            return false;
        }
    }


    public void setSocket(Socket socket) throws IOException {
        if (this.socket == null) {
            this.socket = socket;
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
    }

    public void sendMessage(String protocol, String message) throws IOException {
        if (this.socket != null && !protocol.equals("")) {
            String s = this.prepareMessage(protocol, message);
            out.println(s);
            if (mode == SHOW_ALL || mode == SHOW_SENT) {
                System.out.println("-->: " + s);
            }
        }
    }

    public String[] receiveMessage() throws IOException {
        String s = in.readLine();
        if (mode == SHOW_ALL || mode == SHOW_ALL) {
            System.out.println("<--: " + s);
        }
        if (isValid(s)) {

            return new String[]{s.substring(0,s.indexOf("|")), s.substring(s.indexOf("|")+1,s.length())};
        } else {
            return null;
        }
    }

    public void checkProtocol(String[] message, String protocol) throws IOException {

        if (!message[0].equals(protocol)) {
            this.socket.close();
            throw new IOException();
        }
    }

    public void checkProtocols(String[] message, String[] protocol) throws IOException {
        for (int i = 0; i < protocol.length; i++) {
            if (message[0].equals(protocol[i])) {
                return;
            }
        }
        this.socket.close();
        throw new IOException();
    }



}
