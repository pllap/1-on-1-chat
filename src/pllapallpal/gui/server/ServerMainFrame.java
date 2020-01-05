package pllapallpal.gui.server;

import pllapallpal.gui.netio.Receiver;
import pllapallpal.gui.model.ServerModel;

import javax.swing.*;
import java.awt.*;

public class ServerMainFrame {

    private JFrame mainFrame;

    private JPanel mainPanel;
    private JTextArea chatLog;
    private ServerChatPanel chatPanel;

    private ServerModel serverModel;
    private Thread receiveThread;

    public ServerMainFrame() {

        mainFrame = new JFrame("Server");

        mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainFrame.getContentPane().add(mainPanel);


        chatLog = new JTextArea("Client accepted");
        chatLog.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        chatLog.setBorder(BorderFactory.createLineBorder(Color.black));
        chatLog.setEditable(false);
        chatLog.setLineWrap(true);
        mainPanel.add(chatLog, BorderLayout.CENTER);


        System.out.println("waiting for client...");
        serverModel = new ServerModel();
        System.out.println("client accepted");

        receiveThread = new Thread(new Receiver("Client", serverModel.getInput(), chatLog));
        receiveThread.start();

        chatPanel = new ServerChatPanel(serverModel);
        chatPanel.addRefreshChatLog(this::refreshChatLog);
        mainPanel.add(chatPanel.getPanel(), BorderLayout.SOUTH);


        mainFrame.setSize(800, 600);
        mainFrame.setResizable(false);
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
