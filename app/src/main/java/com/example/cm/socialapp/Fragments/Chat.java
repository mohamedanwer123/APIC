package com.example.cm.socialapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.cm.socialapp.Adapters.chat_people_adapter;
import com.example.cm.socialapp.Models.chat_people_Data;
import com.example.cm.socialapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Chat extends Fragment {


    public Chat() {
        // Required empty public constructor
    }

     chat_people_adapter chat_people_adapter ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        final ListView listView = view.findViewById(R.id.chat_fragment_listview);
        final ArrayList<chat_people_Data> arrayList = new ArrayList<>();


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("LoginData").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot d : dataSnapshot.getChildren())
                {
                    arrayList.add(d.getValue(chat_people_Data.class));
                }

                chat_people_adapter = new chat_people_adapter(getActivity(),R.layout.chat_people,arrayList);
                listView.setAdapter(chat_people_adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

}
