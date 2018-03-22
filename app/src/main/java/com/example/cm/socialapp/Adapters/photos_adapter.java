package com.example.cm.socialapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cm.socialapp.Models.photoData;
import com.example.cm.socialapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by cm on 22/03/2018.
 */

public class photos_adapter extends ArrayAdapter{

    Context context;
    int res;
    ArrayList<photoData> arrayList;
    TextView name,describtion;
    ImageView imageView,menu;

    public photos_adapter(@NonNull Context context, int resource, @NonNull ArrayList<photoData> arrayList) {

        super(context, resource, arrayList);
        this.context = context;
        this.res = resource;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.photos_items,parent,false);
        init(convertView);
        putData(position);

        return convertView;
    }

    public void init(View view)
    {
        name = view.findViewById(R.id.photos_items_name);
        describtion = view.findViewById(R.id.photos_items_des);
        imageView = view.findViewById(R.id.photos_items_current_img);
        menu = view.findViewById(R.id.photos_items_menu);

    }

    public void putData(int position)
    {
        name.setText(arrayList.get(position).getName());
        describtion.setText(arrayList.get(position).getDescribtion());
        Picasso.get().load(arrayList.get(position).getUrl()).into(imageView);

    }
}
