package com.example.cm.socialapp.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cm.socialapp.Activities.Home;
import com.example.cm.socialapp.Activities.Login;
import com.example.cm.socialapp.Models.MSG;
import com.example.cm.socialapp.Models.commentData;
import com.example.cm.socialapp.Models.photoData;
import com.example.cm.socialapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import me.toptas.fancyshowcase.FancyShowCaseView;
import me.toptas.fancyshowcase.OnViewInflateListener;

/**
 * Created by cm on 23/03/2018.
 */

public class photoAdapter extends ArrayAdapter {

    Context context;
    int res;
    ArrayList<photoData> arrayList;

    TextView name ;
    TextView des ;
    ImageView img ;
    ImageButton delete ;
    TextView share , comment ,like;
    TextView like_counter;
    CircleImageView userimage,likeicon;
    FancyShowCaseView fancyShowCaseView;
    ProgressBar progressBar;

    DatabaseReference databaseReference;
    StorageReference  storageReference;

    final String storageFolder="Photos_Page/";
    final String databaseFolder="UserData";
    ArrayList<commentData> arrlist;
    String c;


    public photoAdapter(@NonNull Context context, int resource, @NonNull ArrayList<photoData> arrayList) {
        super(context, resource, arrayList);

        this.context = context;
        this.res = resource;
        this.arrayList = arrayList;



    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(res,parent,false);
        init(convertView);


        Picasso.get().load(arrayList.get(position).getUrl()).into(img);
        Picasso.get().load(arrayList.get(position).getUserimage()).into(userimage);
        name.setText(arrayList.get(position).getName().toString());
        des.setText(arrayList.get(position).getDescribtion().toString());

        String l = arrayList.get(position).getLike();

        if(l.length()!=0)
        {
            String arr[] = l.split("/");
            like_counter.setText(Integer.valueOf(arr[1])+"  LIKES");
            String arr2[] = arr[0].split(",");

                        for (int i = 0; i <arr2.length ; i++) {

                            if(arr2[i].equals(Home.username) || arr2[i] == Home.username)
                            {
                                like.setTextColor(Color.parseColor("#f2bc09"));
                                likeicon.setImageResource(R.drawable.yellowlike);

                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                params.topMargin = 30;
                                like.setLayoutParams(params);


                                break;
                            }
                        }
        }



        // Toast.makeText(context, c, Toast.LENGTH_SHORT).show();


        if(arrayList.get(position).getName().equals(Home.username))
        {
            delete.setVisibility(View.VISIBLE);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Delete(position);

                }
            });
        }else
        {
            delete.setVisibility(View.GONE);
        }


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {share(position);
            }
        });

        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               FancyComment(view,position);
            }
        });

        like_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FancyLike(view,position);





            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(like.getTextColors().getDefaultColor() != Color.parseColor("#f2bc09") )
                {
                    write_like(Home.username,position);
                }

            }
        });

        return convertView;
    }


    public void Delete(int position)
    {
        final String databaseFolder="UserData";
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child(databaseFolder).orderByChild("url")
                .equalTo(arrayList.get(position).getUrl());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d : dataSnapshot.getChildren())
                {
                    d.getRef().removeValue();
                    MSG.msg(context,"Delete","Post Deleted","#000000",R.drawable.delete);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        tone();
    }


    public void  init(View view)
    {
         name = view.findViewById(R.id.tv1);
         des = view.findViewById(R.id.tv2);
         img = view.findViewById(R.id.photos_items_current_img);
         delete = view.findViewById(R.id.delete_btn);
         share = view.findViewById(R.id.photo_items_share);
         comment = view.findViewById(R.id.photo_items_comment);
         like =  view.findViewById(R.id.photo_items_like);
         like_counter =  view.findViewById(R.id.photo_items_like_counter);
         userimage = view.findViewById(R.id.photo_items_userimage);
         likeicon = view.findViewById(R.id.like);

    }


    public void tone()
    {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(context, notification);
        r.play();
    }

    public void share(final int position)
    {

       BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        String path = MediaStore.Images.Media.insertImage(context.getApplicationContext().getContentResolver(),bitmap,"",null);


        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(0);
        progressDialog.setTitle("Data In Uploading");

        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        storageReference.child(storageFolder+System.currentTimeMillis()+".jpg")
                .putFile(Uri.parse(path))
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                photoData photoData = new photoData();
                photoData.setName(Home.username);
                photoData.setDescribtion(arrayList.get(position).getDescribtion());
                photoData.setUrl(taskSnapshot.getDownloadUrl().toString());
                photoData.setComment("");
                photoData.setUserimage(Home.img);
                photoData.setLike("");
                databaseReference.child(databaseFolder).push().setValue(photoData);
                progressDialog.dismiss();


            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override

            public void onProgress(final UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.setMax((int) taskSnapshot.getBytesTransferred()*100);
               progressDialog.show();

            }
        });
    }

    public void FancyComment(View view, final int position)
    {
        fancyShowCaseView = new FancyShowCaseView.Builder((Activity) context)
                .focusOn(view)
                .customView(R.layout.fancy_photo_comment, new OnViewInflateListener() {
                    @Override
                    public void onViewInflated(@NonNull View view) {

                        FloatingActionButton close = view.findViewById(R.id.fancy_photo_comment_close);
                        FloatingActionButton ok = view.findViewById(R.id.fancy_photo_comment_done);
                        final EditText editTextComment = view.findViewById(R.id.fancy_photo_comment_comment);
                        ListView listView = view.findViewById(R.id.fancy_photo_comment_listview);

                        c = arrayList.get(position).getComment();

                        if(c.length()!=0)
                        {
                            String a1[] = c.split(" , ");
                            arrlist = new ArrayList<>();
                            for (int i = 0; i <a1.length ; i++) {

                                String s[] = a1[i].split("---");
                                arrlist.add(new commentData(s[0],s[1],s[2]));
                               // Toast.makeText(context, ""+s[0]+s[1], Toast.LENGTH_SHORT).show();
                            }

                            comment_adapter comment_adapter = new comment_adapter(context,R.layout.comments_layout,arrlist);
                            listView.setAdapter(comment_adapter);

                        }

                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                fancyShowCaseView.hide();
                            }
                        });


                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                write_comment(Home.username,editTextComment.getText().toString(),position);
                                editTextComment.setText("");

                            }
                        });


                    }
                }).closeOnTouch(false).build();

        fancyShowCaseView.show();

    }


    public void write_comment(final String name, final String comment , int position)
    {

        databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child(databaseFolder).orderByChild("url").equalTo(arrayList.get(position).getUrl());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot d : dataSnapshot.getChildren())
                {
                  photoData photoData = d.getValue(photoData.class);
              String com = photoData.getComment()+ name+"---"+comment+"---"+Home.img+" , ";
                photoData.setComment(com);
                d.getRef().setValue(photoData);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void  FancyLike(View view, final int position)
    {
        fancyShowCaseView = new FancyShowCaseView.Builder((Activity) context)
                .focusOn(view)
                .customView(R.layout.fancy_photo_likes, new OnViewInflateListener() {
                    @Override
                    public void onViewInflated(@NonNull View view) {

                        FloatingActionButton close = view.findViewById(R.id.fancy_photo_likes_close);
                        ListView listView = view.findViewById(R.id.fancy_photo_likes_listview);


                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                fancyShowCaseView.hide();
                            }
                        });

                        String l = arrayList.get(position).getLike();
                            String arr[] = l.split("/");
                            String arr2[] = arr[0].split(",");
                        like_adapter arrayAdapter = new like_adapter(context,R.layout.likes_layout,arr2);
                        listView.setAdapter(arrayAdapter);



                    }
                }).closeOnTouch(false).build();

        fancyShowCaseView.show();
    }

    photoData photoData;
    public void write_like(final String name, int position)
    {


        databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child(databaseFolder).orderByChild("url").equalTo(arrayList.get(position).getUrl());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot d : dataSnapshot.getChildren())
                {
                     photoData = d.getValue(photoData.class);
                     String lik = photoData.getLike();

                    if(lik.equals("") || lik=="")
                    {
                        String l = photoData.getLike()+ name+"/"+1;
                        photoData.setLike(l);
                        d.getRef().setValue(photoData);

                    }else
                    {
                        String arr[] = lik.split("/");
                        String l =  arr[0]+","+name+"/"+(Integer.valueOf(arr[1])+1);
                        photoData.setLike(l);
                        d.getRef().setValue(photoData);


                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
