package pllapallpal.gui.client;

import javax.swing.*;

public class MenuBar {

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
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
