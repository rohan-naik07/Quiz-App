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
	private static ObjectInputStream objectInputstream = null;
	private static ObjectOutputStream objectOutputstream = null;
	private static DataOutputStream dataOutputStream = null;
	private static ArrayList<Question> questions = new ArrayList<Question>();
	private static AuthData DataforLogin = null;
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
			String username = "rohan_7";
			String password = "Msdian-77";
			Class.forName(driverName);
			connection = DriverManager.getConnection(url,username,password);
			System.out.println("Successfully Connected to the database!");
		} 
		catch (ClassNotFoundException e)
		{
			System.out.println("Could not Find" + e.getMessage());
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
			socket = server.accept(); 
			System.out.println("Client accepted"); 
			// takes input from the client socket 
			objectInputstream = new ObjectInputStream(socket.getInputStream());
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			try
			{ 
				DataforLogin = (AuthData) objectInputstream.readObject();
				int type = DataforLogin.getType();
				switch(type)
				{
					case 1:
					if(Login()){
						System.out.println("Logged In Successfully!");
						dataOutputStream.writeBoolean(true);
						}
					else {
						System.out.println("Login Unsuccessful !");
						dataOutputStream.writeBoolean(false);
						}
						break;
					case 2:
						Register();
						break;
					case 3:
						getQuestionsfromDatabase();
						break;
					case 4:break;
					default: break;
				}
				
			}
			catch(ClassNotFoundException i) 
			{ 
				System.out.println(i); 
			} 
		} 
		catch(IOException i) 
		{ 
			System.out.println("rohan"+i); 
		} 
		dataOutputStream.close(); 
		objectInputstream.close();  
	 }
	 public static boolean Login(){
		boolean flag = false;
		try{
			Statement statement = connection.createStatement();
			String username = DataforLogin.getUser().getUsername();
			String password = DataforLogin.getUser().getPassword();
			String query = "SELECT * FROM User";
			ResultSet results = statement.executeQuery(query);
			while(results.next())
			{
				if(username.equals(results.getString("username")) && password.equals(results.getString("password")))
						flag = true;
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
			return flag;
	 }
	public static void Register(){
		try{
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO user values("+DataforLogin.getUser().getUsername());
			} catch (SQLException e) {

			System.out.println("Could not retrieve data from the database " + e.getMessage());
			  }
		System.out.println("Successfully created account for " + DataforLogin.getUser().getUsername());
	}
	public static void getQuestionsfromDatabase(){
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
			objectOutputstream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputstream.writeObject(questions);
		}
		catch(Exception e){
			System.out.println(e);
		}	
	}
} 
// tommarrow : sql-join for priority queue 