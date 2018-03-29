package com.example.cm.socialapp.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cm.socialapp.API.ApiSignup;
import com.example.cm.socialapp.Models.MSG;
import com.example.cm.socialapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class Signup extends AppCompatActivity {

    EditText username,password,email,phone;
    Button signup;
    CircleImageView circleImageView;
    ApiSignup apiSignup;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,123);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*String n = username.getText().toString().toLowerCase().trim();
                String p = password.getText().toString().toLowerCase().trim();

                if(n.length()==0 || p.length()==0)
                {
                    MSG.msg(Signup.this,"Empty","Please Enter Your Data","#d3d3d3",R.drawable.empty);

                }else
                {
                    apiSignup.SIGNUP(n,p);
                    SharedPreferences sharedPreferences = getSharedPreferences("info", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name",n);
                    editor.putString("pass",p);
                    editor.apply();

                    //new singnupTask().execute();
                }*/

                if(username.getText().toString().length()==0 || password.getText().toString().length()==0 || email.getText().toString().length()==0 || password.getText().toString().length()==0 ||  uri == null  )
                {
                    MSG.msg(Signup.this,"Empty","Please Enter Your Data","#d3d3d3",R.drawable.empty);
                }else
                {
                    apiSignup.SIGNUP(username.getText().toString()
                            ,password.getText().toString()
                            ,email.getText().toString()
                            ,phone.getText().toString()
                            ,uri.toString());
                    SharedPreferences sharedPreferences = getSharedPreferences("info", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name",username.getText().toString());
                    editor.putString("pass",password.getText().toString());
                    editor.apply();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123 && resultCode == RESULT_OK)
        {
            uri = data.getData();
            circleImageView.setImageURI(uri);
        }
    }

    public void init()
    {
        username = findViewById(R.id.signupActivity_edittext_username);
        password = findViewById(R.id.signupActivity_edittext_password);
        phone = findViewById(R.id.signupActivity_edittext_phone);
        email = findViewById(R.id.signupActivity_edittext_email);
        circleImageView = findViewById(R.id.signupActivity_image);
        signup = findViewById(R.id.signupActivity_button_signup);
        apiSignup = new ApiSignup(Signup.this);
    }



  /*  public class singnupTask extends AsyncTask<Void,Void , Void>
    {
       ProgressDialog progressDialog;
       int time=0;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(Signup.this);
            progressDialog.setProgress(0);
            progressDialog.setMax(100);
            progressDialog.show();
            progressDialog.setMessage("Wait To Finish Registration Your Data");
            progressDialog.setCancelable(false);

        }

        @Override
        protected Void doInBackground(Void... voids) {

            while (time <= 100) {
                progressDialog.setProgress(time);
                time += 10;
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.cancel();
        }
    }*/



}
