package pllapallpal.gui.client;

import pllapallpal.gui.model.ClientModel;
import pllapallpal.gui.netio.Receiver;

import javax.swing.*;
import java.awt.*;

public class ClientMainFrame {

    private JFrame mainFrame;

    private MenuBar menuBar;
    private JPanel mainPanel;
    private JTextArea chatLog;
    private ClientChatPanel chatPanel;

    private ClientModel clientModel;
    private Thread receiveThread;

    public ClientMainFrame() {
        mainFrame = new JFrame("Client");

        mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainFrame.getContentPane().add(mainPanel);


        chatLog = new JTextArea("Connected to Server");
        chatLog.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        chatLog.setBorder(BorderFactory.createLineBorder(Color.black));
        chatLog.setEditable(false);
        chatLog.setLineWrap(true);
        mainPanel.add(chatLog, BorderLayout.CENTER);

        clientModel = new ClientModel();
        receiveThread = new Thread(new Receiver("Server", clientModel.getInput(), chatLog));
        receiveThread.start();

        chatPanel = new ClientChatPanel(clientModel);
        chatPanel.addRefreshChatLog(this::refreshChatLog);
        mainPanel.add(chatPanel.getPanel(), BorderLayout.SOUTH);


        menuBar = new MenuBar(chatLog);
        mainFrame.setJMenuBar(menuBar.getMenuBar());

        mainFrame.setSize(800, 600);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    public void refreshChatLog(String line) {
        chatLog.append("\nClient: " + line);
    }

    public static void main(String[] args) {
        new ClientMainFrame();
    }
}
