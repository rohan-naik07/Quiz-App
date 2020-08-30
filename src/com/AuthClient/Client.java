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
    private DataInputStream dataInputStream = null;
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
    public boolean LoginUser()throws IOException{
        boolean flag = true;
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
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            flag = this.dataInputStream.readBoolean();
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
        return flag;
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
    public ArrayList<Question> getQuestionsfromServer(){
        ArrayList<Question> listofQuestions = null;
        try
        { 
            this.socket = new Socket(this.address, this.port); 
            System.out.println("Connected"); 
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending messages to the ServerSocket");
            this.objectOutputStream.writeObject(new AuthData(this.category,3)); 
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            listofQuestions = (ArrayList<Question>) objectInputStream.readObject();
            System.out.println(listofQuestions.get(0).getQuestion());
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
} 

 