package com.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import com.Users.User;
import com.AuthClient.Client;

public class LoginFrame extends JFrame implements ActionListener,MouseListener {

    private static final long serialVersionUID = 1L;
    Client client = null;
    User currentUser = null;
    Container container = getContentPane();
    JLabel titleLabel = new JLabel("CS Quiz Portal!!");
    JLabel userLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JCheckBox showPassword = new JCheckBox("Show Password");
    JLabel register = new JLabel("Register!");

    public LoginFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        // Setting location and Size of each components using setBounds() method.
        titleLabel.setBounds(50,50,300,40);
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);
        register.setBounds(150,450,80,30);
    }


    public void addComponentsToContainer() {
        // Adding each components to the Container
        container.setBackground(Color.white);
        container.add(titleLabel);
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.add(register);
    }

    // Overriding actionPerformed() method
    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        register.addMouseListener(this);
        titleLabel.setFont(new Font("Arial",Font.BOLD,30));
        titleLabel.setForeground(Color.blue);
        loginButton.setBackground(Color.GREEN);
        loginButton.setForeground(Color.white);
        resetButton.setBackground(Color.RED);
        resetButton.setForeground(Color.white);
        showPassword.setBackground(Color.YELLOW);
    }

    public void LoginUserUI(String username, String password) throws IOException {
        User loggedInUser = new User(username,password);
        client = new Client("127.0.0.1",5000,loggedInUser);
        currentUser = client.LoginUser();
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("Some action performed");
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = String.valueOf(passwordField.getPassword());
            try {
                LoginUserUI(userText, pwdText);
                if(currentUser!=null){
                    JOptionPane.showMessageDialog(this, "Login Successful");
                    HomeFrame frame = new HomeFrame(currentUser);
                    frame.setTitle("Login Form");
                    frame.setVisible(true);
                    frame.setBounds(10,10,370,600);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setResizable(false);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Login Unsuccessful");
                }
               
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
       //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
 
 
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        RegisterFrame frame = new RegisterFrame();
        frame.setTitle("Register");
        frame.setVisible(true);
        frame.setBounds(10,10,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    
}
