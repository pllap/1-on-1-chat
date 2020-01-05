package pllapallpal.gui.netio;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;

public class Receiver implements Runnable {

    JTextArea chatLog;
    DataInputStream input;
    String name;

    public Receiver(String name, DataInputStream input, JTextArea chatLog) {
        this.name = name;
        this.input = input;
        this.chatLog = chatLog;
    }

    @Override
    public void run() {
        while (true) {
            try {
                chatLog.append("[" + name + "] " + input.readUTF() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
