package pllapallpal.gui.server;

import pllapallpal.gui.model.ServerModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.function.Consumer;

public class ServerChatPanel {

    private JPanel chatPanel;

    private JTextField textField;
    private JButton sendButton;

    private Consumer<String> sendMessage;

    public ServerChatPanel(ServerModel serverModel) {

        chatPanel = new JPanel(new BorderLayout(5, 5));

        textField = new JTextField();
        textField.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        textField.setBorder(BorderFactory.createLineBorder(Color.black));
        textField.requestFocus();
        textField.addActionListener(e -> {
            if (!textField.getText().equals("")) {
                try {
                    sendMessage.accept(textField.getText());
                    serverModel.getOutput().writeUTF(textField.getText());
                    textField.setText("");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        sendButton = new JButton(" Send ");
        sendButton.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        sendButton.setBorder(BorderFactory.createLineBorder(Color.black));
        sendButton.addActionListener(e -> {
            if (!textField.getText().equals("")) {
                try {
                    sendMessage.accept(textField.getText());
                    serverModel.getOutput().writeUTF(textField.getText());
                    textField.setText("");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        chatPanel.add(textField, BorderLayout.CENTER);
        chatPanel.add(sendButton, BorderLayout.EAST);
    }

    public void addRefreshChatLog(Consumer<String> sendMessage) {
        this.sendMessage = sendMessage;
    }

    public JPanel getPanel() {
        return chatPanel;
    }
}
