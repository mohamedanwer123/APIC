package com.example.cm.socialapp.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.example.cm.socialapp.API.ApiLogin;
import com.example.cm.socialapp.Models.MSG;
import com.example.cm.socialapp.R;
import com.irozon.sneaker.Sneaker;
import com.spark.submitbutton.SubmitButton;

public class Login extends AppCompatActivity {

    EditText username,password;
    Button signup;
    SubmitButton login;
    ApiLogin apiLogin;
    long times=0;
    //public  static  String clinetname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        SharedPreferences sharedPreferences = getSharedPreferences("info",Context.MODE_PRIVATE);
        String flag = sharedPreferences.getString("name","enter your name").toString();
        if(flag.length()==0)
        {
            username.setHint(flag);
            password.setHint(sharedPreferences.getString("pass","enter your password").toString());
        }else
        {
            username.setText(flag);
            password.setText(sharedPreferences.getString("pass","enter your password").toString());
        }

        //clinetname = username.getText().toString();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              login();
            }
        });

         signup.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(Login.this,Signup.class));
             }
         });

    }

    public void init()
    {
        AndroidNetworking.initialize(Login.this);
        username = findViewById(R.id.loginactivity_edittext_username);
        password = findViewById(R.id.loginactivity_edittext_password);
        login = findViewById(R.id.loginactivity_button_login);
        signup = findViewById(R.id.loginactivity_button_Signup);
        apiLogin = new ApiLogin(Login.this);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBackPressed() {
        if(times>System.currentTimeMillis())
        {
            finishAffinity();
        }else
        {
            Toast.makeText(this, "Press Again", Toast.LENGTH_LONG).show();
            times = System.currentTimeMillis()+3000;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void permession()
    {
        requestPermissions(new String[]{Manifest.permission.INTERNET,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},123);
    }


    public void login()
    {
        String name = username.getText().toString().toLowerCase().trim();
        String pass = password.getText().toString().toLowerCase().trim();

        if(name.length()==0 || pass.length()==0)
        {
            //Toast.makeText(this, "Please Enter Your Data", Toast.LENGTH_SHORT).show();
            MSG.msg(Login.this,"Empty","Please Enter Your Data","#d3d3d3",R.drawable.empty);
        }else
        {
            apiLogin.LOGIN(name,pass);
            SharedPreferences sharedPreferences = getSharedPreferences("info", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name",name);
            editor.putString("pass",pass);
            editor.apply();
        }

    }




}
