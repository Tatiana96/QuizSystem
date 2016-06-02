package ru.javastudy.mvcHtml5Angular.service;

import ru.javastudy.mvcHtml5Angular.model.UserQuestion;

import java.sql.ResultSet;
import java.sql.Statement;

public class proposeQuestionService {

    public static String setProposeQuestion(String test, String login ,String typeQuestion, String contentQuestion){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            myStmt.executeUpdate("INSERT INTO `quizsystem`.`proposed_question` (`idTests`, `idUsers`, `type_question`, `questionContent`, `status`) " +
                    "VALUES ((Select idTest from quizsystem.test where name='"+test+"')," +
                    " (Select idUser from quizsystem.user where login='"+login+"')," +
                    " '"+typeQuestion+"', '"+contentQuestion+"', 'in processing')");
            ResultSet myRs = myStmt.executeQuery("select max(idProposedQuestion)id from quizsystem.proposed_question where idUsers=(Select idUser from quizsystem.user where login='"+login+"')");
            if(myRs.next()) return myRs.getString("id");
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    public static void setProposeAnswer(String idQ,String answer){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            myStmt.executeUpdate("INSERT INTO `quizsystem`.`proposed_answer` (`idQuestion`, `correctly`, `content`) VALUES ('"+idQ+"', '0', '"+answer+"')");
        }catch(Exception exc){
            exc.printStackTrace();
        }
    }

    public static boolean setCorrectProposeAnswer(String idQ,String answer){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();

            ResultSet myRs = myStmt.executeQuery("SELECT * FROM quizsystem.proposed_answer where idQuestion="+idQ);
            while(myRs.next()){
                String Content = myRs.getString("answerContent").toLowerCase();
                if(Content.equalsIgnoreCase(answer)){
                    myStmt.executeUpdate("UPDATE `quizsystem`.`proposed_answer` SET `correctly`='1' WHERE `idProposedAnswer`='"+myRs.getString("idProposedAnswer")+"';");
                    return true;
                }
            }
            return false;
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return false;
    }

    public static  UserQuestion[] getQuestions(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            ResultSet myRs = myStmt.executeQuery("Select *, count(*) count from quizsystem.proposed_question");

            if(myRs.next()) {
                UserQuestion question[] = new UserQuestion[myRs.getInt("count")];
                int i = 0;
                while (myRs.next()) {
                    UserQuestion q = new UserQuestion(myRs.getString("content"), myRs.getString("typeQuestion"), GetAnswers(myRs.getString("idProposedQuestion")), GetCorrectAnswers(myRs.getString("idProposedQuestion")), myRs.getString("idProposedQuestion"));

                    question[i] = q;
                    i++;
                }
                return question;
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    public static String[] GetAnswers(String idQuestion){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            ResultSet myRs = myStmt.executeQuery("Select * from quizsystem.proposed_answer where idQuestion = "+idQuestion);
            int i=0;
            while(myRs.next()) i++;
            String answers[] = new String[i];
            myRs = myStmt.executeQuery("Select * from quizsystem.proposed_answer where idQuestion = "+idQuestion);
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

    public static String[] GetCorrectAnswers(String idQuestion){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            ResultSet myRs = myStmt.executeQuery("Select *, count(*) count from quizsystem.proposed_answer where idQuestion ="+idQuestion+" and correctly = 1");
            if(myRs.next()) {
                String answers[] = new String[myRs.getInt("count")];
                int i = 0;
                while (myRs.next()) {
                    answers[i] = myRs.getString("content");
                    i++;
                }
                return answers;
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    public static void DeleteQuestion(String id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            myStmt.executeUpdate("DELETE FROM `quizsystem`.`proposed_answer` WHERE idQuestion="+id);
            myStmt.executeUpdate("DELETE FROM `quizsystem`.`proposed_question` WHERE idproposed_question="+id);

        }catch(Exception exc){
            exc.printStackTrace();
        }

    }

    public static void AddQuestion(String id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            myStmt.executeUpdate("INSERT INTO `quizsystem`.`question` (`typeQuestion`, `questionContent`, `mark`) VALUES ((Select type_question FROM `quizsystem`.`proposed_question` where idproposed_question = "+id+"), (Select questionContent FROM `quizsystem`.`proposed_question` where idproposed_question = "+id+"), '1')");
            myStmt.executeUpdate("DELETE FROM `quizsystem`.`proposed_question` WHERE idproposed_question="+id);

            String idQuestion="0";

            ResultSet myRs = myStmt.executeQuery("Select max(idquestion) id from quizsystem.question ");
            if(myRs.next()) idQuestion = myRs.getString("id");//айдишник нового вопроса

            myRs = myStmt.executeQuery("Select * from quizsystem.proposed_answer where idQuestion ="+id);

            int i=0;
            while(myRs.next())i++;
            String newAnsw[]= new String[i];//массив ответов
            String correct[]=new String[i];
            myRs = myStmt.executeQuery("Select * from quizsystem.proposed_answer where idQuestion ="+id);
            i=0;
            while(myRs.next()){
                newAnsw[i] = myRs.getString("answerContent").toString();
                correct[i]=myRs.getString("correctly").toString();
                i++;
            }

            myRs = myStmt.executeQuery("Select * from quizsystem.answer ");
            int j=0;
            while(myRs.next())j++;
            String oldAnsw[]= new String[j];//массив впросов
            String oldAnswID[]= new String[j];//массив впросов
            myRs = myStmt.executeQuery("Select * from quizsystem.answer ");
             j=0;
            while(myRs.next()){
                oldAnsw[j] = myRs.getString("answerContent");
                oldAnswID[j] = myRs.getString("idanswer");
                j++;
            }
            boolean flag=false;
            for(int k=0; k<i; k++){
                for(int z=0; z<j; z++){
                    if(newAnsw[k].equalsIgnoreCase(oldAnsw[z])){
                        myStmt.executeUpdate("INSERT INTO `quizsystem`.`question-answer` (`idAnswer`, `idQuestion`, `correctly`) " +
                                "VALUES ('"+oldAnswID[z]+"', '"+idQuestion+"', '"+correct[k]+"')");
                        flag = true;
                        break;
                    }
                }
                if(!flag){

                    myStmt.executeUpdate(" INSERT INTO `quizsystem`.`answer` (`answerContent`) VALUES ('"+newAnsw[k]+"')");
                    String idAnswer="0";

                    myRs = myStmt.executeQuery("Select max(idanswer) id from quizsystem.answer ");
                    if(myRs.next()) idAnswer = myRs.getString("id");//айдишник нового вопроса
                    myStmt.executeUpdate("INSERT INTO `quizsystem`.`question-answer` (`idAnswer`, `idQuestion`, `correctly`) " +
                            "VALUES ('"+idAnswer+"', '"+idQuestion+"', '"+correct[k]+"')");
                }
                myStmt.executeUpdate("DELETE FROM `quizsystem`.`proposed_answer` WHERE answerContent='"+newAnsw[k]+"'");
                flag = false;
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
    }
}
