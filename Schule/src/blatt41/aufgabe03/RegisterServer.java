package blatt41.aufgabe03;

import blatt41.aufgabe01.MessageHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class RegisterServer {
    int port;
    private String welcomeMessage;
    private ArrayList<String> user;
    private ArrayList<String> pass;
    private boolean loggedIn = false;

    private int mode;

    private final int WAIT = 0;
    private final int REGISTER = 1;
    private final int LOGIN = 2;
    private final int LOGOUT = 3;
    private final int ANNOUNCE = 4;

    public RegisterServer(int port, String message) {
        this.port = port;
        this.welcomeMessage = message;
        this.user = new ArrayList<>();
        this.pass = new ArrayList<>();


    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(this.port);
        Socket client = serverSocket.accept();

        communicate(client);
    }

    public void communicate(Socket client) throws IOException {
        this.mode = WAIT;
        MessageHandler mh = new MessageHandler(client);
        mh.sendMessage("WEL",this.welcomeMessage);

        String[] protocols = new String[]{"REG","LOG","OFF","ANC","NAM","PWD"};

        String[] received;

        while (true) {
            received = mh.receiveMessage();
            mh.checkProtocols(received, protocols);

            if (received[0].equals("REG")) {
                this.mode = REGISTER;
            } else if (received[0].equals("LOG")) {
                this.mode = LOGIN;
            } else if (received[0].equals("OFF")) {
                this.mode = LOGOUT;
            } else if (received[0].equals("ANC")) {
                this.mode = ANNOUNCE;
            }

            if (this.mode == REGISTER) {
                boolean acceptUser = false;

                String userTemp = "";
                String passTemp = "";

                while (true) {
                    //Erwarte NAM und PWD
                    received = mh.receiveMessage();
                    mh.checkProtocol(received, "NAM");

                    acceptUser = true;
                    //Check for availability
                    for (String s : user) {
                        if (s.equals(received[1])) {
                            mh.sendMessage("NAC","Username already in use");
                            acceptUser = false;
                        }
                    }
                    if (acceptUser) {
                        userTemp = received[1];
                        mh.sendMessage("ACC",userTemp + " accepted");
                        break;
                    }
                }

                while (true) {
                    received = mh.receiveMessage();
                    mh.checkProtocol(received, "PWD");

                    if (received[1].matches("^(?=.*[a-zA-Z])(?=.*\\\\d).{4,}$")) {
                        mh.sendMessage("ACC", "Password accepted");
                        passTemp = received[1];
                        break;
                    } else {
                        mh.sendMessage("NAC", "Password does not meet the security requirements");
                    }
                }

                //Register
                this.user.add(userTemp);
                this.pass.add(passTemp);
                mh.sendMessage("OK", "User was registered");
                this.mode = WAIT;
            }

            if (this.mode == LOGIN) {
                //erwarte NAM und PWD
                received = mh.receiveMessage();
                mh.checkProtocol(received, "NAM");
                String name = received[1];

                received = mh.receiveMessage();
                mh.checkProtocol(received, "PWD");
                String password = received[1];

                //Find correct username
                for (int i = 0; i < user.size(); i++) {
                    if (name.equals(user.get(i))) {
                        if (password.equals(pass.get(i))) {
                            //Correct
                            mh.sendMessage("ACC","You are logged in.");
                            this.loggedIn = true;
                            this.mode = WAIT;
                        }
                    }
                }
                if (this.loggedIn == false) {
                    //Not correct
                    mh.sendMessage("NAC","Credentials are false.");
                    this.mode = WAIT;
                }

            }

            if (this.mode == LOGOUT) {
                mh.sendMessage("OFF","Offline.");
                this.mode = WAIT;
                this.loggedIn = false;
                client.close();
                break;
            }

            if (this.mode == ANNOUNCE) {
                if (this.loggedIn == false) {
                    mh.sendMessage("NAC","You are not logged in.");
                } else {
                    this.welcomeMessage = received[1];
                    mh.sendMessage("ACC","Welcome Message changed!");
                }
                this.mode = WAIT;
            }
        }

    }
}
