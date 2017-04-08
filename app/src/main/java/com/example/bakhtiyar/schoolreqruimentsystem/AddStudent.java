package com.example.bakhtiyar.schoolreqruimentsystem;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters.StudentGroupListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.bakhtiyar.schoolreqruimentsystem.StaticVariables.studentInfo;

public class AddStudent extends AppCompatActivity {

    StudentGroupListAdapter studentGroupListAdapter;

    ArrayList<StudentInfo> arrayList;

    ArrayList<StudentInfo> newArrayList;

    AlertDialog.Builder alert;

    ArrayList<Integer> indexes;

    boolean found = false;

    StudentInfo studentInfo;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        listView = (ListView) findViewById(R.id.list);

//        for(int i =0 ; i<StaticVariables.recieve.size();i++){
//
//            Log.d("arraylist", "users: "+StaticVariables.recieve.get(i).getName());
//
//
//        }

//        Log.d("arraylist", "\\n\\n");

//        for (int j=0;j<StaticVariables.groupClass.arrayList.size();j++){
//
//            Log.d("arraylist", "group members: "+StaticVariables.recieve.get(j).getName());
//
//        }




        arrayList = new ArrayList<>();

        newArrayList = new ArrayList<>();




//        for (int i=0;i<newArrayList.size();i++){
//
//            Log.d("Answer", "onCreate: "+newArrayList.get(i).getName());
//
//        }



        alert = new AlertDialog.Builder(this);

//        indexes = new ArrayList<>();

        studentGroupListAdapter = new StudentGroupListAdapter(newArrayList,getApplicationContext());

        listView.setAdapter(studentGroupListAdapter);

        for(int i =0 ; i<StaticVariables.recieve.size();i++){

            found = false;

            for (int j=0;j<StaticVariables.groupClass.arrayList.size();j++){

                if (StaticVariables.recieve.get(i).getUid().equals(StaticVariables.groupClass.arrayList.get(j).getUid())){

                    found = true;

                    break;

                }



            }

            if(found ==  false) {

                newArrayList.add(StaticVariables.recieve.get(i));

                studentGroupListAdapter.notifyDataSetChanged();

            }


        }






        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               final int temp = position;


                listView.getChildAt(temp).setBackgroundColor(Color.BLUE);

                arrayList.add(newArrayList.get(temp));




            }
        });


        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ArrayList<StudentInfo>  temp = arrayList;

                for(int i=0;i< StaticVariables.groupClass.arrayList.size();i++){

                    temp.add(StaticVariables.groupClass.arrayList.get(i));

                }

//                arrayList = StaticVariables.groupClass.arrayList;



                if(arrayList.size()==0){

                    Toast.makeText(AddStudent.this, "There is no selected Members", Toast.LENGTH_SHORT).show();


                }
                else {

                   // alert.setMessage("This will discard your previous members");

                    alert.setPositiveButton("Ok ?", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

//                            for(int i =0;i<newArrayList.size();i++ ){
//
//                                arrayList.add(newArrayList.get(i));
//
//                            }




                            FirebaseDatabase.getInstance().getReference().child("Groups").child(StaticVariables.manuid).child(StaticVariables.groupClass.getPush()).child("arrayList").setValue(temp);

                            finish();

                        }
                    });

                    alert.setNegativeButton("Cancel",null);

                    alert.show();


                }


            }
        });



    }
}
