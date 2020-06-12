package com.tutorialspoint;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class radCheckBean {
	
	public radCheckBean(){
		
	}
	public List<Radcheckinfo> getAllUsers(){
		System.out.println("radCheckBean.getAllUsers() Entering");
		List<Radcheckinfo> userList = new ArrayList<Radcheckinfo>();
	  	Connection c = null;
	      Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         
	        // c = DriverManager.getConnection("jdbc:postgresql://172.20.0.2:5432/database","user", "password");
	         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/radius","radius", "radius");
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         ResultSet rs = stmt.executeQuery( "SELECT * FROM radcheck;" );
	         while ( rs.next() ) {
	       
	            System.out.println( "ID = " + rs.getInt("id")+", username = "+rs.getString("username") +", attribute = "+rs.getString("attribute")+" , value :"+rs.getString("value") );
	           Radcheckinfo radcheck = new Radcheckinfo(rs.getInt("id"), rs.getString("username"), rs.getString("attribute"), rs.getString("op"), rs.getString("value"));
	            userList.add(radcheck);
	         }
	         rs.close();
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	      return userList;
	   }
	
	public int addAAAUser(Radcheckinfo obj){
		System.out.println("radCheckBean.addAAAUser() Entering to create a username :"+obj.getUsername()+", Attribute:"+obj.getAttribute()+", op :"+obj.getOp()+" ,value :"+obj.getValue());
		System.out.println("radCheckBean.addAAAUser() Op :"+obj.getOp());
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		int count=0;
		String query = "select count(*) from radcheck where username=? and attribute like '%Password'";
		try {

			Class.forName("org.postgresql.Driver");
			
		//	dbConnection = DriverManager.getConnection("jdbc:postgresql://172.20.0.2:5432/database","user", "password");
			dbConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/radius","radius", "radius");

			System.out.println("Opened database successfully");

			preparedStatement = dbConnection.prepareStatement(query);
			preparedStatement.setString(1, obj.getUsername());
			ResultSet rs = preparedStatement.executeQuery();
			while ( rs.next() ) {
				count=rs.getInt("count"); 
			}

			if(count>0){
				System.out.println("radCheckBean.addAAAUser() User already exists");
				return 0;
			}

			rs.close();
			preparedStatement.close();
			System.out.println("radCheckBean.addAAAUser() Going to insert the user");

			String insertTableSQL = "INSERT INTO RADCHECK(USERNAME, ATTRIBUTE, OP, VALUE) VALUES(?,?,?,?)";
			preparedStatement= dbConnection.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, obj.getUsername().trim());
			preparedStatement.setString(2,obj.getAttribute().trim());
			preparedStatement.setString(3, obj.getOp());
			preparedStatement.setString(4, obj.getValue().trim());

			preparedStatement.executeUpdate();
		}
		catch ( Exception e ) {
			System.out.println("radCheckBean.addAAAUser() Exception occurs");
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			e.printStackTrace();
			return 0;
		}finally {

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		System.out.println("User added successfully");
		return 1;
	}
	
	public List<Radcheckinfo> getAllUsersjunk(){
		List<Radcheckinfo> userList = new ArrayList<Radcheckinfo>();
		try{
			Radcheckinfo obj = new Radcheckinfo(1, "aaa", "bbb", ":=", "ccc");
			userList.add(obj);
		}catch(Exception e){
			e.getMessage();
		}
		return userList;
			
	}
	
	/*
	public static void main(String args[]){
		System.out.println("radCheckBean.main() ");
		radCheckBean obj= radCheckBean();
		obj.getAllUsers();
	}*/

}
