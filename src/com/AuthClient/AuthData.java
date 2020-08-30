package com.AuthClient;
import java.io.*;
import com.Users.*;


public class AuthData implements Serializable {

    private static final long serialVersionUID = 1L;
    public User user =null;
    public String question_category=null;
    public int type;

    public AuthData(User user, int type){
        this.user = user;
        this.type = type;
    }
    public AuthData(String question_category,int type){
        this.question_category = question_category;
        this.type = type;
    }
    public int getType(){
        return this.type;
    }
    public User getUser(){
        return this.user;
    }
    public String getCategory(){
        return this.question_category;
    }
}