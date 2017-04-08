package com.example.bakhtiyar.schoolreqruimentsystem;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters.StudentProfileListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.bakhtiyar.schoolreqruimentsystem.StaticVariables.studentInfo;

public class ForMyStudents extends AppCompatActivity {

    private ListView listView;
    private EditText editText;
    private ArrayList<StudentInfo> recieve;

    StudentProfileListAdapter studentProfileListAdapter;

    AlertDialog.Builder alert;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_my_students);


        editText = (EditText) findViewById(R.id.grpname);

        listView = (ListView) findViewById(R.id.list);

        alert = new AlertDialog.Builder(this);

//        added = new ArrayList<>();

        recieve = new ArrayList<>();

        studentProfileListAdapter = new StudentProfileListAdapter(recieve,getApplicationContext());

        listView.setAdapter(studentProfileListAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final int temp = position;

                alert.setPositiveButton("Suspend", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase.getInstance().getReference().child("MyStudents").child(StaticVariables.uuid).child(recieve.get(temp).getUid()).removeValue();

                        recieve.remove(temp);

                        studentProfileListAdapter.notifyDataSetChanged();

                        // for current students List


                    }
                });
                alert.setNegativeButton("Cancel",null);

                alert.show();


            }
        });


        FirebaseDatabase.getInstance().getReference().child("MyStudents").child(StaticVariables.uuid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                studentInfo = dataSnapshot.getValue(StudentInfo.class);

                recieve.add(studentInfo);

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


    }
}
