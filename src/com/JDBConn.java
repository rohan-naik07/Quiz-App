package com;
import java.sql.*;

import java.util.*;

public class JDBConn {

	public static void main(String[] args) throws Exception
	{
	    ArrayList<String> arr = new ArrayList<String>();
		
		String url="jdbc:mysql://localhost:3306/sys";
		String uname="*****";
		String pass="**********";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		
		Statement st=con.createStatement();
		
		ResultSet rs;
		CallableStatement statement = con.prepareCall("SELECT * FROM customer");
		 
		rs=statement.executeQuery();
		
		while(rs.next())
		{
			arr.add(rs.getString("cust_fname"));
		}
		
		System.out.println(arr);
		
		st.close();
		con.close();
	}
}