package com.example.cm.socialapp.Adapters;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cm.socialapp.API.ApiNews;
import com.example.cm.socialapp.Activities.Home;
import com.example.cm.socialapp.Models.newsData;
import com.example.cm.socialapp.R;

import java.util.ArrayList;

import me.toptas.fancyshowcase.FancyShowCaseView;
import me.toptas.fancyshowcase.OnViewInflateListener;

/**
 * Created by cm on 21/03/2018.
 */

public class news_adapter extends ArrayAdapter {

    Context context ;
    int resource;
    ArrayList<newsData> arrayList;
    FancyShowCaseView fancyShowCaseView;
    ApiNews apiNews;


    public news_adapter(@NonNull Context context, int resource, @NonNull ArrayList<newsData> arrayList) {

        super(context, resource, arrayList);
        this.context = context;
        this.resource = resource;
        this.arrayList = arrayList;
    }



    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(resource,parent,false);
        TextView username = convertView.findViewById(R.id.news_items_name);
        final TextView post = convertView.findViewById(R.id.news_items_post_content);
        Button button = convertView.findViewById(R.id.news_items_share);

        username.setText(arrayList.get(position).getUser_name());
        post.setText(arrayList.get(position).getPost());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               final ApiNews apiNews = new ApiNews(context);

               fancyShowCaseView = new FancyShowCaseView.Builder((Activity) context)
                       .focusOn(view)
                       .customView(R.layout.fancy_post_layout, new OnViewInflateListener() {
                           @Override
                           public void onViewInflated(@NonNull View view) {

                               final FloatingActionButton floatingActionButton = view.findViewById(R.id.fancy_btn_close);
                               final EditText Post_editText = view.findViewById(R.id.fancy_edittext);
                               Button button = view.findViewById(R.id.fancy_btn_Post);

                               Post_editText.setText(post.getText().toString());

                               floatingActionButton.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       fancyShowCaseView.hide();
                                   }
                               });

                               button.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {

                                       apiNews.WRITE_POST(Post_editText.getText().toString(),Home.username);
                                       Post_editText.setText("");
                                       Toast.makeText(context, "Post Shareded", Toast.LENGTH_SHORT).show();
                                   }
                               });

                           }
                       }).closeOnTouch(false)
                       .build();

               fancyShowCaseView.show();

            }
        });

        return convertView;
    }
}
