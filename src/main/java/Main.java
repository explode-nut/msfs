import client.Client;
import client.Shell;

public class Main {
    public static void main(String[] args) {
        Client client = new Shell();
        client.run();
    }
}
