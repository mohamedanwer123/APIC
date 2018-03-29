package com.example.cm.socialapp.API;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.cm.socialapp.Activities.Home;
import com.example.cm.socialapp.Activities.Signup;
import com.example.cm.socialapp.Models.MSG;
import com.example.cm.socialapp.Models.SignupData;
import com.example.cm.socialapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.irozon.sneaker.Sneaker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cm on 14/03/2018.
 */

public class ApiSignup {

    Context context;
   public static String imgprofile;
    public ApiSignup(Context context) {
        this.context = context;
    }



  /*  public void SIGNUP(final String name, String pass) {
        String path = "https://inexpedient-church.000webhostapp.com/chat_insert_clientInfo.php";

        AndroidNetworking.post(path)
                .addBodyParameter("username", name)
                .addBodyParameter("password", pass)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject = null;
                        try {

                            jsonObject = response.getJSONObject(0);

                            if (jsonObject.length() == 2) {

                                Intent intent = new Intent(context, Home.class);
                                intent.putExtra("name", name);
                                context.startActivity(intent);

                            } else {
                                String erroe = jsonObject.getString("error");

                                MSG.msg(context,"Problem","Something error try again", "#23283a", R.drawable.problem);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                       MSG.msg(context,"Internet","Not Connection with Internet", "#e91e63", R.drawable.error);

                    }
                });
    }*/



    public void SIGNUP(final String name, final String pass , final String email , final String phone , final String img)
    {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(0);
        progressDialog.setTitle("Data In Uploading");


            final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            StorageReference storageReference = FirebaseStorage.getInstance().getReference();

            storageReference.child("Login/"+System.currentTimeMillis()+".jpg").putFile(Uri.parse(img)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    SignupData signupData = new SignupData(name,pass,email,phone,taskSnapshot.getDownloadUrl().toString());
                    databaseReference.child("LoginData").push().setValue(signupData);
                    progressDialog.dismiss();
                    imgprofile = taskSnapshot.getDownloadUrl().toString();
                    Intent intent = new Intent(context,Home.class);
                    intent.putExtra("flag","signup");
                    intent.putExtra("name",name);
                    context.startActivity(intent);
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.show();
                    progressDialog.setMax((int) taskSnapshot.getBytesTransferred()*100);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    MSG.msg(context,"Internet","Not Connection with Internet", "#e91e63", R.drawable.error);
                }
            });



    }


}
