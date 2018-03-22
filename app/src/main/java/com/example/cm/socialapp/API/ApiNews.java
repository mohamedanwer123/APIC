package com.example.cm.socialapp.API;

import android.content.Context;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.cm.socialapp.Models.newsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by cm on 21/03/2018.
 */

public class ApiNews {

    Context context;

    public ApiNews(Context context) {
        this.context = context;
    }

    public ArrayList<newsData> READ_POSTS() {
        String path = "https://inexpedient-church.000webhostapp.com/chat_read_posts.php";
        final ArrayList<newsData> data = new ArrayList<>();
        AndroidNetworking.get(path)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSONObject jsonObject = null;
                        try {
                            data.clear();

                            for (int i = response.length()-1; i >=0 ; i--) {

                                jsonObject = response.getJSONObject(i);

                                data.add(new newsData(jsonObject.getString("name"),jsonObject.getString("post")));
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                    }
                });

        return data;
    }


    public void WRITE_POST( String post, String user_name ) {

        String path = "https://inexpedient-church.000webhostapp.com/chat_write_post.php?post=" + post + "&name=" + user_name;

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

                            } else {
                                String error = jsonObject.getString("error");
                                // Toast.makeText(context, erroe, Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
