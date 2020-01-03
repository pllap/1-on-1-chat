package pllapallpal.console;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sender implements Runnable {

    DataOutputStream output;
    BufferedReader bufferedReader;

    public Sender(DataOutputStream output) {
        this.output = output;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {

        String line = "";
        while (!line.equals("Over")) {
            try {
                line = bufferedReader.readLine();
                output.writeUTF(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
