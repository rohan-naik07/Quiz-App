package com.UI;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.AuthClient.Client;
import com.Users.User;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public class RegisterFrame  extends JFrame implements ActionListener{

    private static final long serialVersionUID = 1L;
    Client client = null;
    Container container = getContentPane();
    JLabel titleLabel = new JLabel("CS Quiz Portal!!");
    JLabel userLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");
    JLabel confirmpasswordLabel = new JLabel("Confirm Password");
    JLabel emailLabel = new JLabel("Email");
    JLabel numberLabel = new JLabel("Number");
    JTextField userTextField = new JTextField();
    JTextField emailTextField = new JTextField();
    JTextField numberTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JPasswordField confirmpasswordField = new JPasswordField();
    JButton registerButton = new JButton("Register");
    JButton resetButton = new JButton("Reset");
    JCheckBox showPassword = new JCheckBox("Show Password");
    JCheckBox showPassword2 = new JCheckBox("Show Confirmed Password");

    public RegisterFrame() {
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
        userTextField.setBounds(150, 150, 150, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        confirmpasswordLabel.setBounds(50, 300, 100, 30);
        confirmpasswordField.setBounds(150, 300, 150, 30);
        showPassword2.setBounds(150, 330, 150, 30);
        emailLabel.setBounds(50, 380, 100, 30);
        emailTextField.setBounds(150, 380, 150, 30);
        numberLabel.setBounds(50,430, 100, 30);
        numberTextField.setBounds(150, 430, 150, 30);
        registerButton.setBounds(50, 480, 100, 30);
        resetButton.setBounds(200,480, 100, 30);
        //register.setBounds(150,450,80,30);
    }


    public void addComponentsToContainer() {
        // Adding each components to the Container
        container.setBackground(Color.white);
        container.add(titleLabel);
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(confirmpasswordField);
        container.add(confirmpasswordLabel);
        container.add(emailTextField);
        container.add(this.emailLabel);
        container.add(this.numberLabel);
        container.add(this.numberTextField);
        container.add(showPassword);
        container.add(showPassword2);
        container.add(registerButton);
        container.add(resetButton);
    }

    // Overriding actionPerformed() method
    public void addActionEvent() {
        registerButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        titleLabel.setFont(new Font("Arial",Font.BOLD,30));
        titleLabel.setForeground(Color.blue);
        registerButton.setBackground(Color.GREEN);
        registerButton.setForeground(Color.white);
        resetButton.setBackground(Color.RED);
        resetButton.setForeground(Color.white);
        showPassword.setBackground(Color.YELLOW);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
            confirmpasswordField.setText("");
            emailTextField.setText("");
            numberTextField.setText("");
        }
       //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
 
        }
        if (e.getSource() == showPassword2) {
            if (showPassword2.isSelected()) {
                confirmpasswordField.setEchoChar((char) 0);
            } else {
                confirmpasswordField.setEchoChar('*');
            }
        }
        String username = userTextField.getText().toString();
        String password = passwordField.getPassword().toString();
        String confirmPassword = confirmpasswordField.getPassword().toString();
        String email = emailTextField.getText().toString();
        String number = numberTextField.getText().toString();

        if(!password.equals(confirmPassword)){
            JOptionPane.showMessageDialog(this, "Passwords do not match!");
            return;
        }
        User newUser = new User(username,email,password,Integer.parseInt(number));
        client = new Client("127.0.0.1", 5000,newUser);
        client.RegisterUser();
        JOptionPane.showMessageDialog(this, "Registration Successful!");
        this.dispose();
        LoginFrame frame = new LoginFrame();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10,10,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
    
}

/*
public static boolean Register(){
        String username,email,password,repassword;
        int telephone_no;
        System.out.println("Enter your username : ");
        username = scanner.next();
        System.out.println("Enter your email : ");
        email = scanner.next();
        System.out.println("Enter your number : ");
        telephone_no = scanner.nextInt();
        System.out.println("Enter your password : ");
        password = scanner.next();
        System.out.println("Confirm password : ");
        repassword = scanner.next();
        System.out.print('\u000C');
        if(password.equals(repassword)) {
        User newUser = new User(username,email,password,telephone_no);
        currentUser = newUser;
        client = new Client("127.0.0.1", 5000,newUser);
        client.RegisterUser();
        } else {
        	System.out.println("Passwords do not match");
        	return false;
        }
        return true;
    }
*/
