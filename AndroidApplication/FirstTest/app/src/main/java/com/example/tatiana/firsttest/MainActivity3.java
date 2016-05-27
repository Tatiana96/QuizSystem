package com.example.tatiana.firsttest;

import android.content.Intent;
//import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tatiana.firsttest.model.User;
//import java.net.URI;
//import java.net.URI.*;

public class MainActivity3 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static long back_pressed;

    Intent MainAct3;
    Intent MainAct4;
    ImageButton btnPencil;
    RadioButton btnMan;
    RadioButton btnWoman;
    ImageView ivAvatar;
    Button btnCngPass;

    TextView tvUserLogin;
    TextView tvUserFirstName;
    TextView tvUserSecondName;
    TextView tvUserEmail;
    TextView tvUserBalanceSum;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //public com.google.android.gms.common.api.GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnPencil = (ImageButton) findViewById(R.id.imageBtnPencil);
        btnMan = (RadioButton) findViewById(R.id.radioBtnMan);
        btnWoman = (RadioButton) findViewById(R.id.radioBtnWoman);
        ivAvatar = (ImageView) findViewById(R.id.imageViewAvatar);
        btnCngPass = (Button) findViewById (R.id.btnCngPass);

        tvUserLogin = (TextView) findViewById(R.id.tvUserLogin);
        tvUserFirstName = (TextView) findViewById(R.id.tvUserFirstName);
        tvUserSecondName = (TextView) findViewById (R.id.tvUserSecondName);
        tvUserEmail = (TextView) findViewById(R.id.tvUserEmail);
        tvUserBalanceSum = (TextView) findViewById(R.id.tvUserBalanceSum);

        User UserInstance = (User) getIntent().getParcelableExtra(User.class.getCanonicalName());

        if (UserInstance.getLogin() != null)
            tvUserLogin.setText(UserInstance.getLogin());
        if (UserInstance.getFirstname() != null)
            tvUserFirstName.setText(UserInstance.getFirstname());
        if (UserInstance.getSecondname() != null)
            tvUserSecondName.setText(UserInstance.getSecondname());
        if (UserInstance.getEmail() != null)
            tvUserEmail.setText(UserInstance.getEmail());
        if (UserInstance.getBalance() != null)
            tvUserBalanceSum.setText(UserInstance.getBalance().toString());

        if (UserInstance.getSex() == 1) {
            btnMan.setChecked(true);
            btnWoman.setChecked(false);
            ivAvatar.setImageResource(R.drawable.owlavatar);
        }

        if (UserInstance.getSex() == 2) {
            btnMan.setChecked(false);
            btnWoman.setChecked(true);
            ivAvatar.setImageResource(R.drawable.woman);
        }


        btnMan.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btnMan.setChecked(true);
                    btnWoman.setChecked(false);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    ivAvatar.setImageResource(R.drawable.owlavatar);
                }
                return false;
            }
        });

        btnWoman.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btnWoman.setChecked(true);
                    btnMan.setChecked(false);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    ivAvatar.setImageResource(R.drawable.woman);
                }
                return false;
            }
        });



        btnPencil.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btnPencil.setBackgroundColor(getResources().getColor(R.color.colorPencilPushed));
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    btnPencil.setBackgroundColor(getResources().getColor(R.color.colorPencil));
                    Toast.makeText(getApplicationContext(),
                            "Sorry, this service isn't available now...",
                            Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        btnCngPass.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btnCngPass.setBackgroundColor(getResources().getColor(R.color.BtnSignInPushed));
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    btnCngPass.setBackgroundColor(getResources().getColor(R.color.BtnSignIn));

                        Toast.makeText(getApplicationContext(),
                                "Sorry, this service isn't available now...",
                                Toast.LENGTH_SHORT).show();

                }
                return false;
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new com.google.android.gms.common.api.GoogleApiClient.Builder(this).addApi(com.google.android.gms.appindexing.AppIndex.API).build();
    }

    /*@Override
    public void onBackPressed() {
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
       /* this.finish();
    }*/

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis())
            this.onDestroy();
        else
            Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();

        back_pressed = System.currentTimeMillis();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.avatar) {
            MainAct3 = new Intent(MainActivity3.this, MainActivity3.class);
            startActivity(MainAct3);
            // Handle the camera action
        } else if (id == R.id.tests) {
            MainAct4 = new Intent(MainActivity3.this, MainActivity4.class);
            startActivity(MainAct4);
        } else if (id == R.id.balance) {

        } else if (id == R.id.trans) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onDestroy() {
        //moveTaskToBack(true);

        super.onDestroy();

        System.runFinalizersOnExit(true);
        System.exit(0);
    }

}
