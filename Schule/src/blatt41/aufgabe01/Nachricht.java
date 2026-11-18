package blatt41.aufgabe01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Nachricht {
    String protokoll;
    String nachricht;


    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;


    public Nachricht() {
        this.protokoll = "";
        this.nachricht = "";
    }

    public Nachricht(String protokoll, String nachricht) {
        this.protokoll = protokoll;
        this.nachricht = nachricht;
    }

    public Nachricht(Nachricht nachricht) {
        this.protokoll = nachricht.protokoll;
        this.nachricht = nachricht.nachricht;
    }


    public String prepareMessage() {
        if (!this.protokoll.equals("")) {
            String msg = protokoll + "|" + nachricht;
            return msg;
        } else {
            return null;
        }
    }

    public String getProtokoll() {
        return protokoll;
    }
    public String getNachricht() {
        return nachricht;
    }

    public void setProtokoll(String protokoll) {
        this.protokoll = protokoll;
    }

    public void setNachricht(String nachricht) {
        this.nachricht = nachricht;
    }

    public void setMessage(String protokoll, String nachricht) {
        this.setProtokoll(protokoll);
        this.setNachricht(nachricht);
    }

    public String extractProtokoll(String msg) {
        if (isValid(msg)) {
            String[] s = msg.split("|");
            return s[0];
        } else {
            return null;
        }
    }

    public String extractNachricht(String msg) {
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

    public boolean isValid(String message) {
        if (message.indexOf("|") > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void setMessage(String msg) {
        if (isValid(msg)) {
            if (extractProtokoll(msg) != null) {
                this.protokoll = extractProtokoll(msg);
            }
            if (extractNachricht(msg) != null) {
                this.nachricht = extractNachricht(msg);
            }
        }
    }

    public void sendMessage(Socket socket) throws IOException {
        if (socket == null) {
            this.socket = socket;
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        out.println(this.prepareMessage());
    }

    public Nachricht receiveMessage(Socket socket) throws IOException {
        if (socket == null) {
            this.socket = socket;
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        String s = in.readLine();
        if (isValid(s)) {
            Nachricht n = new Nachricht(this.extractProtokoll(s), this.extractNachricht(s));
            return n;
        } else {
            return null;
        }


    }

}
