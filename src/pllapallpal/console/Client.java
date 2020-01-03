package pllapallpal.console;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    Socket socket;

    DataInputStream input;
    DataOutputStream output;

    Thread sendThread;
    Thread receiveThread;

    public Client(String ip, int port) {

        try {
            socket = new Socket(ip, port);
            System.out.println("Connected to server");


            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

            sendThread = new Thread(new Sender(output));
            receiveThread = new Thread(new Receiver(input));

            sendThread.start();
            receiveThread.start();


            receiveThread.join();

            socket.close();
            sendThread.interrupt();
            receiveThread.interrupt();
            System.out.println("Connection closed");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 11111);
    }
}
