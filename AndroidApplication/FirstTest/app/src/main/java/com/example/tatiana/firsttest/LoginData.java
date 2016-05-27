package com.example.tatiana.firsttest;

import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tatiana on 09.05.2016.
 */

public class LoginData {

    LoginParser LgnParser;
    //String url = "http://10.0.3.2:8080/greeting"; // for Genymotion
    String url = "http://192.168.43.125:8080/greeting"; // for real android device

    public void getData(String strLogin, String strPass) {

        ContentValues ListValues = new ContentValues();
        ListValues.put("strLogin", strLogin);
        ListValues.put("strPass", strPass);
        //database.insert(Table_name,null,values);
        LgnParser.Parse(ListValues, url);

    }

}