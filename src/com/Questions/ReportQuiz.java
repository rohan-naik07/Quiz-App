package com.Questions;

public class ReportQuiz{
    public String question;
    public String givenanswer;
    public String correctAnswer;
    public Integer point;

    public ReportQuiz(String question,String givenanswer,String correctAnswer,Integer point){
        this.question = question;
        this.givenanswer = givenanswer;
        this.correctAnswer = correctAnswer;
        this.point = point;
    }
    public String getQuestion(){
        return this.question;
    }
    public String getGivenAnswer(){
        return this.givenanswer;
    }
    public String getCorrectAnswer(){
        return this.correctAnswer;
    }
    public Integer getPoint(){
        return this.point;
    }
    
}