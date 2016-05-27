package com.example.tatiana.firsttest;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.*;
import android.widget.*;

import com.example.tatiana.firsttest.model.Greeting;
import com.example.tatiana.firsttest.model.User;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String strLogin;
    String strPass;

    TextView tvLogin;
    TextView tvPass;
    TextView tvForget;

    Button btnSignIn;
    Button btnSignUp;

    Intent MainAct2;
    Intent MainAct3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLogin = (TextView) findViewById(R.id.editTextLogin);
        tvPass = (TextView) findViewById(R.id.editTextPass);
        tvForget = (TextView) findViewById (R.id.textViewForget);
        btnSignIn = (Button) findViewById (R.id.buttonSignIn);
        btnSignUp = (Button) findViewById (R.id.buttonSignUp);


        btnSignIn.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        btnSignIn.setBackgroundColor(getResources().getColor(R.color.BtnSignInPushed));
                    }
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        btnSignIn.setBackgroundColor(getResources().getColor(R.color.BtnSignIn));

                        if (tvLogin.getText().length() == 0 || tvPass.getText().length() == 0) {
                            Toast.makeText(getApplicationContext(),
                                    "Please input your login and password for authorisation...",
                                    Toast.LENGTH_SHORT).show();
                        }

                        else {

                            //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            // StrictMode.setThreadPolicy(policy);

                            //Registration process
                            //strLogin = tvLogin.getText().toString();
                            //strPass = tvPass.getText().toString();

                            //new LoginData().getData(strLogin, strPass);
                            //AsyncTask<Void, Void, Greeting> HttpRequestInstance;
                            //HttpRequestInstance = new HttpRequestTask();
                            new HttpRequestTask().execute();

                            /*Toast.makeText(getApplicationContext(),
                                    "Data is trying to load...",
                                    Toast.LENGTH_SHORT).show();
                            */
                            //MainAct3 = new Intent(MainActivity.this, MainActivity3.class);
                            //startActivity(MainAct3);
                        }

                    }
                    return false;
                }
            });

        btnSignUp.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btnSignUp.setBackgroundColor(getResources().getColor(R.color.BtnSignUpPushed));
                    btnSignUp.setTextColor(getResources().getColor(R.color.BtnSignUpTextPushed));
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    btnSignUp.setBackgroundColor(getResources().getColor(R.color.BtnSignUp));
                    btnSignUp.setTextColor(getResources().getColor(R.color.BtnSignUpText));
                }
                return false;
            }
        });

        tvForget.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    tvForget.setTextColor(getResources().getColor(R.color.tvFogetPushed));
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    tvForget.setTextColor(getResources().getColor(R.color.tvFoget));
                    Toast.makeText(getApplicationContext(),
                            "Sorry, this service isn't available now...",
                            Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });



        //btnSignIn.setOnClickListener(ClickSignIn);

    }

    class HttpRequestTask extends AsyncTask<Void, Void, User> {

        int ErrorConnect = 1;
        String login;
        String password;
        //TextView tvLogin;
        //TextView tvPass;

        @Override
        protected void onPreExecute() { // получаем значения логина и пароля
            login = tvLogin.getText().toString();
            password = tvPass.getText().toString();
        }

        @Override
        protected User doInBackground(Void... params) {

            User UserPost;
            User UserGet;
            UserPost = new User();
            UserGet = new User();

            //MultiValueMap<String, String> mapLoginPass = new LinkedMultiValueMap<String, String>(); // массив для хранения логина и пароля
            Map<String, String> mapLoginPass = new HashMap<String, String>();
            mapLoginPass.put("login", login);
            mapLoginPass.put("password", password);
            try {
                //final String url = "http://rest-service.guides.spring.io/greeting"; // url for test
                final String urlPost = "http://192.168.43.125:8080/UserPost"; // real url
                final String urlGet = "http://192.168.43.125:8080/UserGet"; // real url
                RestTemplate restTemplatePost = new RestTemplate();
                RestTemplate restTemplateGet = new RestTemplate();

                restTemplateGet.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                restTemplateGet.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplatePost.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                restTemplatePost.getMessageConverters().add(new StringHttpMessageConverter());

                //greeting = restTemplate.getForObject(url, Greeting.class);
                UserPost = restTemplatePost.postForObject(urlPost, mapLoginPass, User.class);
                UserGet = restTemplateGet.getForObject(urlGet, User.class);
                //new FillingOut().FillElementsWithGreeting(greeting);
                ErrorConnect = 0;
                //Greeting greeting = new Greeting();
                //return greeting;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
                //ErrorConnect = 1;
            }

            return UserGet;
        }

        /*@Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tvLogin.setText("Downloaded " + values[0] + " files");
        }*/

        /*@Override
        protected void onProgressUpdate(Void... params) {

        }*/

        @Override
        protected void onPostExecute(User UserInstance) {

            if (ErrorConnect == 1)
                Toast.makeText(getApplicationContext(),
                        "Server communication error! \nPlease, try to connect again later...",
                        Toast.LENGTH_SHORT).show();

            switch(UserInstance.Authorisation) {
                case 1:
                    Toast.makeText(getApplicationContext(),
                            "Success authorisation!",
                            Toast.LENGTH_SHORT).show();
                            MainAct3 = new Intent(MainActivity.this, MainActivity3.class);
                            MainAct3.putExtra(User.class.getCanonicalName(), UserInstance);
                            startActivity(MainAct3);
                    break;
                case 2:
                    Toast.makeText(getApplicationContext(),
                            "User doesn't exist...",
                            Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(getApplicationContext(),
                            "Wrong Password...",
                            Toast.LENGTH_SHORT).show();
                    break;
            }

            //tvLogin.setText(greeting.getContent());
            //tvPass.setText(greeting.getId());

        }


    }
}
