package com.example.cm.socialapp.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.cm.socialapp.Fragments.Chat;
import com.example.cm.socialapp.Fragments.News;
import com.example.cm.socialapp.Fragments.Photos;

/**
 * Created by cm on 21/03/2018.
 */

public class fragment_pager_adapter extends FragmentPagerAdapter {


    String Pages_names[]={"News","Photos","Chat"};
    Fragment Pages[]={new News(),new Photos(),new Chat()};

    public fragment_pager_adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return Pages[position];
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return Pages_names[position];
    }

    @Override
    public int getCount() {
        return 3;
    }
}
