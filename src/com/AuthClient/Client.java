package com.AuthClient;
import java.net.*; 
import java.io.*;
import com.Users.User;
import com.Questions.Question;
import java.util.*;
  
public class Client 
{ 
    // initialize socket and input output streams 
    private Socket socket = null; 
    private OutputStream outputStream = null;
    private ObjectOutputStream objectOutputStream = null;
    private ObjectInputStream objectInputStream = null;
    private String address;
    private int port;
    private User user;
    private String category;
    // constructor to put ip address and port 
    public Client(String address, int port,User user) {
        this.port = port;
        this.address = address;
        this.user = user;  
    }
    public Client(String address, int port,String category) {
        this.port = port;
        this.address = address;
        this.category = category;  
    }
    public User LoginUser()throws IOException{
       User user = null;
        try
        { 
            this.socket = new Socket(this.address, this.port); 
            System.out.println("Connected"); 
            // get the output stream from the socket.
            this.outputStream = socket.getOutputStream();
            // create an object output stream from the output stream so we can send an object through it
            this.objectOutputStream = new ObjectOutputStream(this.outputStream);
            System.out.println("Sending messages to the ServerSocket");
            this.objectOutputStream.writeObject(new AuthData(this.user,1)); 
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            user = (User)this.objectInputStream.readObject();
        } 
        catch(Exception u) 
        { 
            System.out.println(u); 
        }
        
        // close the connection 
        try
        { 
            this.outputStream.close(); 
            this.objectOutputStream.close(); 
            this.socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        }
        return user;
    }
    public void RegisterUser(){
        try
        { 
            this.socket = new Socket(this.address, this.port); 
            System.out.println("Connected"); 
            // get the output stream from the socket.
            this.outputStream = socket.getOutputStream();
            // create an object output stream from the output stream so we can send an object through it
            this.objectOutputStream = new ObjectOutputStream(this.outputStream);
            System.out.println("Sending messages to the ServerSocket");
            this.objectOutputStream.writeObject(new AuthData(this.user,2)); 
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        }
        catch(ConnectException c){
            System.out.println(c);
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
        // close the connection 
        try
        { 
            this.outputStream.close(); 
            this.objectOutputStream.close(); 
            this.socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    }
    @SuppressWarnings("unchecked")
    public ArrayList<Question> getQuestionsfromServer(int type){
        ArrayList<Question> listofQuestions = null;
        try
        { 
            this.socket = new Socket(this.address, this.port); 
            System.out.println("Connected"); 
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending messages to the ServerSocket");
            this.objectOutputStream.writeObject(new AuthData(this.category,type)); 
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            listofQuestions = (ArrayList<Question>) objectInputStream.readObject();
        } 
        catch(Exception u) 
        { 
            System.out.println("Connection issue " + u); 
        }
        // close the connection 
        try
        { 
            this.objectOutputStream.close(); 
            this.objectInputStream.close();
            this.socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
        return listofQuestions; 
    } 
    public void setHighestScore(Integer score,User user){
        try
        { 
            this.socket = new Socket(this.address, this.port); 
            System.out.println("Connected"); 
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending messages to the ServerSocket");
            this.objectOutputStream.writeObject(new AuthData(user,Integer.toString(score))); 
        } 
        catch(Exception u) 
        { 
            System.out.println("Connection issue " + u); 
        }
        // close the connection 
        try
        { 
            this.objectOutputStream.close(); 
            this.socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
} 

 