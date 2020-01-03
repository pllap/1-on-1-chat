package pllapallpal.console;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private Socket socket;
    private ServerSocket serverSocket;

    private DataInputStream input;
    private DataOutputStream output;

    private Thread sendThread;
    private Thread receiveThread;

    public Server(int port) {

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client...");

            socket = serverSocket.accept();
            System.out.println("Client accepted");

            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());


            sendThread = new Thread(new Sender(output));
            receiveThread = new Thread(new Receiver(input));
            sendThread.start();
            receiveThread.start();


            receiveThread.join();

            socket.close();
            serverSocket.close();
            sendThread.interrupt();
            receiveThread.interrupt();

            System.out.println("Connection closed");
            System.out.println("Server off");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(11111);
    }
}
