package ru.javastudy.mvcHtml5Angular.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditService {
    public static boolean CheckPassword(String login, String password){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }
        Statement myStmt = driver.connect();

        ResultSet myRs;
        try {
            myRs = myStmt.executeQuery("SELECT * FROM QuizSystem.user where login = '" + login + "' and password = '"+password+"'");

            if(!myRs.next()){
                driver.unconnect();
                return false;
            }
            else {
                driver.unconnect();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static Boolean edit(String login, String nameField, String value){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }
        try{
            Statement myStmt = driver.connect();
            //не делаем проверку на существование пользователя так как это действие доступно только для вошедшего
            myStmt.executeUpdate("UPDATE QuizSystem.user SET " + nameField +" = '" + value + "' where login = '" + login + "'");

            return true;

        }catch(Exception exc){
            exc.printStackTrace();
            return false;
        }
    }

    public static Boolean editPassword(String login, String lastPass, String newPass){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }
        try{
            Statement myStmt = driver.connect();
            //не делаем проверку на существование пользователя так как это действие доступно только для вошедшего
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM QuizSystem.user where login = '" + login + "' and password = '"+lastPass+"'");
            if(!myRs.next()){
                System.out.println("password is incorrect");///вставить в страницу что ошибка
                driver.unconnect();
                return false;
            }
            else {
                myStmt.executeUpdate("UPDATE QuizSystem.user SET password = '" + newPass + "' where login = '" + login + "'");
                driver.unconnect();
                return true;
            }
        }catch(Exception exc){
            exc.printStackTrace();
            return false;
        }
    }
}
