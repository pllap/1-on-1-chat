package pllapallpal.gui.server;

import pllapallpal.gui.client.ClientChatPanel;

import javax.swing.*;
import java.awt.*;

public class ServerMainFrame {

    private JFrame mainFrame;

    private JPanel mainPanel;
    private JTextArea chatLog;
    private ClientChatPanel chatPanel;

    public ServerMainFrame() {
        mainFrame = new JFrame("Client");

        mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainFrame.getContentPane().add(mainPanel);


        chatLog = new JTextArea();
        chatLog.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        chatLog.setBorder(BorderFactory.createLineBorder(Color.black));
        chatLog.setEditable(false);
        chatLog.setLineWrap(true);
        mainPanel.add(chatLog, BorderLayout.CENTER);

        chatPanel = new ClientChatPanel();
        chatPanel.addRefreshChatLog(this::refreshChatLog);
        mainPanel.add(chatPanel.getPanel(), BorderLayout.SOUTH);


        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    public void refreshChatLog(String line) {
        chatLog.append("\nServer: " + line);
    }

    public static void main(String[] args) {
        new ServerMainFrame();
    }
}
