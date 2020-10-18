package com.UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.Questions.Question;
import com.Questions.ReportQuiz;
import com.Users.User;

public class QuizFrame extends JFrame implements ActionListener, MouseListener {

    private static final long serialVersionUID = 1L;
    private Integer QuestionIndex = 0;
    private ArrayList<Question> arr = null;
    private ArrayList<ReportQuiz> results = new ArrayList<>();
    private Integer score = 0;
    private User user = null;
    Container container = getContentPane();
    JPanel centerPanel = new JPanel(new GridLayout(2, 2));
    JPanel questionPanel = new JPanel(new GridLayout(2, 16));
    JTextArea questionField = new JTextArea("Question");
    JButton option1 = new JButton("option1");
    JButton option2 = new JButton("option2");
    JButton option3 = new JButton("option3");
    JButton option4 = new JButton("option4");
    JButton back = new JButton("Back");
    JButton finish = new JButton("Finish");
    JButton next = new JButton("Next");
    JLabel timer = new JLabel("Timer");
    Timer clock;
    int counter = 30;
    ArrayList<JLabel> qOptionsList = new ArrayList<>(16);

    public QuizFrame(ArrayList<Question> arr, User user) {
        this.arr = arr;
        this.user = user;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        displayQuestion(arr);
    }

    public QuizFrame(PriorityQueue<Question> q, User user) {
        this.user = user;
        this.arr = new ArrayList<>(15);
        for (Question question : q) {
            arr.add(question);
        }
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        displayQuestion(arr);
    }

   
    public void setLayoutManager() {
        container.setLayout(null);
    }

   
    public void setLocationAndSize() {
        // Setting location and Size of each components using setBounds() method.
        back.setBounds(20, 20, 70, 30);
        timer.setBounds(250, 20, 60, 30);
        questionPanel.setBounds(30, 60, 300, 60);
        questionField.setBounds(40, 130, 260, 150);
        centerPanel.setBounds(40, 300, 260, 150);
        finish.setBounds(110, 480, 120, 30);
    }

    public void addComponentsToContainer() {
        // Adding each components to the Container
        container.setBackground(Color.yellow);
        for (Integer i = 1; i <= 15; i++) {
            JLabel button = new JLabel(i.toString());
            button.setOpaque(true);
            button.setBackground(Color.LIGHT_GRAY);
            button.setBorder(BorderFactory.createLineBorder(Color.black));
            button.setHorizontalAlignment(SwingConstants.CENTER);
            button.addMouseListener(this);
            button.setFont(new Font("Arial", Font.PLAIN, 10));
            qOptionsList.add(button);
            questionPanel.add(qOptionsList.get(i - 1));
        }
        centerPanel.add(option1);
        centerPanel.add(option2);
        centerPanel.add(option3);
        centerPanel.add(option4);
        container.add(back);
        container.add(timer);
        container.add(questionField);
        container.add(centerPanel, SwingConstants.CENTER);
        container.add(questionPanel, SwingConstants.CENTER);
        container.add(finish, SwingConstants.CENTER);
    }

    // Overriding actionPerformed() method
    public void addActionEvent() {
        questionPanel.setBackground(Color.yellow);

        timer.setOpaque(true);
        timer.setForeground(Color.WHITE);
        timer.setHorizontalAlignment(SwingConstants.CENTER);
        timer.setBackground(Color.RED);

        questionField.setOpaque(true);
        questionField.setBackground(Color.white);
        questionField.setEditable(false);
        questionField.setLineWrap(true);
        questionField.setWrapStyleWord(true);
        questionField.setFont(new Font("Arial",Font.PLAIN,15));

        option1.setOpaque(true);
        option1.setBackground(Color.white);
        option1.addActionListener(this);

        option2.setOpaque(true);
        option2.setBackground(Color.white);
        option2.addActionListener(this);

        option3.setOpaque(true);
        option3.setBackground(Color.white);
        option3.addActionListener(this);

        option4.setOpaque(true);
        option4.setBackground(Color.white);
        option4.addActionListener(this);

        finish.addActionListener(this);
        back.addActionListener(this);

        clock = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.setText(String.valueOf(counter));
                counter--;
                if (counter == 0) {
                    //timer.removeActionListener(this);
                      clock.stop();
                      gotoReportView();
                }
            }
        });
        clock.start();
    }

    public void displayQuestion(ArrayList<Question> arr) {
        HashMap<String, String> questionMap = arr.get(QuestionIndex).getQuestion();
        System.out.println(questionMap.size());
        questionField.setText(questionMap.get("Question").toString());
        option1.setText(questionMap.get("Option1").toString());
        option2.setText(questionMap.get("Option2").toString());
        option3.setText(questionMap.get("Option3").toString());  // question to be displayed
        option4.setText(questionMap.get("Option4").toString());
        qOptionsList.get(QuestionIndex).setBackground(Color.YELLOW);
    }

    public void gotoReportView(){
        clock.stop();
        ReportFrame frame = new ReportFrame(results,score,user);
        frame.setTitle("Your Results");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        this.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == finish) {
            gotoReportView();
            return;
        }
        if (e.getSource() == back) {
            HomeFrame frame = new HomeFrame(user);
            frame.setTitle("Play Quiz");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            this.dispose();
            return;
        }
        qOptionsList.get(QuestionIndex).setBackground(Color.GREEN);  // option is chosen
        HashMap<String,String> answeredQuestion = arr.get(QuestionIndex).getQuestion();
        if(option1.getText().toString().equals(answeredQuestion.get("Answer"))){
            score+=1;
            ReportQuiz result = new ReportQuiz(answeredQuestion.get("Question"),
                option1.getText().toString(),answeredQuestion.get("Answer"),1);
            results.add(result);
        }
        else{
            ReportQuiz result = new ReportQuiz(answeredQuestion.get("Question"),
                option1.getText().toString(),answeredQuestion.get("Answer"),0);
            results.add(result);
        }
        this.QuestionIndex = (this.QuestionIndex+1)%15;
        displayQuestion(arr);  // display next question
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        qOptionsList.get(QuestionIndex).setBackground(Color.LIGHT_GRAY);
        JLabel label = (JLabel)e.getSource();
        int index = Integer.parseInt(label.getText());
        QuestionIndex = index-1;
        displayQuestion(arr);
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
        JLabel label = (JLabel)e.getSource();
        if(label.getBackground()==Color.lightGray)
            label.setBackground(Color.CYAN);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        JLabel label = (JLabel)e.getSource();
        if(label.getBackground()==Color.CYAN)
            label.setBackground(Color.LIGHT_GRAY);
    }

}

