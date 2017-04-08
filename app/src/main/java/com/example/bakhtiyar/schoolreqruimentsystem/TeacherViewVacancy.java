package com.example.bakhtiyar.schoolreqruimentsystem;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters.ManagerViewPostList;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherViewVacancy extends Fragment {

    int temp;

    ArrayList<WriteYourVacancies> arrayList;

    AlertDialog.Builder alert;

    ManagerViewPostList managerViewPostList;

    ListView listView;

    View v;

    public TeacherViewVacancy() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_teacher_view_vacancy, container, false);

        alert = new AlertDialog.Builder(getContext());




        arrayList = new ArrayList<>();

        managerViewPostList = new ManagerViewPostList(arrayList,getContext());

        listView = (ListView) v.findViewById(R.id.list);


        listView.setAdapter(managerViewPostList);

        FirebaseDatabase.getInstance().getReference().child("PublicJob").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                WriteYourVacancies writeYourVacancies = dataSnapshot.getValue(WriteYourVacancies.class);

                arrayList.add(writeYourVacancies);

                managerViewPostList.notifyDataSetChanged();


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

                alert.setTitle("Apply for this job click ok");

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        if(StaticVariables.teachersHire == null){

                            Toast.makeText(getContext(), "Please Complete your profile", Toast.LENGTH_SHORT).show();

                            return;
                        }

                        FirebaseDatabase.getInstance().getReference().child("Apply").child(arrayList.get(temp).getUid()).child(StaticVariables.uuid).setValue(StaticVariables.teachersHire);

                        Toast.makeText(getContext(), "Applied", Toast.LENGTH_SHORT).show();


                    }
                });

                alert.setNegativeButton("Cancel",null);

                alert.show();



            }
        });

        return v;
    }

}
