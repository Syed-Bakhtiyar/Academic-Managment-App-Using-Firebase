package com.example.bakhtiyar.schoolreqruimentsystem;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters.TeachersProfileListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeachersProfile extends Fragment {

    View view;

    int a =2;

    TextView name, qual, exp,age,skill,gender;

    ImageView imageView;


    ArrayList<TeachersHire> arrayList;

    TeachersProfileListAdapter teachersProfileListAdapter;

    TeachersHire teachersHire;



    CardView cardView;

    public TeachersProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_teachers_profile, container, false);

        name = (TextView) view.findViewById(R.id.name);
        qual = (TextView) view.findViewById(R.id.qualification);
        exp = (TextView) view.findViewById(R.id.experience);
        age = (TextView) view.findViewById(R.id.age);
        skill = (TextView) view.findViewById(R.id.TeachingSkills);
        gender = (TextView) view.findViewById(R.id.gender);
        imageView = (ImageView) view.findViewById(R.id.image);
        cardView = (CardView) view.findViewById(R.id.cv);


        read();



//

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             if(a%2==0){


                 name.setVisibility(View.GONE);
                 qual.setVisibility(View.GONE);
                 exp.setVisibility(View.GONE);
                 age.setVisibility(View.GONE);
                 skill.setVisibility(View.GONE);
                 gender.setVisibility(View.GONE);


             }else {

                 name.setVisibility(View.VISIBLE);
                 qual.setVisibility(View.VISIBLE);
                 exp.setVisibility(View.VISIBLE);
                 age.setVisibility(View.VISIBLE);
                 skill.setVisibility(View.VISIBLE);
                 gender.setVisibility(View.VISIBLE);






             }

                a++;

            }
        });





        return view;
    }

    public void read(){

        FirebaseDatabase.getInstance().getReference().child("TeachersHiring").child(StaticVariables.uuid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                teachersHire = dataSnapshot.getValue(TeachersHire.class);

                Log.d("data", "onChildAdded: "+teachersHire.getName());

                StaticVariables.isteacher = true;

                StaticVariables.teachersHire = teachersHire;

                StaticVariables.mName = teachersHire.getName();

                try {

                    Glide.with(imageView.getContext())
                            .load(teachersHire.getImglink())
                            .into(imageView);


                }
                catch (Exception e){


                }




                name.setText(teachersHire.getName());
                qual.setText(teachersHire.getQualification());
                exp.setText(teachersHire.getExperience());
                age.setText(teachersHire.getAge()+"");
                skill.setText(teachersHire.getSkills());

//                if(teachersHire.getMale()){
//
//                    gender.setText("Male");
//
//                }
//                else {
//
//                    gender.setText("Female");
//                }


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
