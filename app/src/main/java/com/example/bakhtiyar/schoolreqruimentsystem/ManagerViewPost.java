package com.example.bakhtiyar.schoolreqruimentsystem;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters.ManagerViewPostList;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ManagerViewPost extends AppCompatActivity {

    ArrayList<WriteYourVacancies> arrayList;

    ListView listView;

    int temp;


    LinearLayout linearLayout;

    EditText qalification, skills, experience;

    AlertDialog.Builder alert;


    ManagerViewPostList managerViewPostList;

    WriteYourVacancies writeYourVacancies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_view_post);

        alert = new AlertDialog.Builder(this);











        arrayList = new ArrayList<>();

        managerViewPostList = new ManagerViewPostList(arrayList,this);

        listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(managerViewPostList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                temp = i;


                linearLayout = new LinearLayout(getApplicationContext());

                linearLayout.setOrientation(LinearLayout.VERTICAL);



                qalification = new EditText(getApplicationContext());

                skills = new EditText(getApplicationContext());

                experience = new EditText(getApplicationContext());

                qalification.setTextColor(Color.BLACK);

                experience.setTextColor(Color.BLACK);

                skills.setTextColor(Color.BLACK);

                qalification.setText(arrayList.get(temp).getQual());

                experience.setText(arrayList.get(temp).getExp());

                skills.setText(arrayList.get(temp).getSkills());


                linearLayout.addView(qalification);

                linearLayout.addView(skills);

                linearLayout.addView(experience);


                alert.setView(linearLayout);

                alert.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String q,e,s;
                        q = qalification.getText().toString().trim();

                        e = experience.getText().toString().trim();

                        s = skills.getText().toString().trim();

                        if(TextUtils.isEmpty(q)|| TextUtils.isEmpty(e)||TextUtils.isEmpty(s)){


                            Toast.makeText(ManagerViewPost.this, "Please complete your Vacancies", Toast.LENGTH_SHORT).show();

                            return;

                        }else {

                            writeYourVacancies = new WriteYourVacancies(q,e,s,arrayList.get(temp).getUid(),arrayList.get(temp).getPush(),StaticVariables.managerInfo.getCampusname());

                            FirebaseDatabase.getInstance().getReference().child("Job").child(arrayList.get(temp).getUid()).child(writeYourVacancies.getPush()).setValue(writeYourVacancies);

                            FirebaseDatabase.getInstance().getReference().child("PublicJob").child(arrayList.get(temp).getPush()).setValue(writeYourVacancies);

                            Toast.makeText(ManagerViewPost.this, "Submitted", Toast.LENGTH_SHORT).show();

                            arrayList.remove(temp);

                            arrayList.add(temp,writeYourVacancies);

                            managerViewPostList.notifyDataSetChanged();

                            qalification.setText("");

                            skills.setText("");

                            experience.setText("");


                        }



                    }
                });

                alert.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        FirebaseDatabase.getInstance().getReference().child("Job").child(arrayList.get(temp).getUid()).child(writeYourVacancies.getPush()).removeValue();

                        FirebaseDatabase.getInstance().getReference().child("PublicJob").child(arrayList.get(temp).getPush()).removeValue();

                        arrayList.remove(temp);

                        managerViewPostList.notifyDataSetChanged();

                        Toast.makeText(ManagerViewPost.this, "Deleted", Toast.LENGTH_SHORT).show();


                    }
                });


                alert.show();








            }
        });



        FirebaseDatabase.getInstance().getReference().child("Job").child(StaticVariables.uuid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                writeYourVacancies = dataSnapshot.getValue(WriteYourVacancies.class);

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

    }
}
