package blatt41;

public class Message {
    private String protocol;
    private String msg;

    public Message(String protocol, String msg) {
        this.protocol = protocol;
        this.msg = msg;
    }

    public Message(String msg) {
        String [] str = msg.split("|");
        this.protocol = str[0];
        this.msg = str[1];
    }

    public String getForm() {
        return protocol+"|"+msg;
    }


}
