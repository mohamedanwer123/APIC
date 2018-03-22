package com.example.cm.socialapp.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.example.cm.socialapp.R;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class Splash extends AppIntro {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    addSlide(AppIntroFragment.newInstance("APIC",
                "Chat \n\n You Can Chat With Others in All Sides",
             R.drawable.chat,
             Color.parseColor("#23283a")));

        addSlide(AppIntroFragment.newInstance("APIC",
                "News \n\n You will See All People News It Shared",
                R.drawable.news,
                Color.parseColor("#23283a")));

        addSlide(AppIntroFragment.newInstance("APIC",
                "Load Images \n\n the people Will Share them Images",
                R.drawable.person,
                Color.parseColor("#23283a")));

        addSlide(AppIntroFragment.newInstance("APIC",
                "Videos \n\n You Will Watch The New Videos",
                R.drawable.video,
                Color.parseColor("#23283a")));

        setBarColor(Color.parseColor("#fe104d"));

}

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startActivity(new Intent(this,Login.class));

    }
}
