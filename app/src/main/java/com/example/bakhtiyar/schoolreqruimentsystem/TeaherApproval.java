package com.example.bakhtiyar.schoolreqruimentsystem;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters.TeachersProfileListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeaherApproval extends Fragment {

    ArrayList<TeachersHire> arrayList;

    TeachersProfileListAdapter teachersProfileListAdapter;

    TeachersHire teachersHire;

    ListView listView;

    View v;

    int temp;

    ApprovalClass approvalClass;

    AlertDialog.Builder alert;

    public TeaherApproval() {
        // Required empty public constructor
    }
//FirebaseDatabase.getInstance().getReference().child("Apply").child(arrayList.get(temp).getUid())

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_teaher_approval, container, false);

        alert = new AlertDialog.Builder(getContext());

        alert.setTitle("Approve or not?");



        arrayList = new ArrayList<>();

        teachersProfileListAdapter = new TeachersProfileListAdapter(arrayList,getContext());

        listView = (ListView) v.findViewById(R.id.list);

        listView.setAdapter(teachersProfileListAdapter);


        FirebaseDatabase.getInstance().getReference().child("Apply").child(StaticVariables.uuid).addChildEventListener(new ChildEventListener() {
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


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                temp =i;

                alert.setNeutralButton("For Call Inter View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(getActivity(),CallingInterview.class);

                        intent.putExtra("key",arrayList.get(temp).getUid());

                        startActivity(intent);

                    }
                });

                alert.setPositiveButton("Approve", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        FirebaseDatabase.getInstance().getReference().child("MyTeachers").child(StaticVariables.uuid).child(arrayList.get(temp).getUid()).setValue(arrayList.get(temp));

                        approvalClass = new ApprovalClass(StaticVariables.uuid,arrayList.get(temp).getUid(),StaticVariables.managerInfo.getCampusname());

                        FirebaseDatabase.getInstance().getReference().child("Approval").child(approvalClass.getTuid()).child(approvalClass.getMuid()).setValue(approvalClass);

                        Toast.makeText(getContext(), "Approved", Toast.LENGTH_SHORT).show();

                    }
                });


                alert.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        FirebaseDatabase.getInstance().getReference().child("Apply").child(StaticVariables.uuid).child(arrayList.get(temp).getUid()).removeValue();

                        arrayList.remove(temp);

                        teachersProfileListAdapter.notifyDataSetChanged();


                    }
                });

                alert.show();



            }
        });


        return v;
    }

}
