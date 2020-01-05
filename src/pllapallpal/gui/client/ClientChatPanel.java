package pllapallpal.gui.client;

import pllapallpal.gui.model.ClientModel;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Consumer;

public class ClientChatPanel {

    private JPanel chatPanel;

    private JTextField chatMessage;
    private JButton sendButton;

    private Consumer<String> refreshChatLog;

    private BufferedWriter writer;

    public ClientChatPanel(ClientModel clientModel) {

        chatPanel = new JPanel(new BorderLayout(5, 5));

        try {
            writer = new BufferedWriter(new FileWriter("out.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        chatMessage = new JTextField();
        chatMessage.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        chatMessage.setBorder(BorderFactory.createLineBorder(Color.black));
        chatMessage.requestFocus();
        chatMessage.addActionListener(e -> {
            if (!chatMessage.getText().equals("")) {
                try {
                    String message = chatMessage.getText();
                    chatMessage.setText("");
                    refreshChatLog.accept(message);
                    writer.write(message);
                    writer.flush();
                    clientModel.getOutput().writeUTF(message);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

        sendButton = new JButton(" Send ");
        sendButton.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        sendButton.setBorder(BorderFactory.createLineBorder(Color.black));
        sendButton.addActionListener(e -> {
            if (!chatMessage.getText().equals("")) {
                try {
                    String message = chatMessage.getText();
                    chatMessage.setText("");
                    refreshChatLog.accept(message);
                    writer.write(message);
                    clientModel.getOutput().writeUTF(message);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

        chatPanel.add(chatMessage, BorderLayout.CENTER);
        chatPanel.add(sendButton, BorderLayout.EAST);
    }

    public void addRefreshChatLog(Consumer<String> sendMessage) {
        this.refreshChatLog = sendMessage;
    }

    public JPanel getPanel() {
        return chatPanel;
    }
}
