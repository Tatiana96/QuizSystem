package ru.javastudy.mvcHtml5Angular.service;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OtherService {

    public static String decodeGetParameter(String parameter) throws UnsupportedEncodingException {
        return new String(parameter.getBytes("ISO-8859-1"),"UTF8");
    }

    public static String getDateTime() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date();

        return dateFormat.format(date);

    }

    public static String GetMessageResult(int User, int Total) {
        if(User == Total) return "ВАШ УРОВЕНЬ... ПЯТЬ С ПЛЮСОМ!Просто феноменальный результат, у вас отличная память и вы можете этим гордиться.";
        else if(User*100/Total >=75) return "ВАШ УРОВЕНЬ... ТВЕРДАЯ ЧЕТВЕРКА!Вам можно позавидовать, у вас довольно высокий результат, присущий большинству людей. Так держать.";
        else if(User*100/Total < 75 && User*100/Total >= 50) return "3-до луны";
        else return "ЭХ... В этот раз вам не повезло....Двойка...";

    }

}
