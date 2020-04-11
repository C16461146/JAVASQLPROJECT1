//package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


class Login{
    static Database db=new Database();
    static JFrame frame = new JFrame("PC Shop");

    //static GUI gui =new GUI();

    public static void addComponentsToPane(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        JLabel loginStatus = new JLabel(" ");
        JLabel usernameLabel = new JLabel("ID:");
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(usernameLabel);

        JTextField usernameField = new JTextField(20);
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(usernameField);

        usernameField.setText("C11421156");

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(passwordField);

        passwordField.setText("test");

        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(loginButton);
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String sql="SELECT `ppsn_FK` FROM `employee login` WHERE ID = '"+usernameField.getText()+"' and password='"+passwordField.getText()+"'";
                ResultSet rs=db.checkLogin(sql);
                //String status
                //System.out.println("status : " + status);
                //loginStatus.setText(status);
                if(rs!=null){
                    frame.dispose();
                    GUI gui=new GUI();
                    gui.showUI(rs);
                }
                else{
                    loginStatus.setText("Incorrect login");
                }
            }
        });

        loginStatus.setForeground(Color.red);
        loginStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(loginStatus);
    }

    public static void createAndShowGUI() {
        //Create and set up the window.

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String args[]){
        /*javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });*/
        //GUI gui =new GUI();
        //gui.createUIComponents();


    }
}
