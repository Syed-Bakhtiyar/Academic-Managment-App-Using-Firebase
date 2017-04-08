package com.example.bakhtiyar.schoolreqruimentsystem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class ViewProfile extends Fragment {


    View view;

    int a =2;

    TextView name, qual, exp,age,skill,gender,txt_fname,phone,email;

    ImageView imageView;


    ArrayList<TeachersHire> arrayList;

    TeachersProfileListAdapter teachersProfileListAdapter;

    StudentInfo teachersHire;



    CardView cardView;


    public ViewProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_view_profile, container, false);

        txt_fname = (TextView) view.findViewById(R.id.fname);

        phone = (TextView) view.findViewById(R.id.phone);
        email = (TextView) view.findViewById(R.id.email);



        name = (TextView) view.findViewById(R.id.name);
        qual = (TextView) view.findViewById(R.id.qualification);
        exp = (TextView) view.findViewById(R.id.dob);
        age = (TextView) view.findViewById(R.id.age);
        skill = (TextView) view.findViewById(R.id.classs);
        gender = (TextView) view.findViewById(R.id.gender);
        imageView = (ImageView) view.findViewById(R.id.image);
        cardView = (CardView) view.findViewById(R.id.cv);


        read();



//

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(a%2==0){

                    txt_fname.setVisibility(View.GONE);
                    email.setVisibility(View.GONE);
                    phone.setVisibility(View.GONE);
                    name.setVisibility(View.GONE);
                    qual.setVisibility(View.GONE);
                    exp.setVisibility(View.GONE);
                    age.setVisibility(View.GONE);
                    skill.setVisibility(View.GONE);
                    gender.setVisibility(View.GONE);


                }else {

                    txt_fname.setVisibility(View.VISIBLE);
                    email.setVisibility(View.VISIBLE);
                    phone.setVisibility(View.VISIBLE);

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

        FirebaseDatabase.getInstance().getReference().child("StudentHiring").child(StaticVariables.uuid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                teachersHire = dataSnapshot.getValue(StudentInfo.class);

                StaticVariables.studentInfo = teachersHire;

                StaticVariables.mName = teachersHire.getName();

                try{


                    Glide.with(imageView.getContext())
                            .load(teachersHire.getUrl())
                            .into(imageView);


                }catch (Exception e){}





                name.setText("Name: "+teachersHire.getName());
                txt_fname.setText("Gardian: "+teachersHire.getFname());
                qual.setText("Last Class: "+teachersHire.getQual());
                exp.setText("Date of Birth: "+teachersHire.getDateOfBirth());
                age.setText("Age: "+teachersHire.getAge()+"");
                skill.setText("Class Want: "+teachersHire.getClasss());
                gender.setText("Gender: "+teachersHire.getIsmale()+"");
                phone.setText("Phone: "+teachersHire.getPhone());
                email.setText("Email: "+teachersHire.getEmail());

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
