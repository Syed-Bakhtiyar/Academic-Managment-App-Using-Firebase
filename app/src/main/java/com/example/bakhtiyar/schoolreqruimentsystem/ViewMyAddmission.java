package com.example.bakhtiyar.schoolreqruimentsystem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters.AdmissionListAdapter;
import com.example.bakhtiyar.schoolreqruimentsystem.MainFragments.AdmissionPostingClass;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewMyAddmission extends Fragment {


    View v;

    ListView listView;

    ArrayList<AdmissionPostingClass> arrayList;

    AdmissionPostingClass admissionPostingClass;

    AdmissionListAdapter admissionListAdapter;

    public ViewMyAddmission() {
        // Required empty public constructor
    }


    //FirebaseDatabase.getInstance().getReference().child("MyAddmission").child(admissionPostingClass.getUid()).child(admissionPostingClass.getPush())

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_view_my_addmission, container, false);

        arrayList = new ArrayList<>();

        listView = (ListView) v.findViewById(R.id.list);

        admissionListAdapter = new AdmissionListAdapter(arrayList,getContext(),1);

        listView.setAdapter(admissionListAdapter);


FirebaseDatabase.getInstance().getReference().child("MyAddmission").child(StaticVariables.uuid).addChildEventListener(new ChildEventListener() {
    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

        admissionPostingClass = dataSnapshot.getValue(AdmissionPostingClass.class);

        arrayList.add(admissionPostingClass);

        admissionListAdapter.notifyDataSetChanged();

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
