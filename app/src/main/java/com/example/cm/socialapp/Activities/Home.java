package com.example.cm.socialapp.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.cm.socialapp.API.ApiLogin;
import com.example.cm.socialapp.API.ApiSignup;
import com.example.cm.socialapp.Adapters.fragment_pager_adapter;
import com.example.cm.socialapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    public static String username;
    CircleImageView circleImageView;
    String flag ;
    public  static   String img ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        Put_Pages();

        flag = getIntent().getStringExtra("flag");
        if(flag.equals("signup") || flag == "signup")
        {
            img = ApiSignup.imgprofile;

        }else  if(flag.equals("login") || flag == "login")
        {
            img = ApiLogin.imgprofile;
        }
        Picasso.get().load(Uri.parse(img)).into(circleImageView);
    }

    public void init()
    {
        viewPager = findViewById(R.id.home_activity_viewPager);
        tabLayout = findViewById(R.id.home_activity_tabllayout);
        circleImageView = findViewById(R.id.home_activity_profile_image);
        username = getIntent().getStringExtra("name");
    }

    public void Put_Pages()
    {
        viewPager.setAdapter(new fragment_pager_adapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {

    }

    public void logout(View view) {

        startActivity(new Intent(this,Login.class));
    }
}
