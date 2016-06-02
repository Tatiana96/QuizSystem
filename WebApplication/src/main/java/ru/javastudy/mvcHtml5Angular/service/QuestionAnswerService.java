package ru.javastudy.mvcHtml5Angular.service;

import ru.javastudy.mvcHtml5Angular.model.Question;

import java.sql.ResultSet;
import java.sql.Statement;

public class QuestionAnswerService {

    public static String[] GetQuestionsCategory(String Category){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            ResultSet myRs = myStmt.executeQuery("Select *, count(*) count from quizsystem.question where idQuestion in " +
                    "(SELECT idQuestion FROM quizsystem.`test-question` where idTest in " +
                    "(select idTest from quizsystem.test where idCategory = " +
                    "(Select idСategory from quizsystem.сategory where name = '"+Category+"')))");
            //получили id всех вопросов причастных к этому тесту
            int i=0;
            if(myRs.next()) {
                String question[] = new String[myRs.getInt("count")];
                while (myRs.next()) {
                    question[i] = myRs.getString("questionContent");
                    i++;
                }
                return question;
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    public static Question[] GetMultyQuestions(String[] categories, Integer size){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            ResultSet myRs;

            Question question[] = new Question[size*5];
            int CurSize=0;
            for(int i=0; i<size; i++){//от каждой категории будет 5 вопросов
                myRs = myStmt.executeQuery("SELECT * FROM quizsystem.сategory where name='"+categories[i]+"'");
                if(myRs.next()){
                    String id = myRs.getString("idСategory");
                    myRs = myStmt.executeQuery("SELECT * FROM quizsystem.question where idquestion in " +
                                                "(SELECT idQuestion FROM quizsystem.`test-question` where idTest in " +
                                                "(select idtest from quizsystem.test where idCategory = "+id+"))");
                    int j=0;

                    while(myRs.next()){
                        Question q = new Question(myRs.getString("questionContent"), myRs.getString("typeQuestion"), GetAnswers(myRs.getString("idquestion")), myRs.getString("idquestion"));

                        question[CurSize] = q;
                        if(j>=5) break;
                        j++;
                        CurSize++;
                    }
                }
            }
            return question;
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    public static Question[] GetQuestions(String nameTest){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            ResultSet myRs;

            if(nameTest != null) myRs= myStmt.executeQuery("Select * from quizsystem.question where idquestion in "
                    + "(SELECT idQuestion FROM quizsystem.`test-question` where idTest = "
                    + "(select idtest from quizsystem.test where name = '"+nameTest+"'))");
            else myRs= myStmt.executeQuery("Select * from quizsystem.question ");
            //получили id всех вопросов причастных к этому тесту
            int i=0;
            while(myRs.next()) i++;

            Question question[] = new Question[i];

            if(nameTest != null) myRs= myStmt.executeQuery("Select * from quizsystem.question where idquestion in "
                    + "(SELECT idQuestion FROM quizsystem.`test-question` where idTest = "
                    + "(select idtest from quizsystem.test where name = '"+nameTest+"'))");
            else myRs= myStmt.executeQuery("Select * from quizsystem.question ");
            i=0;
            while(myRs.next()){
                Question q = new Question(myRs.getString("content"), myRs.getString("typeQuestion"), GetAnswers(myRs.getString("idquestion")), myRs.getString("idquestion"));
                question[i] = q;
                i++;
            }
            return question;
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    public static String[] GetAnswers(String idQuestion){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            ResultSet myRs = myStmt.executeQuery("Select * from quizsystem.answer where idanswer in"
                    + "	(SELECT idAnswer FROM quizsystem.`question-answer` where idquestion = "+idQuestion+")");
            int i=0;
            while(myRs.next()) i++;
            String answers[] = new String[i];
            myRs = myStmt.executeQuery("Select * from quizsystem.answer where idanswer in"
                    + "	(SELECT idAnswer FROM quizsystem.`question-answer` where idquestion = "+idQuestion+")");
            i=0;
            while(myRs.next()){
                answers[i] = myRs.getString("content");
                i++;
            }
            return answers;
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    public static void SetUserResponse(String idTesting, String IdQuestion, String NameAnswer, String login){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            ResultSet myRs = myStmt.executeQuery("Select * from quizsystem.answer where content = '"+NameAnswer+"'");
            if(myRs.next()) {
                String idAnswer = myRs.getString("idanswer");
                myStmt.executeUpdate("INSERT INTO `quizsystem`.`user_response` (`idUser`, `idTesting`, `idQuestion`, `idAnswer`, `correctness`)" +
                        "VALUES ((select iduser from quizsystem.user where login='" + login + "'), '" +
                        idTesting + "', '" + IdQuestion + "'," + idAnswer + ", " +
                        "(SELECT correctly FROM quizsystem.`question-answer` where idQuestion=" + IdQuestion + " and idAnswer = " + idAnswer + "));");
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
    }
}

