package com.Questions;
import java.util.*;
import com.AuthClient.*;

public class QuestionList{
    public ArrayList<Question> QuestionsList;
    public Client client;
    
    public QuestionList(String type){
        client = new Client("127.0.0.1",5000,type);
    }
    public ArrayList<Question> getList(){
        QuestionsList = client.getQuestionsfromServer();
        return QuestionsList;
    }
    public PriorityQueue<Question> getRandomList(){
         PriorityQueue<Question> pq = new PriorityQueue<Question>(15, new QuestionComparator()); 
         return pq;
    }

}
class QuestionComparator implements Comparator<Question>{ 
            public int compare(Question q1, Question q2) { 
                if (q1.getPriority() > q2.getPriority()) 
                    return 1; 
                else if (q1.getPriority() < q1.getPriority()) 
                    return -1; 
                                return 0; 
                } 
        } 