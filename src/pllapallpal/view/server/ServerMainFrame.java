package pllapallpal.view.server;

import javax.swing.*;

public class ServerMainFrame {

    private JFrame mainFrame;

    public ServerMainFrame() {
        mainFrame = new JFrame("Server");



        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new ServerMainFrame();
    }
}
