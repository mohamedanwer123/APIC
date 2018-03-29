package com.example.cm.socialapp.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cm.socialapp.Models.commentData;
import com.example.cm.socialapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by cm on 26/03/2018.
 */

public class comment_adapter extends ArrayAdapter {

    Context context;
    int res ;
    ArrayList<commentData> arrayList;
    public comment_adapter(@NonNull Context context, int resource, @NonNull ArrayList<commentData> arrayList) {

        super(context, resource, arrayList);

        this.context = context;
        this.res = resource;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(res,parent,false);

        TextView name = convertView.findViewById(R.id.comment_layout_name);
        TextView comment = convertView.findViewById(R.id.comment_layout_comment);
        CircleImageView circleImageView = convertView.findViewById(R.id.comment_layout_img);
        Picasso.get().load(Uri.parse(arrayList.get(position).getImg())).into(circleImageView);

        name.setText(arrayList.get(position).getName().toString());
        comment.setText(arrayList.get(position).getComment().toString());

        return convertView;
    }
}
