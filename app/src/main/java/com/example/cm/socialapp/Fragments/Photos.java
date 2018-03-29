package com.example.cm.socialapp.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cm.socialapp.Activities.Home;
import com.example.cm.socialapp.Adapters.photoAdapter;
import com.example.cm.socialapp.Models.MSG;
import com.example.cm.socialapp.Models.photoData;
import com.example.cm.socialapp.R;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import me.toptas.fancyshowcase.FancyShowCaseView;
import me.toptas.fancyshowcase.OnViewInflateListener;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class Photos extends Fragment {


    View view;
    FloatingActionButton floatingActionButton;
    FancyShowCaseView fancyShowCaseView;
    ListView listView;

    FloatingActionButton close;
    EditText des;
    Button post;
    ImageView img;
    CircleImageView camera,gallery;

    DatabaseReference databaseReference;
    StorageReference storageReference;

    final int galleryCode = 100;
    final int cameraCode = 200;

    Uri imgUri;

    final String storageFolder="Photos_Page/";
    final String databaseFolder="UserData";


    photoAdapter photoAdapter;
    ArrayList<photoData> arrayList;
    ArrayList<photoData> arrayList22;


    public Photos() {

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_photos, container, false);
        init();
        permession();



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewBtn) {
                fancy(viewBtn);

            }
        });


       databaseReference.child(databaseFolder).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                arrayList.clear();

                photoData photoData = null;
                    for (DataSnapshot d : dataSnapshot.getChildren()) {


                         photoData = new photoData();
                        photoData.setName(d.getValue(com.example.cm.socialapp.Models.photoData.class).getName());
                        photoData.setDescribtion(d.getValue(com.example.cm.socialapp.Models.photoData.class).getDescribtion());
                        photoData.setUrl(d.getValue(com.example.cm.socialapp.Models.photoData.class).getUrl());
                        photoData.setComment(d.getValue(com.example.cm.socialapp.Models.photoData.class).getComment());
                        photoData.setLike(d.getValue(com.example.cm.socialapp.Models.photoData.class).getLike());
                        photoData.setUserimage(d.getValue(com.example.cm.socialapp.Models.photoData.class).getUserimage());
                        arrayList.add(photoData);
                    }


                     arrayList22 = new ArrayList<>();
                    arrayList22.clear();
                    for (int i = arrayList.size() - 1; i >= 0; i--) {

                        arrayList22.add(arrayList.get(i));
                    }


                    photoAdapter = new photoAdapter(getActivity(), R.layout.photos_items, arrayList22);
                    Parcelable state = listView.onSaveInstanceState();
                    listView.setAdapter(photoAdapter);
                    listView.onRestoreInstanceState(state);
                //listView.setSelection(3);




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getActivity(), ""+databaseError.getDetails(), Toast.LENGTH_LONG).show();
            }
        });



        return view;
    }

    public void init()
    {
        floatingActionButton = view.findViewById(R.id.photos_fragment_camerBtn);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        arrayList = new ArrayList<>();
        listView = view.findViewById(R.id.photos_fragment_listview);
    }

    public void fancy(View v)
    {
        fancyShowCaseView = new FancyShowCaseView.Builder(getActivity())
                .focusOn(v)
                .customView(R.layout.fancy_photo_layout, new OnViewInflateListener() {
                    @Override
                    public void onViewInflated(@NonNull View viewFancy) {

                        fancyinit(viewFancy);

                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                fancyShowCaseView.hide();
                            }
                        });

                        gallery.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intent,galleryCode);
                            }
                        });


                        camera.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent,cameraCode);
                            }
                        });

                        post.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if(imgUri == null || imgUri.equals(""))
                                {
                                MSG.msg(getActivity(),"Empty","Please Select Image","#d3d3d3",R.drawable.empty);
                                }else
                                {
                                    uploadData();


                                }

                            }
                        });

                    }
                }).closeOnTouch(false).build();

        fancyShowCaseView.show();
    }


    public  void fancyinit(View viewInit)
    {
        close = viewInit.findViewById(R.id.fancy_photo_btn_close);
        des = viewInit.findViewById(R.id.fancy_photo_edittext);
        img = viewInit.findViewById(R.id.fancy_photo_image);
        camera = viewInit.findViewById(R.id.fancy_photo_camera);
        gallery = viewInit.findViewById(R.id.fancy_photo_gallery);
        post = viewInit.findViewById(R.id.fancy_photo_btn_Post);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == galleryCode && resultCode == RESULT_OK)
        {
            imgUri = data.getData();
            img.setImageURI(imgUri);


        }else if(requestCode == cameraCode && resultCode == RESULT_OK)
        {

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            String path = MediaStore.Images.Media.insertImage(getActivity().getApplicationContext().getContentResolver(),bitmap,"",null);
            imgUri = Uri.parse(path);
            img.setImageURI(imgUri);

        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void permession()
    {
        requestPermissions(new String[]{android.Manifest.permission.INTERNET, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE},123);
    }

    public void uploadData()
    {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Data In Uploading");
        progressDialog.setProgressStyle(0);

        storageReference.child(storageFolder+System.currentTimeMillis()+".jpg").putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                photoData photoData = new photoData(Home.username,""+des.getText().toString(),taskSnapshot.getDownloadUrl().toString(),"","",Home.img);
                databaseReference.child(databaseFolder).push().setValue(photoData);
                progressDialog.dismiss();
                des.setText("");
                imgUri = null;

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.show();
                    progressDialog.setMax((int) taskSnapshot.getBytesTransferred()*100);

            }
        });
    }



}
