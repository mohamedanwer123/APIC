package com.example.cm.socialapp.Models;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;

import com.example.cm.socialapp.Activities.Login;
import com.irozon.sneaker.Sneaker;

/**
 * Created by cm on 22/03/2018.
 */

public class MSG {

    public static void msg(Context c, String title, String msg, String color, int icon)
    {
        Sneaker.with((Activity) c)
                .setTitle(title, Color.WHITE)
                .setMessage(msg,Color.WHITE)
                .setDuration(8000)
                .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .autoHide(true)
                .setIcon(icon)
                .sneak(Color.parseColor(color));
    }
}
