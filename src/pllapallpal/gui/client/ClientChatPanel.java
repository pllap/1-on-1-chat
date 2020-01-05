package pllapallpal.gui.client;

import pllapallpal.gui.model.ClientModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.function.Consumer;

public class ClientChatPanel {

    private JPanel chatPanel;

    private JTextField chatMessage;
    private JButton sendButton;

    private Consumer<String> refreshChatLog;

    private ClientModel clientModel;

    public ClientChatPanel(ClientModel clientModel) {

        chatPanel = new JPanel(new BorderLayout(5, 5));
        this.clientModel = clientModel;

        chatMessage = new JTextField();
        chatMessage.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        chatMessage.setBorder(BorderFactory.createLineBorder(Color.black));
        chatMessage.requestFocus();
        chatMessage.addActionListener(e -> {
            if (!chatMessage.getText().equals("")) {
                try {
                    refreshChatLog.accept(chatMessage.getText());
                    clientModel.getOutput().writeUTF(chatMessage.getText());
                    chatMessage.setText("");
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
                    refreshChatLog.accept(chatMessage.getText());
                    clientModel.getOutput().writeUTF(chatMessage.getText());
                    chatMessage.setText("");
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
