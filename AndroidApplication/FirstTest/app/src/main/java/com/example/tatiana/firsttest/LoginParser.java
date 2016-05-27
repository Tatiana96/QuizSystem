package com.example.tatiana.firsttest;

import android.content.ContentValues;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Tatiana on 09.05.2016.
 */

public class LoginParser {

    public static void Parse(ContentValues ListValues, String strUrl) {

        StringBuffer chaine = new StringBuffer("");
        try{
            URL url = new URL(strUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            //connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.connect();

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            //String post_data = URLEncoder.encode("user_name","UTF-8") + "=" + URLEncoder.encode(user_name,"UTF-8") + "&" +
            //        URLEncoder.encode("password","UTF-8") + "=" + URLEncoder.encode(password,"UTF-8");

            String post_data = URLEncoder.encode("user_name","UTF-8") + URLEncoder.encode(ListValues.getAsString("strLogin"), "UTF-8");
                    //+ "=" + URLEncoder.encode(ListValues.getAsString("strLogin"),"UTF-8") + "&" +
                    //URLEncoder.encode("password","UTF-8") + "=" + URLEncoder.encode(ListValues.getAsString("strPassword"),"UTF-8");


            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            connection.disconnect();

        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
        }

    }
}