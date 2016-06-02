package ru.javastudy.mvcHtml5Angular.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.javastudy.mvcHtml5Angular.service.driver;
import java.sql.Statement;
import java.util.Date;

@Component
public class ScheduleTask {

    @Scheduled(fixedDelay = 900000)//будет запускаться каждый час
    public void fixedDelaySchedule() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement myStmt = driver.connect();

            myStmt.executeUpdate("TRUNCATE TABLE quizsystem.top");//отчистили таблицу top
            myStmt.executeUpdate("INSERT INTO quizsystem.top (idlogin, login, answers) " +
                    "SELECT idUser id,(Select login from quizsystem.user where idUser=id) login, SUM(correctness) " +
                    "FROM quizsystem.user_response " +
                    "group by idUser " +
                    "order by SUM(correctness) Desc");

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
