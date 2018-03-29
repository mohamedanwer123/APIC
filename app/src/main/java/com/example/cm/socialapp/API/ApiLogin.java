package com.example.cm.socialapp.API;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.irozon.sneaker.Sneaker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cm on 14/03/2018.
 */

public class ApiLogin {

    Context context;
    public static String imgprofile;

    public ApiLogin(Context context) {
        this.context = context;
    }



    /*public void LOGIN(final String name, final String password) {
        String path = "https://inexpedient-church.000webhostapp.com/chat_get_clientInfo.php?username=" + name + "&password=" + password;

        AndroidNetworking.get(path)
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
                                // Toast.makeText(context, erroe, Toast.LENGTH_SHORT).show();
                                MSG.msg(context,"authentication","The Data is incorrect", "#383f59", R.drawable.incorect);

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

    int flag=0;
    public void LOGIN(final String name, final String password)
    {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("LoginData").orderByChild("name").equalTo(name);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot d : dataSnapshot.getChildren())
                {
                    SignupData signupData = d.getValue(SignupData.class);

                    if(name.equals(signupData.getName()) && password.equals(signupData.getPass()))
                    {
                        imgprofile = signupData.getImg();
                        Intent intent = new Intent(context,Home.class);
                        intent.putExtra("flag","login");
                        intent.putExtra("name",name);
                        context.startActivity(intent);
                        flag=1;
                    }
                }

                if(flag==0)
                {
                    MSG.msg(context,"authentication","The Data is incorrect", "#383f59", R.drawable.incorect);
                }




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }



}
