package blatt41.aufgabe03;

import java.io.IOException;

public class MainClient {
    public static void main(String[] args) throws IOException {
        RegisterClient rc = new RegisterClient(520, "Schischtop");
        rc.connect();
    }
}
