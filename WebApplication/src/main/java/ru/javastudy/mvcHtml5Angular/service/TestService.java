package ru.javastudy.mvcHtml5Angular.service;

import java.sql.ResultSet;
import java.sql.Statement;

public class TestService {

    public static void CommentAdd(String name, String content){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            ResultSet myRs = myStmt.executeQuery("Select idTest from quizsystem.test where name ='"+name+"'");
            if(myRs.next()) {
                String id = myRs.getString("idtest");
                myStmt.executeUpdate("INSERT INTO `quizsystem`.`comment` (`idTest`, `content`) " +
                        "VALUES ('"+id+"', '"+content+"')");
                driver.unconnect();
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
    }

    public static String[] GetComments(String name){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();

            ResultSet myRs = myStmt.executeQuery("SELECT * FROM quizsystem.comment where idTest = (Select idTest from quizsystem.test where name ='"+name+"')");
            int i=0;
            while(myRs.next())	i++;

            String comm[] = new String[i];

            i=0;
            myRs = myStmt.executeQuery("SELECT * FROM quizsystem.comment where idTest = (Select idTest from quizsystem.test where name ='"+name+"')");
            while(myRs.next()){
                comm[i] = myRs.getString("content");
                i++;
            }
            return comm;
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }
    public static String[] GetCategories(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM quizsystem.сategory");
            int i=0;
            while(myRs.next())	i++;
            String cat[] = new String[i];
            i=0;
            myRs = myStmt.executeQuery("SELECT * FROM quizsystem.сategory");
            while(myRs.next()){
                cat[i] = myRs.getString("name");
                i++;
            }
            return cat;
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }
    public static String[] GetTests(String category){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();

            ResultSet myRs1;
            if(category !=null) myRs1= myStmt.executeQuery("SELECT * FROM quizsystem.test where idCategory = (SELECT idCategory from quizsystem.сategory where name ='"+category+"')");
            else myRs1= myStmt.executeQuery("SELECT * FROM quizsystem.test");

            if(myRs1.next()){
                String idCategory = null;
                if(category !=null)  idCategory = myRs1.getString("idCategory");
                ResultSet myRs;
                if(category !=null) myRs= myStmt.executeQuery("SELECT * FROM quizsystem.test where idCategory = " + idCategory);
                else myRs= myStmt.executeQuery("SELECT * FROM quizsystem.test");

                int i=0;
                while(myRs.next())	i++;
                String cat[] = new String[i];

                i=0;
                if(category !=null) myRs= myStmt.executeQuery("SELECT * FROM quizsystem.test where idCategory = " + idCategory);
                else myRs= myStmt.executeQuery("SELECT * FROM quizsystem.test");
                while(myRs.next()){
                    cat[i] = myRs.getString("name");
                    i++;
                }
                return cat;
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    public static String GetAboutTest(String nameTest, String parameter){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM quizsystem.test where name = '"+nameTest+"'");
            if(myRs.next())	return myRs.getString(parameter);
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    public static String SetTesting(String login,String test){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();//getDateTime
            String time = OtherService.getDateTime();
            myStmt.executeUpdate("INSERT INTO `quizsystem`.`testing` (`idUsers`, `idTests`, `startTime`,  `result`) VALUES (" +
                    "(select iduser from quizsystem.user where login='"+login+"')," +
                    " (select idtest from quizsystem.test where name = '"+test+"'),'"+time+"' , '0');");

            ResultSet myRs = myStmt.executeQuery("SELECT * FROM quizsystem.testing where idUsers = " +
                    "(select iduser from quizsystem.user where login = '"+login+"') and startTime = '"+time+"'");
            if(myRs.next())	return myRs.getString("idtesting");

        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    public static String SetMultyTesting(String login){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();//getDateTime
            String time = OtherService.getDateTime();
            myStmt.executeUpdate("INSERT INTO `quizsystem`.`testing` (`idUsers`, `idTests`, `startTime`,  `result`) VALUES (" +
                    "(select iduser from quizsystem.user where login='"+login+"'),'-1','"+time+"' , '0');");

            ResultSet myRs = myStmt.executeQuery("SELECT * FROM quizsystem.testing where idUsers = " +
                    "(select iduser from quizsystem.user where login = '"+login+"') and startTime = '"+time+"'");
            if(myRs.next())	return myRs.getString("idtesting");

        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    public static void SetFinishTesting(String idTesting,String UserResult){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();//getDateTime
            myStmt.executeUpdate("UPDATE `quizsystem`.`testing` SET `finishTime`='"+OtherService.getDateTime()+"', `result`='"+UserResult+"' WHERE `idtesting`='"+idTesting+"';");
        }catch(Exception exc){
            exc.printStackTrace();
        }
    }

    public static boolean addCategory(String newCat){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            ResultSet myRs = myStmt.executeQuery("SELECT * from quizsystem.сategory where name ='" + newCat + "'");

            if (!myRs.next()) {
                myStmt.executeUpdate("INSERT INTO `quizsystem`.`сategory` (`name`) VALUES ('"+newCat+"');");
                driver.unconnect();
                return true;
            } else {
                return false;
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return false;
    }

    public static void AddTest(String name, String category , String about,  String cost, String [] questions){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            ResultSet myRs = myStmt.executeQuery("SELECT * from quizsystem.сategory where name ='" + category + "'");
            String idCat = null;
            if(myRs.next()) idCat = myRs.getString("idСategory");

            myStmt.executeUpdate("INSERT INTO `quizsystem`.`test` (`idCategory`, `name`, `about`, `cost`) " +
                    "VALUES ('"+idCat+"', '"+name+"', '"+about+"', '"+cost+"')");

            myRs = myStmt.executeQuery("SELECT * FROM quizsystem.test where name ='"+name+"'");
            String idTest=null;
            if(myRs.next()) idTest = myRs.getString("idtest");

            for(int i=0; i<questions.length; i++ ){
                myRs = myStmt.executeQuery("SELECT * FROM quizsystem.question where questionContent='"+questions[i]+"'");
                if(myRs.next()) {
                    String id=myRs.getString("idquestion");
                    myStmt.executeUpdate("INSERT INTO `quizsystem`.`test-question` (`idTest`, `idQuestion`) " +
                            "VALUES ('"+idTest+"', '"+id+"');");
                }
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
    }

    public static String GetKode(String Name){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            ResultSet myRs = myStmt.executeQuery("SELECT * from quizsystem.test where name ='" + Name + "'");

            if(myRs.next())  return myRs.getString("idtest").toString();
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }
}
