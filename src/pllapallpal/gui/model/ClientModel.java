package pllapallpal.gui.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientModel {

    private final String ADDRESS = "127.0.0.1";
    private final int PORT = 11111;

    private Socket socket;

    private DataInputStream input;
    private DataOutputStream output;

    public ClientModel() {
        try {
            socket = new Socket(ADDRESS, PORT);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataInputStream getInput() {
        return input;
    }

    public DataOutputStream getOutput() {
        return output;
    }
}
