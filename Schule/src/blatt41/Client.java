package blatt41;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("10.8.205.28",753);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner sc = new Scanner(System.in);
        while (true) {
            String protocol = sc.nextLine();
            String msg = sc.nextLine();

            String toSend = protocol + "|" + msg;
            out.println(toSend);


            socket.close();
            break;
        }
    }
}
