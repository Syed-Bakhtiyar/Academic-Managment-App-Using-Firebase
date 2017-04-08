package com.example.bakhtiyar.schoolreqruimentsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {

    int a;

    AlertDialog.Builder alert;



    LayoutInflater infl;


    ManagerInfo managerInfo;

    TextView textView;

    WriteYourVacancies writeYourVacancies;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        alert = new AlertDialog.Builder(this);


        func();



        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                View v = infl.from(getApplicationContext()).inflate(R.layout.job_posting_layout,null);

                startActivity(new Intent(ProfileActivity.this,JobPostActivity.class));
            }
        });


        findViewById(R.id.create).setVisibility(View.VISIBLE);

        findViewById(R.id.fab).setVisibility(View.GONE);

        textView = (TextView) findViewById(R.id.text);

        findViewById(R.id.post).setVisibility(View.GONE);

        findViewById(R.id.teacher_hiring).setVisibility(View.GONE);

        findViewById(R.id.stadmission).setVisibility(View.GONE);

        findViewById(R.id.crteacher).setVisibility(View.GONE);

        findViewById(R.id.crstud).setVisibility(View.GONE);

        findViewById(R.id.studresult).setVisibility(View.GONE);

        findViewById(R.id.studleave).setVisibility(View.GONE);

        findViewById(R.id.teachSal).setVisibility(View.GONE);

        findViewById(R.id.studfee).setVisibility(View.GONE);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.setPositiveButton("ok",null);
                alert.show();
            }
        });


        findViewById(R.id.post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,ManagerViewPost.class));
            }
        });


        findViewById(R.id.create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ProfileActivity.this,CreateManagersProfile.class));

            }
        });


        findViewById(R.id.stadmission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ProfileActivity.this,PostForAdmission.class));

            }
        });



        findViewById(R.id.teacher_hiring).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,TeachersHiring.class));
            }
        });
    }

    public void func(){

        FirebaseDatabase.getInstance().getReference().child("Manager").child(StaticVariables.uuid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                managerInfo = dataSnapshot.getValue(ManagerInfo.class);

                if(managerInfo.getCampusname().equals(null)||managerInfo.getCampusname().equals("")){


                    findViewById(R.id.create).setVisibility(View.VISIBLE);

                    findViewById(R.id.post).setVisibility(View.GONE);

                    findViewById(R.id.fab).setVisibility(View.GONE);

                    textView.setVisibility(View.GONE);

                    findViewById(R.id.teacher_hiring).setVisibility(View.GONE);

                    findViewById(R.id.stadmission).setVisibility(View.GONE);

                    findViewById(R.id.crteacher).setVisibility(View.GONE);

                    findViewById(R.id.crstud).setVisibility(View.GONE);

                    findViewById(R.id.studresult).setVisibility(View.GONE);

                    findViewById(R.id.studleave).setVisibility(View.GONE);

                    findViewById(R.id.teachSal).setVisibility(View.GONE);

                    findViewById(R.id.studfee).setVisibility(View.GONE);



                }
                else {

                    alert.setMessage(managerInfo.getCampusname()+"\n"+
                    managerInfo.getEmail()+"\n"+managerInfo.getAddress()+"\n"+managerInfo.getManagername()+"\n"+managerInfo.getPhone());

                    StaticVariables.managerInfo = managerInfo;


                    textView.setVisibility(View.VISIBLE);

                    findViewById(R.id.post).setVisibility(View.VISIBLE);

                    textView.setText(managerInfo.getCampusname());

                    findViewById(R.id.create).setVisibility(View.GONE);

                    findViewById(R.id.fab).setVisibility(View.VISIBLE);

                    findViewById(R.id.teacher_hiring).setVisibility(View.VISIBLE);

                    findViewById(R.id.stadmission).setVisibility(View.VISIBLE);

                    findViewById(R.id.crteacher).setVisibility(View.VISIBLE);

                    findViewById(R.id.crstud).setVisibility(View.VISIBLE);

                    findViewById(R.id.studresult).setVisibility(View.VISIBLE);

                    findViewById(R.id.studleave).setVisibility(View.VISIBLE);

                    findViewById(R.id.teachSal).setVisibility(View.VISIBLE);

                    findViewById(R.id.studfee).setVisibility(View.VISIBLE);






                }



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
