package com.Users;
import java.io.Serializable;

public class User implements Serializable{
    
    private static final long serialVersionUID = 1L;
    String username;
    String email;
    String password;
    int telephone_no;
    int highest_score;
   
    public User(String username,String email,String password,int telephone_no){
        this.username = username;
        this.email = email;
        this.password = password;
        this.telephone_no = telephone_no;
        this.highest_score = 0;
    }
    public User(String username,String password){
    	 this.username = username;
         this.password = password;
    }
    public User(String username,String email,String password,int highest_score,int telephone_no){
        this.username = username;
        this.email = email;
        this.password = password;
        this.telephone_no = telephone_no;
        this.highest_score = highest_score;
    }

    public void setHighestscore(Integer score){
        this.highest_score = score;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public Integer getHighestScore(){
        return this.highest_score;
    }
    public String getEmail(){
        return this.email;
    }
    public Integer getNumber(){
        return this.telephone_no;
    }
}