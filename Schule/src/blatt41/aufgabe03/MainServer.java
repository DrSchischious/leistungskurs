package blatt41.aufgabe03;

import java.io.IOException;

public class MainServer {
    public static void main(String[] args) throws IOException {
        RegisterServer rs = new RegisterServer(520, "Willkommen!");
        rs.start();
    }
}
