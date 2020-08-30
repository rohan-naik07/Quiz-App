package com.Questions;
import java.util.*;
import java.io.*;

public class Question implements Serializable{
	private static final long serialVersionUID = 1L;
	//private static final long serialVersionUID = 465520374568722812L;
	String answer;
    String question;
    String option1;
    String option2;
    String option3;
    String option4;
    int priority;

    public Question(){}
    
    public Question(String question,String option1,String option2,String option3,String option4,String answer,int priority){
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.priority = priority;
    }

    public int getPriority(){
        return this.priority;
    }
   
    public HashMap<String,String> getQuestion(){
       HashMap<String,String> questions = new HashMap<String,String>();
       questions.put("Question",this.question);
       questions.put("Option1",this.option1);
       questions.put("Option2",this.option2);
       questions.put("Option3",this.option3);
       questions.put("Option4",this.option4);
       questions.put("Answer",this.answer);
       return questions;
    }
}
