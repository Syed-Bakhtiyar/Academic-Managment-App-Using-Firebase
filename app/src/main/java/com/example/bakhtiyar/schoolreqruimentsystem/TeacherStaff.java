package com.example.bakhtiyar.schoolreqruimentsystem;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters.TeachersProfileListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherStaff extends Fragment {


    ArrayList<TeachersHire> arrayList;

    TeachersHire teachersHire;

    ListView listView;

    TeachersProfileListAdapter teachersProfileListAdapter;

    int temp;

    View v;

    AlertDialog.Builder alert;

    public TeacherStaff() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_teacher_staff, container, false);

        arrayList = new ArrayList<>();

        listView = (ListView) v.findViewById(R.id.list);

        alert = new AlertDialog.Builder(getContext());

        alert.setTitle("For Suspend");

        alert.setMessage("Are you sure you wan't to suspend ?");


        teachersProfileListAdapter = new TeachersProfileListAdapter(arrayList,getContext());

        listView.setAdapter(teachersProfileListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



                temp = i;

                alert.setPositiveButton("Suspend", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        FirebaseDatabase.getInstance().getReference().child("MyTeachers").child(StaticVariables.uuid).child(arrayList.get(temp).getUid()).removeValue();

                        FirebaseDatabase.getInstance().getReference().child("Approval").child(teachersHire.getUid()).child(StaticVariables.uuid).removeValue();

                        arrayList.remove(temp);

                        teachersProfileListAdapter.notifyDataSetChanged();


                    }
                });

                alert.show();



            }
        });

        FirebaseDatabase.getInstance().getReference().child("MyTeachers").child(StaticVariables.uuid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                teachersHire = dataSnapshot.getValue(TeachersHire.class);

                StaticVariables.teachersHire = teachersHire;

                Log.d("data", "onChildAdded: "+teachersHire.getName());

                StaticVariables.arrayList.add(teachersHire);



                arrayList.add(teachersHire);

                teachersProfileListAdapter.notifyDataSetChanged();


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
