package ru.ifmo.se;

import ru.ifmo.se.enums.CommandTypes;
import ru.ifmo.se.mbean.ServerCommandsCounter;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainPanel extends JFrame {
    JMenu menu;
    JMenuBar jMenuBar;
    JMenuItem jMenuItem;
    JLabel label;
    JLabel resLabel;
    JTextField textField;
    JTree jTree;
    JButton addButton;
    JButton remButton;
    JPanel jPanel;
    Container container;
    DefaultTreeModel model;
    DefaultMutableTreeNode root;
    GroupLayout groupLayout;


    public MainPanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Lab 7. ServerSide");
        createMenu();
        container = getContentPane();
        jPanel = new JPanel(new BorderLayout());
        groupLayout = new GroupLayout(jPanel);
        groupLayout.getAutoCreateGaps();
        container.add(jPanel);
        root = new DefaultMutableTreeNode("People");
        jTree = new JTree(root);
        Connection.filemaker();
        try{
            Connection.load();
        } catch (IOException e){
            e.printStackTrace();
        }
        updateTree();
        model = (DefaultTreeModel) jTree.getModel();
        createOptions();
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup()
                        .addComponent(jTree).addGap(100)
                        .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(label).addGap(10)
                        .addComponent(textField).addGap(10)
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(addButton).addGap(10)
                                .addComponent(remButton)).addGap(10)
                        .addComponent(resLabel)));
        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addComponent(jTree).addGap(100)
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label).addGap(10)
                        .addComponent(textField).addGap(10)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(addButton).addGap(10)
                                .addComponent(remButton)).addGap(10)
                        .addComponent(resLabel)));

        model.reload();
        groupLayout.linkSize(textField);
        jPanel.setLayout(groupLayout);
        pack();
        setVisible(true);
    }

    public void updateTree(){ //to google: how to update jtree
        root.removeAllChildren();
        Server.collec.forEach(person -> root.add(new DefaultMutableTreeNode(person.toString())));
        jTree.updateUI();
        jPanel.updateUI();
    }

    public void createMenu(){
        jMenuBar = new JMenuBar();
        menu = new JMenu("Menu");
        jMenuItem = new JMenuItem("Load collection from the file (Current collection will be lost)");
        jMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent a) {
                Connection.filemaker();
                try{
                    Connection.clear();
                    Connection.load();
                } catch (IOException e){
                    e.printStackTrace();
                }
                ServerCommandsCounter.recordCommands(CommandTypes.LOAD_FROM_FILE);
                updateTree();
            }
        });
        menu.add(jMenuItem);
        jMenuItem = new JMenuItem("Load current collection");
        jMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent a) {
                updateTree();
                ServerCommandsCounter.recordCommands(CommandTypes.LOAD_CURRENT);
            }
        });
        menu.add(jMenuItem);
        jMenuItem = new JMenuItem("Save current collection to the file");
        jMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent a) {
                Connection.saveOnQuit();
                ServerCommandsCounter.recordCommands(CommandTypes.SAVE);
            }
        });
        menu.add(jMenuItem);
        jMenuItem = new JMenuItem("Clear current collection");
        jMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent a) {
                Connection.clear();
                ServerCommandsCounter.recordCommands(CommandTypes.CLEAR);
                updateTree();
            }
        });
        menu.add(jMenuItem);
        jMenuBar.add(menu);
        setJMenuBar(jMenuBar);
    }

    public void createOptions(){
        label = new JLabel("Object to add/Remove objects greater than:");
        resLabel = new JLabel();
        textField = new JTextField("{\"name\":\"Andy\"}",15);
        addButton = new JButton("Add object");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string = textField.getText();
                resLabel.setText(Connection.addObject(string));
                ServerCommandsCounter.recordCommands(CommandTypes.ADD);
                updateTree();
            }
        });
        remButton = new JButton("Remove greater objects");
        remButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string = textField.getText();
                resLabel.setText(Connection.removeGreater(string));
                ServerCommandsCounter.recordCommands(CommandTypes.REMOVE);
                updateTree();
            }
        });
    }
}
