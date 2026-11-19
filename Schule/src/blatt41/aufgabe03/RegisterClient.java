package blatt41.aufgabe03;

import blatt41.aufgabe01.MessageHandler;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class RegisterClient {

    int port;
    String zielAdresse;

    public RegisterClient(int port, String zielAdresse) {
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
        mh.setMode(4);
        String[] received = mh.receiveMessage();
        String[] protocols = new String[]{"ACC","NAC"};

        mh.checkProtocol(received,"WEL");

        boolean loggedIn = false;
        int choice = 0;
        Scanner sc = new Scanner(System.in);


        while (true) {
            System.out.println("Was möchtest du tun?");
            System.out.println("1 - Registrieren");
            System.out.println("2 - Einloggen");
            if (loggedIn == true) {
                System.out.println("3 - Ankündigung");
            }
            System.out.println("5 - Ausloggen");

            choice = sc.nextInt();

            if (choice == 1) {
                mh.sendMessage("REG","Register");
                String username;
                String password;

                while (true) {
                    System.out.println("Wähle einen Usernamen:");
                    username = sc.next();

                    mh.sendMessage("NAM",username);

                    received = mh.receiveMessage();
                    mh.checkProtocols(received, protocols);

                    if (received[0].equals("NAC")) {
                        System.out.println("Username ist bereits vergeben.");
                    } else if (received[0].equals("ACC")) {
                        System.out.println("Username akzeptiert.");
                        break;
                    }
                }

                while (true) {
                    System.out.println("Wähle ein geeignetes Passwort. (Mindestens ein Buchstabe und mindestens eine Ziffer");
                    password = sc.next();
                    mh.sendMessage("PWD",password);
                    received = mh.receiveMessage();
                    mh.checkProtocols(received, protocols);

                    if (received[0].equals("NAC")) {
                        System.out.println("Passwort wurde nicht akzeptiert.");
                    } else if (received[0].equals("ACC")) {
                        System.out.println("User wurde angelegt.");
                        break;
                    }
                }


            } else if (choice == 2) {
                mh.sendMessage("LOG","Login");
                System.out.println("Gib deinen Usernamen an.");
                String username = sc.next();
                System.out.println("Gib dein Passwort ein.");
                String password = sc.next();

                mh.sendMessage("NAM",username);
                mh.sendMessage("PWD",password);

                received = mh.receiveMessage();
                mh.checkProtocols(received, protocols);
                if (received[0].equals("ACC")) {
                    System.out.println("Login erfolgreich.");
                    loggedIn = true;
                } else if (received[0].equals("NAC")) {
                    System.out.println("Login war nicht erfolgreich.");
                    loggedIn = false;
                }


            } else if (choice == 3 && loggedIn == true) {

                System.out.println("Gib deine neue Willkommensnachricht an.");
                String welcome = sc.next();
                mh.sendMessage("ANC",welcome);
                received = mh.receiveMessage();
                mh.checkProtocol(received,"ACC");

            } else if (choice == 5) {
                mh.sendMessage("OFF","Ausloggen.");
                received = mh.receiveMessage();
                mh.checkProtocol(received, "OFF");

                System.out.println("Du wurdest erfolgreich ausgeloggt.");
                loggedIn = false;

                sc.close();
                client.close();
                break;


            } else {
                System.out.println("Diese Auswahl existiert nicht.");
            }


        }


    }
}
