package com.example.cm.socialapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cm.socialapp.Models.commentData;
import com.example.cm.socialapp.R;

import java.util.ArrayList;

/**
 * Created by cm on 26/03/2018.
 */

public class like_adapter extends ArrayAdapter {

    Context context;
    int res ;
    String arr[];

    public like_adapter(@NonNull Context context, int resource, @NonNull   String arr[]) {

        super(context, resource, arr);

        this.context = context;
        this.res = resource;
        this.arr = arr;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(res,parent,false);

        TextView name = convertView.findViewById(R.id.likes_layout_name);
        name.setText(arr[position].toString());


        return convertView;
    }
}
