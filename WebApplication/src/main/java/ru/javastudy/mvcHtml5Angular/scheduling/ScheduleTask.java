package ru.javastudy.mvcHtml5Angular.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.javastudy.mvcHtml5Angular.service.driver;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

@Component
public class ScheduleTask {

    @Scheduled(fixedDelay = 900000)
    public void fixedDelaySchedule() {
        System.out.println("fixedDelaySchedule every 15 minute" + new Date());

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();

            myStmt.executeUpdate("TRUNCATE TABLE quizsystem.top");//отчистили таблицу top
            myStmt.executeUpdate("INSERT INTO quizsystem.top (idlogin, login, answers) SELECT idUser id,(Select login from quizsystem.user where iduser=id) login, SUM(correctness) FROM quizsystem.user_response group by idUser order by SUM(correctness) Desc");

        }catch(Exception exc){
            exc.printStackTrace();
        }

    }

}
