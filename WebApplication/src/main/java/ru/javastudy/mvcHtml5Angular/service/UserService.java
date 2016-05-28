package ru.javastudy.mvcHtml5Angular.service;

import java.sql.ResultSet;
import java.sql.Statement;

public class UserService {

    public static String[] GetTop(){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM quizsystem.top order by answers Desc");

            int i=0;
            while(myRs.next()) i++;

            String users[] = new String[i];

            myRs = myStmt.executeQuery("SELECT * FROM quizsystem.top order by answers Desc");
            i=0;
            while(myRs.next()){
                users[i] = myRs.getString("login");
                i++;
            }
            return users;

        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }


    public static String GetAboutUser(String login, String parameter){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();

            ResultSet myRs = myStmt.executeQuery("SELECT * FROM quizsystem.user where login = '"+login+"'");
            if(myRs.next())	return myRs.getString(parameter);

        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    public static String GetBestAnswer( String login){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();

            ResultSet myRs = myStmt.executeQuery("SELECT count(*) itog FROM quizsystem.user_response where iduser = (select iduser from quizsystem.user where login = '"+login+"') and correctness = 1");
            if(myRs.next()){
                return myRs.getString("itog");
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }
    public static String GetBadAnswer(String login){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();

            ResultSet myRs = myStmt.executeQuery("SELECT count(*) itog FROM quizsystem.user_response where iduser = (select iduser from quizsystem.user where login = '"+login+"')and correctness = 0");
            if(myRs.next()){
                return myRs.getString("itog");
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    public static String GetUserResult(String idTesting, String login){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();

            ResultSet myRs = myStmt.executeQuery("SELECT count(*) itog FROM quizsystem.user_response where idTesting = "+idTesting+" and idUser = (select idUser from quizsystem.user where login = '"+login+"') and correctness = 1");
            if(myRs.next()){
                return myRs.getString("itog");
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }
    public static String GetTotalResult(String idTesting, String login){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();

            ResultSet myRs = myStmt.executeQuery("SELECT count(*) itog FROM quizsystem.user_response where idTesting = "+idTesting+" and idUser = (select idUser from quizsystem.user where login = '"+login+"')");
            if(myRs.next()){
                return myRs.getString("itog");
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }
}
