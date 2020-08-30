package com;
import java.util.*;
import java.io.*;
import com.Users.User;
import com.Questions.Question;
import com.Questions.QuestionList; 
import com.AuthClient.Client;
import com.Questions.ReportQuiz; 

/*
 * To do-
 * Server loop
 * Priority Queue
 * Add questions to database
 * Formatting 
 * */
public class Quiz{
     static Scanner scanner = new Scanner(System.in);
     static QuestionList queslist = null;
     static Client client = null;
     static User currentUser = null;
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
        User newUser = new User(username,email,password,telephone_no);
        currentUser = newUser;
        client = new Client("127.0.0.1", 5000,newUser);
        client.RegisterUser();
        return true;
    }
    public static boolean LogIn() throws IOException {
        String username,email,password;
        int number; 
        System.out.println("Enter your username : ");
        username = scanner.next();
        System.out.println("Enter your password : ");
        password = scanner.next();
        System.out.println("Enter your email : ");
        email = scanner.next();
        System.out.println("Enter your number : ");
        number = scanner.nextInt();
        User loggedInUser = new User(username,email,password,number);
        client = new Client("127.0.0.1",5000,loggedInUser);
        currentUser = loggedInUser;
        return client.LoginUser();
    }
    public static void Report(Integer score,ArrayList<ReportQuiz> report){
        
        if(score > currentUser.getHighestScore()){
            currentUser.setHighestscore(score);
            System.out.println("Congrats!! Your new highest score is : " + score.toString());
        }
        else System.out.print("Your score is : " + score.toString());
        for(ReportQuiz report_quiz : report){
            System.out.println(report_quiz.getQuestion());
            String answer = report_quiz.getCorrectAnswer();
            if(answer.equals(report_quiz.getGivenAnswer()))
             {
                System.out.println("Your Answer " + report_quiz.getGivenAnswer() +" is correct!" );
                System.out.println("Point " + report_quiz.getPoint());
             }
            else {
                System.out.println("Your Answer " + report_quiz.getGivenAnswer() +" is wrong!" );
                System.out.println("Correct Answer is " + report_quiz.getCorrectAnswer());
                System.out.println("Point 0");
            }
            System.out.println(""); 
        }
    }
    public static void playQuiz(){
        String answer;
        Integer score = 0;
        ArrayList<ReportQuiz> report = new ArrayList<ReportQuiz>();
        String categories[] = {"DSA","OOP","DB","CN"};
        System.out.println("1.Topic Wise Classic");
        System.out.println("2.Random Fire Up");
        switch(scanner.nextInt()){
            case 1:
                System.out.println("Please choose your category!");
                System.out.println("1.Data Structures and Algorithms");
                System.out.println("2.Object Oriented Programming");
                System.out.println("3.Database Management");
                System.out.println("4.Computer Networking");
                queslist = new QuestionList(categories[scanner.nextInt()-1]);
                ArrayList<Question> question_list = queslist.getList();
                System.out.print('\u000C');

                for(Question question : question_list){
                    HashMap<String,String> ques = question.getQuestion();
                    System.out.println(ques.get("Question"));
                    System.out.println(ques.get("Option1"));
                    System.out.println(ques.get("Option2"));
                    System.out.println(ques.get("Option3"));
                    System.out.println(ques.get("Option4"));
                    answer = scanner.next();
                    if(answer.equals(ques.get("Answer")))
                    {
                        System.out.println("Correct!");
                        score+=1;
                    }
                    else{
                        System.out.println("Wrong!");
                    }
                    report.add(new ReportQuiz(ques.get("Question"),answer,ques.get("Answer"),1));
                    System.out.println(""); 
                }
                Report(score,report);
                break;
            case 2:
                PriorityQueue<Question> queue = queslist.getRandomList();
                for(Question ques : queue){
                    HashMap<String,String> q = ques
                    .getQuestion();
                    System.out.println(q.get("Question"));
                    System.out.println(q.get("Option1"));
                    System.out.println(q.get("Option2"));
                    System.out.println(q.get("Option3"));
                    System.out.println(q.get("Option4"));
                    answer = scanner.next();
                    if(answer.equals(q.get("Answer")))
                    {
                        System.out.println("Correct!");
                        score+=1;
                    }
                    else{
                        System.out.println("Wrong!");
                    }
                    report.add(new ReportQuiz(q.get("Question"),answer,q.get("Answer"),1));
                    System.out.println(""); 
                }
                Report(score,report);
                 break;
            default:
                System.out.println("Please select right category!");
                 break;
                }
       }
    public static void main(String args[]) throws IOException{
        boolean isLoggedIn;
        int choice;
        System.out.println("**********WELCOME TO QUIZIT! **********");
        System.out.println("Do you have an account?");
        System.out.println("1.Log In");
        System.out.println("2.Register");
        if(scanner.nextInt()==1)
             isLoggedIn = LogIn();
        else
             isLoggedIn = Register();

        if(isLoggedIn)
            System.out.println("Welcome " + currentUser.getUsername()+" !");
        else
            System.exit(0);
        
        do{
        System.out.println("1.Play");
        System.out.println("2.Logout");
        choice = scanner.nextInt(); 
        if(choice==2){
            isLoggedIn = false;
            System.out.println("Thanks for Playing!");
        }
        else{
           playQuiz(); 
        }
       
     }while(isLoggedIn);
        
    }
}