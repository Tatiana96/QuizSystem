package ru.javastudy.mvcHtml5Angular.model;

import java.sql.Date;


public class Transaction {
   private Date date;
   private String summa;
   private String status;
    private String idlogin;
    private String id;

    public Transaction(){
        date = new Date(System.currentTimeMillis());
        summa="";
        status="";
        idlogin="";
        id="";

    }
    public Transaction(Date data, String summa, String status , String id){
        this.date = data;
        this.summa = summa;
        this.status = status;
        this.id = id;
    }

    public void setData(Date data){
        this.date = data;
    }
    public  void setSumma(String summa){
        this.summa = summa;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public void setIdlogin(String id){
        this.idlogin = id;
    }

    public Date getDate(){return this.date;}
    public String getSumma(){return this.summa;}
    public String getStatus(){return this.status;}
    public String getIdlogin(){return this.idlogin;}
    public String getId(){return this.id;}
}
