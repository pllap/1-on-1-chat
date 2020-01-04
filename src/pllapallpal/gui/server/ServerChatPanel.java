package pllapallpal.gui.server;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ServerChatPanel {

    private JPanel chatPanel;

    private JTextField textField;
    private JButton sendButton;

    private Consumer<String> sendMessage;

    public ServerChatPanel() {

        chatPanel = new JPanel(new BorderLayout(5, 5));

        textField = new JTextField();
        textField.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        textField.setBorder(BorderFactory.createLineBorder(Color.black));
        textField.requestFocus();
        textField.addActionListener(e -> {
            if (!textField.getText().equals("")) {
                sendMessage.accept(textField.getText());
                textField.setText("");
            }
        });

        sendButton = new JButton(" Send ");
        sendButton.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
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
