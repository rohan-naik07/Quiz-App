package com.AuthServers;
import java.net.*; 
import java.io.*;
import java.util.*;
import com.AuthClient.*; 
import com.Users.*;
import java.sql.*;
import com.Questions.*;

public class Server 
{ 
	//initialize socket and input stream 
	private static Socket socket = null; 
	private static ServerSocket server = null; 
	private static int port = 5000;
	private static Connection connection = null;
	private static String serverName = "localhost:3306";
	private static String schema = "sys";
	private static String url = "jdbc:mysql://" + serverName +  "/" + schema;
	// constructor with port 
	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws IOException,ClassNotFoundException{ 
		// starts server and waits for a connection
		try {
			String driverName = "com.mysql.jdbc.Driver";
			String username = "root";
			String password = "Msdian-77";
			Class.forName(driverName);
			connection = DriverManager.getConnection(url,username,password);
			System.out.println("Successfully Connected to the database!");
		} 
		catch (ClassNotFoundException e)
		{
			System.out.println("Could not Find " + e.getMessage());
		}
		catch (SQLException e) {
		System.out.println("Could not connect to the database " + e.getMessage());
		}

		
        //users.add(new User("rohan","r@hotmail.com","rohan123",380984824));
		try
		{ 
			server = new ServerSocket(port); 
			System.out.println("Server started"); 
			System.out.println("Waiting for a client ...");
			while(true) {
				socket = server.accept(); 
				System.out.println("Client accepted"); 
				// takes input from the client socket 
				System.out.println("Assigning new thread for this client"); 
                // create a new thread object 
                Thread t = new ClientHandler(socket,connection); 
                // Invoking the start() method 
                t.start(); 
			}
				
		} 
		catch(Exception i) 
		{ 
			System.out.println("rohan"+i); 
		} 
		
	 }
	
} 
// tommarrow : sql-join for priority queue 
class ClientHandler extends Thread  
{ 
	
    final ObjectInputStream objectInputstream; 
    final DataOutputStream dataOutputStream; 
    final Socket socket; 
    private AuthData DataforLogin;
    final ObjectOutputStream objectOutputstream;
    final Connection connection;
    private ArrayList<Question> questions = new ArrayList<Question>();
    
    // Constructor 
    public ClientHandler(Socket socket,Connection connection) throws IOException  
    { 
        this.socket = socket; 
		this.objectInputstream = new ObjectInputStream(socket.getInputStream());
		this.objectOutputstream = new ObjectOutputStream(socket.getOutputStream());
		this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
		this.connection = connection;
    } 
  
    @Override
    public void run()  
    { 
    	
    	try
		{ 
			DataforLogin = (AuthData) objectInputstream.readObject();
			int type = DataforLogin.getType();
			switch(type)
			{
				case 1:
				    Login();
					break;
				case 2:
					Register();
					break;
				case 3:
					getQuestionsfromDatabase();
					break;
				case 4:
					getRandomQuestionsfromDatabase();
					break;
				case 5:
					setHighestScore();
					break;
				default: break;
			}
			
		}
		catch(ClassNotFoundException i) 
		{ 
			System.out.println(i); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
    		this.socket.close();
    		this.dataOutputStream.close(); 
    		this.objectInputstream.close(); 
    		this.objectOutputstream.close();
    	}
    	catch(Exception e) {
    		System.out.println("Radda" + e); 
    	}
	}
  
public void Login(){
	try{
		Statement statement = this.connection.createStatement();
		String username = this.DataforLogin.getUser().getUsername();
		String password = this.DataforLogin.getUser().getPassword();
		String query = "SELECT * FROM Users";
		User user = null;
		ResultSet results = statement.executeQuery(query);
		while(results.next())
		{
			if(username.equals(results.getString("username")) && password.equals(results.getString("password")))
					user  = new User(
							results.getString("username"),
							results.getString("email"),
							results.getString("password"),
							results.getInt("highest_score"),
							results.getInt("telephone_no"));
		}
		if(user!=null){
			System.out.println("Logged In Successfully!");
			this.objectOutputstream.writeObject(user);
			}
		else {
			System.out.println("Login Unsuccessful !");
			this.objectOutputstream.writeObject(user);
		}
	}
	catch(Exception e){
		e.printStackTrace();
	}
 }
public void Register(){
	try{
		PreparedStatement statement  = connection.prepareStatement("INSERT INTO users values(null,?,?,?,?,?)");
		statement.setString(1,DataforLogin.getUser().getUsername());
		statement.setString(2,DataforLogin.getUser().getEmail());
		statement.setString(3,DataforLogin.getUser().getPassword());
		statement.setInt(4,DataforLogin.getUser().getNumber());
		statement.setInt(5,DataforLogin.getUser().getHighestScore());
		statement.executeUpdate();
		
		} catch (SQLException e) {

		System.out.println("Could not retrieve data from the database " + e.getMessage());
		  }
	System.out.println("Successfully created account for " + DataforLogin.getUser().getUsername());
}
public void getQuestionsfromDatabase(){
	try{
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM questions"+
				  " WHERE category = ?");
		
		statement.setString(1,DataforLogin.getCategory());
		ResultSet results = statement.executeQuery();
		while (results.next()) {
		  questions.add(new Question(results.getString("question"),results.getString("option1"),
			  results.getString("option2"),results.getString("option3"),
			  results.getString("option4"),results.getString("answer"),results.getInt("priority"))); 
		}

		} catch (SQLException e) {

		System.out.println("Could not retrieve data from the database " + e.getMessage());
		  }
	try{
		this.objectOutputstream.writeObject(questions);
	}
	catch(Exception e){
		System.out.println(e);
	}	
}
public void getRandomQuestionsfromDatabase(){
	try{
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM questions LIMIT 15");
		ResultSet results = statement.executeQuery();
		while (results.next()) {
		  questions.add(new Question(results.getString("question"),results.getString("option1"),
			  results.getString("option2"),results.getString("option3"),
			  results.getString("option4"),results.getString("answer"),results.getInt("priority"))); 
		}
		System.out.println("Added random questions from the database ");
		} catch (SQLException e) {

		System.out.println("Could not retrieve data from the database " + e.getMessage());
		  }
	try{
		this.objectOutputstream.writeObject(questions);
	}
	catch(Exception e){
		System.out.println(e);
	}	
}
public void setHighestScore(){
	try{
		String query = "UPDATE Users SET highest_score=? where username=?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1,DataforLogin.getCategory());
		statement.setString(2,DataforLogin.getUser().getUsername());
		statement.executeQuery();

		} catch (SQLException e) {

		System.out.println("Could not retrieve data from the database " + e.getMessage());
		  }	
	}
} 


