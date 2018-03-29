package com.example.cm.socialapp.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.cm.socialapp.Models.chat_people_Data;
import com.example.cm.socialapp.Models.commentData;
import com.example.cm.socialapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by cm on 29/03/2018.
 */

public class chat_people_adapter extends ArrayAdapter {

    Context context;
    int res ;
    ArrayList<chat_people_Data> arrayList;

    public chat_people_adapter(@NonNull Context context, int resource, @NonNull ArrayList<chat_people_Data> arrayList) {

        super(context, resource, arrayList);

        this.context = context;
        this.res = resource;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(res,parent,false);

        CircleImageView circleImageView = convertView.findViewById(R.id.chat_people_img);
        TextView textView = convertView.findViewById(R.id.chat_people_name);
        Button button = convertView.findViewById(R.id.chat_people_btn);

        Picasso.get().load(Uri.parse(arrayList.get(position).getImg())).into(circleImageView);
        textView.setText(arrayList.get(position).getName());
        return convertView;
    }
}
