package com.example.bakhtiyar.schoolreqruimentsystem;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.bakhtiyar.schoolreqruimentsystem.ForDate.Date;
import com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters.InterViewClassList;
import com.example.bakhtiyar.schoolreqruimentsystem.MainFragments.InterViewClass;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class CallingInterview extends AppCompatActivity {

    InterViewClass interViewClass;

    ListView listView;

    LinearLayout linearLayout;

    String frkey;

    EditText textView;

    EditText txt_desc;

    TextView texxt;

    static final int D_ID=0;

    RadioButton r_am, r_pm;

    String format = null;

    int temp;

    String date;

    String time;

    String description;

    String name;

    Bundle bundle;

    AlertDialog.Builder alert;

    ArrayList<InterViewClass> arrayList;

    InterViewClassList interViewClassList;

    TimePickerDialog.OnTimeSetListener kTimePickerListner = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int h, int m) {

            hour =h;

            mint=m;

            texxt.setText("Hour: "+hour+" Minute "+mint);
        }
    };

    int hour;

    int mint;

    @Override
    protected void onStart() {
        super.onStart();

       textView  = (EditText) findViewById(R.id.calender);


        textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){

                    Date d = new Date(view);

                    android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

                    d.show(ft,"DatePicker");


                }
            }
        });


    }

    @Override
    protected Dialog onCreateDialog(int id) {
//        return super.onCreateDialog(id);



        if(id==D_ID){

            return new TimePickerDialog(CallingInterview.this, kTimePickerListner, hour,mint,true);

        }

        return null;

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling_interview);

        alert = new AlertDialog.Builder(this);

        arrayList = new ArrayList<>();


        interViewClassList = new InterViewClassList(arrayList,this);

        bundle = getIntent().getExtras();

        frkey = bundle.getString("key");

        txt_desc = (EditText) findViewById(R.id.come);

        r_am = (RadioButton) findViewById(R.id.am);

        r_pm = (RadioButton) findViewById(R.id.pm);

        texxt = (TextView) findViewById(R.id.clock);

        texxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog(D_ID);

            }
        });


        r_am.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                format = "A.M";
            }
        });

        r_pm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                format = "P.M";
            }
        });


        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                description = txt_desc.getText().toString().trim();

                date = textView.getText().toString().trim();

                time =  texxt.getText().toString().trim();

                time += "  " +format;

                if (description.equals("Write your Discription") || description.equals("") ||
                        date.equals("Click for select interview date") ||
                        time.equals("Click for select interview date") ||
                        format.equals(null)){

                    Toast.makeText(CallingInterview.this, "Please Complete Form", Toast.LENGTH_SHORT).show();

                    txt_desc.setHint("Write your Discription");

                    textView.setHint("Click for select interview date");

                    texxt.setText("Click for select interview date");

                    r_pm.setChecked(false);

                    r_am.setChecked(false);

                }
                else {

                    if(StaticVariables.check==1){

                        interViewClass = new InterViewClass(frkey, StaticVariables.uuid, StaticVariables.managerInfo.getCampusname(), description, date, time, FirebaseDatabase.getInstance().getReference().child("MyInterView").child(StaticVariables.uuid).push().getKey());

                        FirebaseDatabase.getInstance().getReference().child("MyTest").child(StaticVariables.uuid).child(interViewClass.getPush()).setValue(interViewClass);

                        FirebaseDatabase.getInstance().getReference().child("PublicTest").child(frkey).child(interViewClass.getPush()).setValue(interViewClass);

                        Toast.makeText(CallingInterview.this, "Your Test is submitted", Toast.LENGTH_SHORT).show();




                    }
                    else {


                        interViewClass = new InterViewClass(frkey, StaticVariables.uuid, StaticVariables.managerInfo.getCampusname(), description, date, time, FirebaseDatabase.getInstance().getReference().child("MyInterView").child(StaticVariables.uuid).push().getKey());

                        FirebaseDatabase.getInstance().getReference().child("MyInterView").child(StaticVariables.uuid).child(interViewClass.getPush()).setValue(interViewClass);

                        FirebaseDatabase.getInstance().getReference().child("PublicInterview").child(frkey).child(interViewClass.getPush()).setValue(interViewClass);

                        Toast.makeText(CallingInterview.this, "Your Interview is submitted", Toast.LENGTH_SHORT).show();
                    }


                    finish();

                }


            }
        });

        findViewById(R.id.See).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                linearLayout = new LinearLayout(getApplicationContext());

                linearLayout.setOrientation(LinearLayout.VERTICAL);

                listView = new ListView(getApplicationContext());


                linearLayout.addView(listView);

                listView.setAdapter(interViewClassList);


                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {


                        temp = i;




                        FirebaseDatabase.getInstance().getReference().child("MyInterView").child(StaticVariables.uuid).child(arrayList.get(temp).getPush()).removeValue();

                        FirebaseDatabase.getInstance().getReference().child("PublicInterview").child(arrayList.get(temp).getFrkey()).child(interViewClass.getPush()).removeValue();

                        arrayList.remove(temp);

                        interViewClassList.notifyDataSetChanged();

                        Toast.makeText(CallingInterview.this, "Removed", Toast.LENGTH_SHORT).show();



                        return false;
                    }
                });




                alert.setTitle("Your Inter View List");

                alert.setView(linearLayout);

                alert.setPositiveButton("Ok",null);

                alert.show();

            }
        });


        FirebaseDatabase.getInstance().getReference().child("MyInterView").child(StaticVariables.uuid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                interViewClass = dataSnapshot.getValue(InterViewClass.class);

                arrayList.add(interViewClass);

                interViewClassList.notifyDataSetChanged();

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
