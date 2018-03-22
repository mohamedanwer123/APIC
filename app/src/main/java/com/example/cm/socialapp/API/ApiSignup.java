package com.example.cm.socialapp.API;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.cm.socialapp.Activities.Home;
import com.example.cm.socialapp.Models.MSG;
import com.example.cm.socialapp.R;
import com.irozon.sneaker.Sneaker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cm on 14/03/2018.
 */

public class ApiSignup {

    Context context;

    public ApiSignup(Context context) {
        this.context = context;
    }



    public void SIGNUP(final String name, String pass) {
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
    }


}
