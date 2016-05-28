package ru.javastudy.mvcHtml5Angular.service;

import java.sql.ResultSet;
import java.sql.Statement;

public class LoginService {
    public static  boolean login(String login, String password){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM QuizSystem.user where login = '" + login + "'");

            if(!myRs.next()) {///такой пользователь не найден
                driver.unconnect();
                return false;
            }
            else {
                myRs = myStmt.executeQuery("SELECT * FROM QuizSystem.user where login = '" + login + "' and password = '"+password+"'");
                if(!myRs.next()){
                    System.out.println("password is incorrect");///вставить в страницу что пароль не верен
                    driver.unconnect();
                    return false;
                }

                else {
                    driver.unconnect();
                    return true;
                }
            }

        }catch(Exception exc){
            exc.printStackTrace();
        }
        return false;
    }

    public static Boolean singup( String login, String password, String firstname, String secondname, String email){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM QuizSystem.user where login = '" + login + "'");

            if(!myRs.next()) {//если такого пользователя нет то создаем
                myStmt.executeUpdate("INSERT INTO quizsystem.user (isAdmin, login, password, firstName, secondName, Email, balance) VALUES ( 0, '"
                        + login + "', '" +password+"', '" + firstname + "', '" + secondname + "', '" + email + "', '0');");
                driver.unconnect();
                return true;
            }else{//выводим ошибку что такой пользователь уже есть
                driver.unconnect();
                return false;
            }


        }catch(Exception exc){
            exc.printStackTrace();
        }
        return false;
    }

    public static Boolean IsAdmin(String login){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM QuizSystem.user where login = '" + login + "'");

            if(myRs.next()) {//если такого пользователя нет то создаем
                String bla = myRs.getString("isAdmin").toString();
               if( bla.equalsIgnoreCase("1"))return true;
               else return false;

            }

        }catch(Exception exc){
            exc.printStackTrace();
        }
        return false;

    }

}
