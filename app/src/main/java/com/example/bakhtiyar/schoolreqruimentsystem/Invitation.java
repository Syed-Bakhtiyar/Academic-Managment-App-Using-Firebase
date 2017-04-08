package com.example.bakhtiyar.schoolreqruimentsystem;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class Invitation extends Fragment {

    ImageButton imageView;

    ApprovalClass approvalClass;

    Intent i;

    Button btn;

    TextView textView;

    View v;

    public Invitation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_invitation, container, false);


        imageView = (ImageButton) v.findViewById(R.id.image);

        imageView.setVisibility(View.GONE);

//        btn = (Button) v.findViewById(R.id.view);

//        textView = (TextView) v.findViewById(R.id.name);

//        btn.setVisibility(View.GONE);


        i = new Intent(getActivity(),TeacherProfile.class);

        i.putExtra("key",1);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(i);

            }
        });




        FirebaseDatabase.getInstance().getReference().child("Approval").child(StaticVariables.uuid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                approvalClass = dataSnapshot.getValue(ApprovalClass.class);

                if(approvalClass.getCname().equals("")||approvalClass.getCname().equals(null)){

                    imageView.setVisibility(View.GONE);

//                    btn.setVisibility(View.GONE);

  //                  textView.setVisibility(View.GONE);

                }
                else {


                    imageView.setVisibility(View.VISIBLE);

                    try {


                        Glide.with(imageView.getContext())
                                .load(StaticVariables.teachersHire.getImglink())
                                .into(imageView);

                    }
                    catch (Exception e){


                        Log.d("error", "onChildAdded: "+e);

                    }



//                    btn.setVisibility(View.VISIBLE);

//                    textView.setVisibility(View.VISIBLE);

  //                  textView.setText(approvalClass.getCname());

                    StaticVariables.manuid = approvalClass.getMuid();
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


        return v;
    }

}
