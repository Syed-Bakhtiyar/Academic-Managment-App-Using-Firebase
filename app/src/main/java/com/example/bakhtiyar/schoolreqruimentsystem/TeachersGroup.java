package com.example.bakhtiyar.schoolreqruimentsystem;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters.StudentGroupListAdapter;
import com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters.StudentProfileListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeachersGroup extends Fragment {


    ArrayList<StudentInfo> recieve, added;

    StudentInfo studentInfo;

    StudentGroupListAdapter studentProfileListAdapter;

    View v;

    ListView listView;

    int temp;

    String grp = null;

    EditText editText;

    GroupClass groupClass;

    public TeachersGroup() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_teachers_group, container, false);

        editText = (EditText) v.findViewById(R.id.grpname);

        listView = (ListView) v.findViewById(R.id.list);

        added = new ArrayList<>();

        recieve = new ArrayList<>();

        v.findViewById(R.id.create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                grp = editText.getText().toString().trim();


                if(grp == null || grp.equals("")){

                    Toast.makeText(getContext(), "Please Type your Group Name", Toast.LENGTH_SHORT).show();

                    return;

                }
                if (added.size() == 0){

                    Toast.makeText(getContext(), "Please Select Students", Toast.LENGTH_SHORT).show();

                    return;
                }




                groupClass = new GroupClass(StaticVariables.teachersHire.getImglink(),added,grp,StaticVariables.manuid,FirebaseDatabase.getInstance().getReference().child("Groups").child(StaticVariables.manuid).push().getKey(),StaticVariables.uuid);

                FirebaseDatabase.getInstance().getReference().child("Groups").child(StaticVariables.manuid).child(groupClass.getPush()).setValue(groupClass);

                Toast.makeText(getContext(), "Group Created", Toast.LENGTH_SHORT).show();

            }
        });

        studentProfileListAdapter = new StudentGroupListAdapter(recieve,getContext());

        listView.setAdapter(studentProfileListAdapter);

        FirebaseDatabase.getInstance().getReference().child("MyStudents").child(StaticVariables.manuid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                studentInfo = dataSnapshot.getValue(StudentInfo.class);

                recieve.add(studentInfo);

                StaticVariables.recieve = recieve;

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

                listView.getChildAt(temp).setBackgroundColor(Color.YELLOW);

                added.add(recieve.get(temp));


            }
        });


        return v;
    }

}
