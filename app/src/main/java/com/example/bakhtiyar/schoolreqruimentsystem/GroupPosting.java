package com.example.bakhtiyar.schoolreqruimentsystem;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters.TeacherPostListAdapter;
import com.example.bakhtiyar.schoolreqruimentsystem.MainFragments.TeachersPostClass;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;

public class GroupPosting extends AppCompatActivity {

    View v;

    int temp;

    File localFile;

    ListView listView;

    ArrayList<TeachersPostClass> arrayList;

    TeachersPostClass teachersPostClass;

    TeacherPostListAdapter teacherPostListAdapter;

    StorageReference storage;

    ProgressDialog progressDialog;

    AlertDialog.Builder alert;

    EditText posttxt;

    DownloadManager downloadManager;

    LinearLayout linearLayout;

    Uri uri;

    FloatingActionButton fab;

    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_posting);

        downloadManager = (DownloadManager) getApplicationContext().getSystemService(Activity.DOWNLOAD_SERVICE);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        listView = (ListView) findViewById(R.id.list);

        fab.setVisibility(View.GONE);

        listView.setVisibility(View.GONE);


        for (int i=0;i<StaticVariables.groupClass.arrayList.size();i++){

            if(StaticVariables.uuid.equals(StaticVariables.groupClass.arrayList.get(i).getUid())){


                check++;

                fab.setVisibility(View.VISIBLE);

                listView.setVisibility(View.VISIBLE);

                break;

            }


        }

        if(StaticVariables.uuid.equals(StaticVariables.groupClass.getTuid())) {


            fab.setVisibility(View.VISIBLE);

            listView.setVisibility(View.VISIBLE);

            check++;

        }

        storage = FirebaseStorage.getInstance().getReference().child("GroupPost").child(StaticVariables.manuid);



        arrayList = new ArrayList<>();

        teacherPostListAdapter = new TeacherPostListAdapter(arrayList, getApplicationContext());

        listView.setAdapter(teacherPostListAdapter);

        FirebaseDatabase.getInstance().getReference().child("GroupPost").child(StaticVariables.manuid).child(StaticVariables.groupClass.getPush()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                teachersPostClass = dataSnapshot.getValue(TeachersPostClass.class);

                Log.d("tata", "onChildAdded: "+dataSnapshot.getValue()+"  "+teachersPostClass.getName());

                arrayList.add(teachersPostClass);

                teacherPostListAdapter.notifyDataSetChanged();


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

                StaticVariables.teachersPostClass = arrayList.get(temp);

                StaticVariables.name = arrayList.get(temp).getName();

                if(StaticVariables.isteacher){

                    StaticVariables.url = StaticVariables.teachersHire.getImglink();

                }

                   /////jjkjkj



                //Toast.makeText(GroupPosting.this, ""+arrayList.get(temp).getPush(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(GroupPosting.this,ActivityForComment.class);

                startActivity(intent);



            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GroupPosting.this, TeaherPosting.class);

                intent.putExtra("data",2);


                startActivity(intent);

            }
        });






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.



            getMenuInflater().inflate(R.menu.my_menu, menu);




        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add) {

            if(check!=0){
            startActivity(new Intent(GroupPosting.this,AddStudent.class));

            }
            else {

                Toast.makeText(this, "Sorry you are not member", Toast.LENGTH_SHORT).show();

            }
            }

        return super.onOptionsItemSelected(item);
    }

}
