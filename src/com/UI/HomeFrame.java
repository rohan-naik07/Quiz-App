package com.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import com.Users.User;
import com.AuthClient.Client;
import com.Questions.Question;
import com.Questions.QuestionList;

public class HomeFrame extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;
    private static final String Instructions = "You will be given 15 questions based on random CS topics";
    private QuestionList queslist = null;
    ArrayList<Question> question_list = null;
    PriorityQueue<Question> queue = null;
    User currentUser = null;
    QuizFrame frame = null;
    Container container = getContentPane();
    JLabel homeLabel = new JLabel("USERNAME");
    JLabel categoryLabel = new JLabel("Please choose category : ");
    JLabel topicsLabel = new JLabel("Please choose your Topic");
    JLabel scoreLabel = new JLabel("");
    JTextArea randomIns = new JTextArea(Instructions);
    JButton topicWise = new JButton("TOPIC WISE");
    JButton random = new JButton("RANDOM FIRE UP");
    JButton DSA = new JButton("Data Structues");
    JButton OOP = new JButton("Object Oriented Programming");
    JButton CN = new JButton("Computer Networking");
    JButton DB = new JButton("Database Management");
    JButton proceed  = new JButton("Proceed");
    JButton back = new JButton("Back");

    public HomeFrame(User user) {
        this.currentUser = user;
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
       
        homeLabel.setBounds(90,30, 200, 60);
        scoreLabel.setBounds(90,60,250,60);
        categoryLabel.setBounds(105, 150, 200, 60);
        topicsLabel.setBounds(105, 150, 200, 60);
        topicWise.setBounds(105, 220, 150, 40);
        random.setBounds(105, 280, 150, 40);
        DSA.setBounds(105, 220, 150, 40);
        OOP.setBounds(105, 280, 150, 40);
        DB.setBounds(105, 340, 150, 40);
        CN.setBounds(105, 400, 150, 40);
        randomIns.setBounds(20, 180, 300, 30);
        proceed.setBounds(105, 280, 150, 40);
        back.setBounds(105,500,150,40);
    }
    public void clearViews(){
        this.categoryLabel.setVisible(false);
        this.topicWise.setVisible(false);
        this.random.setVisible(false);
        this.topicsLabel.setVisible(false);
        this.DSA.setVisible(false);
        this.OOP.setVisible(false);
        this.DB.setVisible(false);
        this.CN.setVisible(false);
        this.randomIns.setVisible(false);
        this.proceed.setVisible(false);
    }
    public void setCategoryView(){
        this.categoryLabel.setVisible(true);
        this.topicWise.setVisible(true);
        this.random.setVisible(true);
    }
    public void setTopicsView(){
        this.topicsLabel.setVisible(true);
        this.DSA.setVisible(true);
        this.OOP.setVisible(true);
        this.DB.setVisible(true);
        this.CN.setVisible(true);
    }
    public void setRandomView(){
        this.randomIns.setVisible(true);
        this.proceed.setVisible(true);
    }
    public void addComponentsToContainer() {
        // Adding each components to the Container
        container.setBackground(Color.white);
        container.add(homeLabel);
        container.add(categoryLabel);
        container.add(topicsLabel);
        container.add(scoreLabel);
        container.add(topicWise);
        container.add(random);
        container.add(DSA);
        container.add(OOP);
        container.add(DB);
        container.add(CN);
        container.add(randomIns);
        container.add(proceed);
        container.add(back);
        randomIns.setEditable(false);
        randomIns.setLineWrap(true);
        randomIns.setWrapStyleWord(true);
        clearViews();
        setCategoryView();
    }

    // Overriding actionPerformed() method
    public void addActionEvent() {
        homeLabel.setForeground(Color.blue);
        homeLabel.setFont(new Font("Arail",Font.BOLD,20));
        try{
            homeLabel.setText("WELCOME " + currentUser.getUsername().toUpperCase());
        }catch(Exception e){
            homeLabel.setText("WELCOME User");
        }
        try{
            scoreLabel.setText("Highest Score is " + currentUser.getHighestScore().toString());
            scoreLabel.setFont(new Font("Arial",Font.BOLD,20));
        } catch(Exception e){
            scoreLabel.setText("Highest Score");
        }
        
        topicWise.addActionListener(this);
        random.addActionListener(this); 
        back.addActionListener(this);
        DSA.addActionListener(this);
        OOP.addActionListener(this);
        DB.addActionListener(this);
        CN.addActionListener(this);
        proceed.addActionListener(this);
        back.setBackground(Color.RED);
    }

    public void setQuizView(PriorityQueue<Question> q,ArrayList<Question> arr){
        if(q==null){
            this.frame = new QuizFrame(arr,currentUser);
        } else {
            this.frame = new QuizFrame(q,currentUser);
        }
        frame.setTitle("Play Quiz");
        frame.setVisible(true);
        frame.setBounds(10,10,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        this.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("Some action performed");
        if (e.getSource() == random) {
            clearViews();
            setRandomView();
        }
        //Coding Part of RESET button
        if (e.getSource() == topicWise) {
            clearViews();
            setTopicsView();
        }
        if(e.getSource()==back){
            clearViews();
            setCategoryView();
        }
        if(e.getSource()==DSA){
            this.queslist = new QuestionList("DSA");
            this.question_list = queslist.getList();
            setQuizView(null,this.question_list);
        }
        if(e.getSource()==OOP){
            this.queslist = new QuestionList("OOP");
            this.question_list = queslist.getList();
            setQuizView(null,this.question_list);
        }
        if(e.getSource()==DB){
            this.queslist = new QuestionList("DB");
            this.question_list = queslist.getList();
            setQuizView(null,this.question_list);
        }
        if(e.getSource()==CN){
            this.queslist = new QuestionList("CN");
            this.question_list = queslist.getList();
            setQuizView(null,this.question_list);
        }
        if(e.getSource()==proceed){
            this.queslist = new QuestionList("");
            this.queue = queslist.getRandomList();
            setQuizView(this.queue,null);
        }
    }
}
