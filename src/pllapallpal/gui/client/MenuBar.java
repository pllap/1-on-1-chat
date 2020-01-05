package pllapallpal.gui.client;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MenuBar {

    private final String PATH = "out.txt";

    private JMenuBar menuBar;

    private JMenu fileMenu;
    private JMenu editMenu;

    private JMenuItem save;
    private JMenuItem load;
    private JMenuItem exit;

    private JMenuItem clear;
    private JMenuItem selectAll;

    private JTextArea chatLog;

    public MenuBar(JTextArea chatLog) {

        this.chatLog = chatLog;

        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        save = new JMenuItem("Save");
        load = new JMenuItem("Load");
        exit = new JMenuItem("Exit");
        fileMenu.add(save);
        fileMenu.add(load);
        fileMenu.add(exit);

        editMenu = new JMenu("Edit");
        clear = new JMenuItem("Clear");
        selectAll = new JMenuItem("Select All");
        editMenu.add(clear);
        editMenu.add(selectAll);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);


        save.addActionListener(e -> {
            saveToFile(chatLog.getText());
        });
        load.addActionListener(e -> {
            loadFromFile(chatLog);
        });
        exit.addActionListener(e -> {
            int input = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Message", JOptionPane.YES_NO_OPTION);
            if (input == 0) {
                System.exit(0);
            }
        });

        clear.addActionListener(e -> {
            chatLog.setText("");
        });
        selectAll.addActionListener(e -> {
            chatLog.requestFocus();
            chatLog.selectAll();
        });
    }

    public void saveToFile(String text) {
        if (!text.equals("")) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(PATH));
                writer.write(text);
                Desktop.getDesktop().edit(new File(PATH));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadFromFile(JTextArea chatLog) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PATH));
            chatLog.setText("");
            String str;
            while ((str = reader.readLine()) != null) {
                chatLog.append(str + "\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
