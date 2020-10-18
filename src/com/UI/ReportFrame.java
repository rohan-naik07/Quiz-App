package com.UI;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.AuthClient.Client;
import com.Questions.ReportQuiz;
import com.Users.User;

//import jdk.nashorn.internal.runtime.regexp.joni.ast.ConsAltNode;

import java.awt.Container;
import java.awt.Font;

public class ReportFrame extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    Container container = getContentPane();
    Client client = null;
    JLabel resultScore = new JLabel("Your Score");
    JLabel HighestScore = new JLabel("Highest Score");
    JButton playAgain = new JButton("Play Again");
    ArrayList<ReportQuiz> results = null;

    Integer score;
    User user = null;

    ReportFrame(ArrayList<ReportQuiz> results, Integer score, User user) {
        this.results = results;
        this.score = score;
        this.user = user;
        this.client = new Client("127.0.0.1",5000,user);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        resultScore.setBounds(50,30,100,40);
        HighestScore.setBounds(50,90,200,30);
        playAgain.setBounds(100,120,100,40);
    }

    public void addComponentsToContainer() {
        container.setBackground(Color.white);
        container.add(this.resultScore);
        container.add(this.HighestScore);
        container.add(this.playAgain);
        this.pack();
    }

    public void addActionEvent() {
        resultScore.setFont(new Font("Arail",Font.BOLD,20));
        resultScore.setText("Your Score is " + score.toString());

        if(score > user.getHighestScore()){
            user.setHighestscore(score);
            HighestScore.setText("Congrats!! Your new highest score is : " + score.toString());
            client.setHighestScore(score,user);
        }
        else HighestScore.setText("Your score is : " + score.toString());
        playAgain.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        this.dispose();
        HomeFrame frame = new HomeFrame(user);
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10,10,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}

