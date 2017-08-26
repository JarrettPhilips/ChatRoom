package com.company;

import javax.swing.*;
import java.awt.*;

public class ServerUI {
    /*
        Variables
    */
    JFrame window = new JFrame("Server");
    JPanel masterPanel = new JPanel();
    JTextArea textArea = new JTextArea();
    JTextField inputField = new JTextField();
    JTextArea userList = new JTextArea();

    private GridBagConstraints c  = new GridBagConstraints();

    Server server;

    /*
        Constructors
    */
    public ServerUI(){
        setUp();
    }

    /*
        Functions
    */
    private void setUp(){
        setUpWindow();
    }

    private void setUpWindow(){
        //Sets up the window
        window.setSize(800, 500);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
        //window.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);
        masterPanel.setLayout(new GridBagLayout());

        //Sets up the player list panel
        resetc();
        masterPanel.add(userList, c);

        //Sets up the main text area and input fields
        resetc();


        //Adds master panel to window
        window.add(masterPanel);
    }

    //Resets all gridbaglayout constraints
    private void resetc(){
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.NONE;
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0;
        c.weighty = 0;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.LINE_START;
    }

    /*
        Testing Main
    */
    public static void main(String args[]){
        ServerUI server = new ServerUI();
    }
}
