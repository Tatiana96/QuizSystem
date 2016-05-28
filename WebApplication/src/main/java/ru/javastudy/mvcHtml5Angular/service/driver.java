package ru.javastudy.mvcHtml5Angular.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class driver {
	
	private static String pass="14091974";
	private static String usr="root";
	static Connection myConn;
	
	public static String getUsr() {
		return usr;
	}
	
	public static String getPass() {
		return pass;
	}
	

	public static Statement connect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizsystem", getUsr(), getPass());
			return myConn.createStatement();
			
       }catch(Exception exc){
    	   exc.printStackTrace();
    	   return null;
       }
	}
	
	public static void unconnect(){
		try {
			myConn.close();
		} catch (Exception exc) {
			 exc.printStackTrace();
		}
	}
}
