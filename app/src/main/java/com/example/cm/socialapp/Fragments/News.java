package com.example.cm.socialapp.Fragments;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cm.socialapp.API.ApiNews;
import com.example.cm.socialapp.Activities.Home;
import com.example.cm.socialapp.Adapters.news_adapter;
import com.example.cm.socialapp.Models.newsData;
import com.example.cm.socialapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import me.toptas.fancyshowcase.FancyShowCaseView;
import me.toptas.fancyshowcase.OnViewInflateListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class
News extends Fragment {

    View v;
    EditText editText;
    FancyShowCaseView fancyShowCaseView;
    ListView listView;
    CircleImageView circleImageView;
    news_adapter  news_adapter;
    ApiNews apiNews;
    ArrayList<newsData> arrayList;

   String flag;

    public News() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_news, container, false);
        init();
        fancyPost();
        readPost();

        return v;
    }

    public void init()
    {
         editText = v.findViewById(R.id.news_fregment_edittext);
         listView = v.findViewById(R.id.news_fregment_listview);
         circleImageView = v.findViewById(R.id.news_fregment_imgpost);
        Picasso.get().load(Uri.parse(Home.img)).into(circleImageView);
         apiNews = new ApiNews(getActivity());
         arrayList = new ArrayList<>();

    }

    public void readPost()
    {
        arrayList = apiNews.READ_POSTS();

        news_adapter = new news_adapter(getActivity(),R.layout.news_items,arrayList);
        listView.setAdapter(news_adapter);
    }

    public void fancyPost()
    {
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fancyShowCaseView = new FancyShowCaseView.Builder(getActivity())
                        .focusOn(view)
                        .customView(R.layout.fancy_post_layout, new OnViewInflateListener() {
                            @Override
                            public void onViewInflated(@NonNull View view) {

                                FloatingActionButton floatingActionButton = view.findViewById(R.id.fancy_btn_close);
                                 final EditText Post_editText = view.findViewById(R.id.fancy_edittext);
                                Button button = view.findViewById(R.id.fancy_btn_Post);



                               button.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       String data = Post_editText.getText().toString();

                                       if(data.length()!=0)
                                       {
                                           arrayList.clear();
                                           apiNews.WRITE_POST(data, Home.username);
                                           arrayList = apiNews.READ_POSTS();

                                           Post_editText.setText("");
                                           news_adapter = new news_adapter(getActivity(),R.layout.news_items,arrayList);
                                           listView.setAdapter(news_adapter);
                                           Toast.makeText(getActivity(), "Post Uploaded", Toast.LENGTH_SHORT).show();
                                       }else
                                       {
                                           Toast.makeText(getActivity(), "the post is empty", Toast.LENGTH_SHORT).show();
                                       }

                                   }
                               });

                                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        fancyShowCaseView.hide();
                                    }
                                });
                            }
                        }).closeOnTouch(false)
                        .build();



                fancyShowCaseView.show();
            }
        });
            }



}
