package com.example.cm.socialapp.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cm.socialapp.Adapters.fragment_pager_adapter;
import com.example.cm.socialapp.R;

public class Home extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    public static String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        Put_Pages();
    }

    public void init()
    {
        viewPager = findViewById(R.id.home_activity_viewPager);
        tabLayout = findViewById(R.id.home_activity_tabllayout);
        username = getIntent().getStringExtra("name");
    }

    public void Put_Pages()
    {
        viewPager.setAdapter(new fragment_pager_adapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }
}
