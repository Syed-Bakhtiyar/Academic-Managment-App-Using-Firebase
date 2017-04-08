package com.example.bakhtiyar.schoolreqruimentsystem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters.InterViewClassList;
import com.example.bakhtiyar.schoolreqruimentsystem.MainFragments.InterViewClass;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewNotification extends Fragment {

    ListView listView;

    ArrayList<InterViewClass> arrayList;

    InterViewClass interViewClass;

    InterViewClassList interViewClassList;

    View v;


    public ViewNotification() {
        // Required empty public constructor
    }

//FirebaseDatabase.getInstance().getReference().child("PublicInterview").child(frkey)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_view_notification, container, false);


        arrayList = new ArrayList<>();

        interViewClassList = new InterViewClassList(arrayList,getContext());

        listView = (ListView) v.findViewById(R.id.list);


        listView.setAdapter(interViewClassList);

        FirebaseDatabase.getInstance().getReference().child("PublicInterview").child(StaticVariables.uuid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                interViewClass = dataSnapshot.getValue(InterViewClass.class);

                arrayList.add(interViewClass);

                interViewClassList.notifyDataSetChanged();

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



        return v;
    }

}
