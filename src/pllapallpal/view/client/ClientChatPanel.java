package pllapallpal.view.client;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ClientChatPanel {

    private JPanel chatPanel;

    private JTextField textField;
    private JButton sendButton;

    private Consumer<String> sendMessage;

    public ClientChatPanel() {

        chatPanel = new JPanel(new BorderLayout(5, 5));

        textField = new JTextField();
        textField.setBorder(BorderFactory.createLineBorder(Color.black));
        textField.requestFocus();
        textField.addActionListener(e -> {
            if (!textField.getText().equals("")) {
                sendMessage.accept(textField.getText());
                textField.setText("");
            }
        });
        sendButton = new JButton("Send");
        sendButton.setBorder(BorderFactory.createLineBorder(Color.black));
        sendButton.addActionListener(e -> {
            if (!textField.getText().equals("")) {
                sendMessage.accept(textField.getText());
                textField.setText("");
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
