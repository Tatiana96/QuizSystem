package ru.javastudy.mvcHtml5Angular.service;

import java.sql.ResultSet;
import java.sql.Statement;

public class StatisticaService {


    public static String Count(String parameter){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            ResultSet myRs = myStmt.executeQuery("SELECT count(*) c FROM quizsystem."+parameter);

            if(myRs.next()) {
                return myRs.getString("c");
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }
    public static String CountUsers(){return null;}
    public static String CountCategories(){return null;}

}
