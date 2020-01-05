package pllapallpal.gui.netio;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Receiver implements Runnable {

    JTextArea chatLog;
    DataInputStream input;
    String name;

    BufferedWriter writer;

    public Receiver(String name, DataInputStream input, JTextArea chatLog) {
        this.name = name;
        this.input = input;
        this.chatLog = chatLog;

        try {
            writer = new BufferedWriter(new FileWriter("out.txt", false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                chatLog.append("[" + name + "] " + input.readUTF() + "\n");
                if (name.equals("Server")) {
                    writer.write(chatLog.getText());
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
