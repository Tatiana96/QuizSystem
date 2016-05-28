package ru.javastudy.mvcHtml5Angular.service;

import ru.javastudy.mvcHtml5Angular.model.Transaction;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;


public class TransactionService {

    static public void add(String login, String summa){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            String time = OtherService.getDateTime();
            myStmt.executeUpdate("INSERT INTO `quizsystem`.`transaction` (`idUser`, `dataPay`, `status`, `summa`) VALUES (" +
                    "(select iduser from quizsystem.user where login='"+login+"'), '"+time+"', 'in processing', '"+summa+"');");
            //if(myRs.next())	return myRs.getString(parameter);

        }catch(Exception exc){
            exc.printStackTrace();
        }

    }

    public static Transaction[] GetTransactions(String login){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();

            ResultSet myRs = myStmt.executeQuery("SELECT * FROM quizsystem.transaction where idUser = (Select iduser from quizsystem.user where login = '"+login+"')");
            int i=0;
            while(myRs.next()) i++;

            Transaction tr[] = new Transaction[i];

            myRs = myStmt.executeQuery("SELECT * FROM quizsystem.transaction where idUser = (Select iduser from quizsystem.user where login = '"+login+"')");
            i=0;
            while(myRs.next()){
                Date data = myRs.getDate("dataPay");

                String summa = myRs.getString("summa");
                String status =  myRs.getString("status");
                String id = myRs.getString("idtransaction");
               Transaction tmp = new Transaction(data, summa, status, id);
               tr[i] = tmp;
                i++;
            }

            return tr;
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    public static Transaction[] GetTransactionsProcessing(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();

            ResultSet myRs = myStmt.executeQuery("SELECT * FROM quizsystem.transaction where status = 'in processing'");
            int i=0;
            while(myRs.next()) i++;

            Transaction tr[] = new Transaction[i];

            myRs = myStmt.executeQuery("SELECT * FROM quizsystem.transaction where status = 'in processing'");
            i=0;
            while(myRs.next()){
                Transaction tmp = new Transaction(myRs.getDate("dataPay"), myRs.getString("summa"), myRs.getString("status"), myRs.getString("idtransaction"));
                tmp.setIdlogin(myRs.getString("idUser"));
                tr[i] = tmp;
                i++;
            }

            return tr;

        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    public static void AddTransactions(String id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();
            myStmt.executeUpdate("UPDATE `quizsystem`.`transaction` SET `status`='success' WHERE `idtransaction`='"+id+"'");
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM quizsystem.transaction where `idtransaction`='"+id+"'");
            int summa = 0;
            String iduser = "0";
            if(myRs.next()) {
                summa = myRs.getInt("summa");
                iduser = myRs.getString("idUser");
                myRs = myStmt.executeQuery("SELECT * FROM quizsystem.user where `iduser`='"+iduser+"'");
                if(myRs.next()){
                    int summauser = myRs.getInt("balance");
                    myStmt.executeUpdate("UPDATE `quizsystem`.`user` SET `balance`='"+(summauser+summa)+"' WHERE `iduser`='"+iduser+"'");
                }
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }

    }

}
