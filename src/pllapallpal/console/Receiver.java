package pllapallpal.console;

import java.io.DataInputStream;
import java.io.IOException;

public class Receiver implements Runnable {

    DataInputStream input;

    public Receiver(DataInputStream input) {
        this.input = input;
    }

    @Override
    public void run() {
        String line = "";
        while (!line.equals("Over")) {
            try {
                line = input.readUTF();
                System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
