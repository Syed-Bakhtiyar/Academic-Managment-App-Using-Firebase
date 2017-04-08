package com.example.bakhtiyar.schoolreqruimentsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.bakhtiyar.schoolreqruimentsystem.Adapters.TotalLikesAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyTotalLikes extends AppCompatActivity {


    ArrayList<LikesClass> arrayList;

    TotalLikesAdapter totalLikesAdapter;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_total_likes);


        listView = (ListView) findViewById(R.id.list);

        arrayList = new ArrayList<>();

        totalLikesAdapter = new TotalLikesAdapter(arrayList,this);

        listView.setAdapter(totalLikesAdapter);


        FirebaseDatabase.getInstance().getReference().child("Likes").child(StaticVariables.teachersPostClass.getPush()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                LikesClass likesClass = dataSnapshot.getValue(LikesClass.class);

                arrayList.add(likesClass);

                totalLikesAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }
}
