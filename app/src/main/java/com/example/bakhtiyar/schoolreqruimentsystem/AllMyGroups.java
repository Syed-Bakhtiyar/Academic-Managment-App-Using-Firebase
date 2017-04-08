package com.example.bakhtiyar.schoolreqruimentsystem;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters.GroupListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllMyGroups extends Fragment {

    View v;

    GroupListAdapter groupListAdapter;

    ArrayList<GroupClass> arrayList;

    ListView listView;

    GroupClass groupClass;

    int temp;

    public AllMyGroups() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_all_my_groups, container, false);

        listView = (ListView) v.findViewById(R.id.list);

        arrayList = new ArrayList<>();

        groupListAdapter = new GroupListAdapter(arrayList,getContext());

        listView.setAdapter(groupListAdapter);

        FirebaseDatabase.getInstance().getReference().child("Groups").child(StaticVariables.manuid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                groupClass = dataSnapshot.getValue(GroupClass.class);

                arrayList.add(groupClass);

                groupListAdapter.notifyDataSetChanged();

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                temp = i;

                StaticVariables.groupClass = arrayList.get(temp);

                Intent intent = new Intent(getActivity(),GroupPosting.class);


                startActivity(intent);


            }
        });




        return v;
    }

}
