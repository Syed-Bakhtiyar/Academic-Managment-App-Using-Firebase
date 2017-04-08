package com.example.bakhtiyar.schoolreqruimentsystem;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters.StudentProfileListAdapter;
import com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters.TeachersProfileListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class ViewStudentsWhoApplied extends Fragment {

    View v;

    int temp;

    ListView listView;

    ArrayList<StudentInfo> arrayList;

    StudentProfileListAdapter studentProfileListAdapter;

    StudentInfo studentInfo;

    ApprovalClass approvalClass;

    AlertDialog.Builder alert;





    public ViewStudentsWhoApplied() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//
        v=inflater.inflate(R.layout.fragment_view_students_who_applied, container, false);

        alert = new AlertDialog.Builder(getContext());

        arrayList = new ArrayList<>();

        listView = (ListView) v.findViewById(R.id.list);

        studentProfileListAdapter = new StudentProfileListAdapter(arrayList,getContext());

        listView.setAdapter(studentProfileListAdapter);




        FirebaseDatabase.getInstance().getReference().child("ApplyForAddmission").child(StaticVariables.uuid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                studentInfo = dataSnapshot.getValue(StudentInfo.class);

                arrayList.add(studentInfo);

                studentProfileListAdapter.notifyDataSetChanged();

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

                alert.setPositiveButton("Approve", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        FirebaseDatabase.getInstance().getReference().child("MyStudents").child(StaticVariables.uuid).child(arrayList.get(temp).getUid()).setValue(arrayList.get(temp));

                        approvalClass = new ApprovalClass(StaticVariables.uuid,arrayList.get(temp).getUid(),StaticVariables.managerInfo.getCampusname());

                        FirebaseDatabase.getInstance().getReference().child("StudentApproval").child(approvalClass.getTuid()).child(approvalClass.getMuid()).setValue(approvalClass);

                        Toast.makeText(getContext(), "Approved", Toast.LENGTH_SHORT).show();



                    }
                });

                alert.setNegativeButton("For Test", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        StaticVariables.check = 1;

                        Intent intent = new Intent(getActivity(),CallingInterview.class);

                        intent.putExtra("key",arrayList.get(temp).getUid());

                        startActivity(intent);


                    }
                });

                alert.setNeutralButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        FirebaseDatabase.getInstance().getReference().child("ApplyForAddmission").child(StaticVariables.uuid).child(arrayList.get(temp).getUid()).removeValue();

                        arrayList.remove(temp);

                        studentProfileListAdapter.notifyDataSetChanged();
                    }
                });



                alert.show();

            }
        });


        return v;
    }
}
