package com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakhtiyar.schoolreqruimentsystem.MainFragments.AdmissionPostingClass;
import com.example.bakhtiyar.schoolreqruimentsystem.R;
import com.example.bakhtiyar.schoolreqruimentsystem.StaticVariables;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Bakhtiyar on 2/21/2017.
 */
public class AdmissionListAdapter extends BaseAdapter {

    ArrayList<AdmissionPostingClass> arrayList;

    int temp;

    Context context;

    LayoutInflater inflater;

    CheckBox p,s,sssc,hssc, ssci,csci,preee,premm,ccom,aarts;

    int check;

    Button button;

    EditText to, from;
    TextView name;
    TextView end;

    public AdmissionListAdapter(ArrayList<AdmissionPostingClass> arrayList, Context context, int chk) {
        this.arrayList = arrayList;
        this.context = context;
        this.check=chk;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {

        temp =i;

        v = inflater.from(context).inflate(R.layout.fragment_manager_post, viewGroup, false);

        if (check==1){

            v.findViewById(R.id.apply).setVisibility(View.GONE);






        }

        else {

            v.findViewById(R.id.apply).setVisibility(View.VISIBLE);

            v.findViewById(R.id.apply).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {




                                FirebaseDatabase.getInstance().getReference().child("ApplyForAddmission").child(arrayList.get(temp).getUid()).child(StaticVariables.uuid).setValue(StaticVariables.studentInfo);

                                Toast.makeText(context, "Applied", Toast.LENGTH_SHORT).show();


                }
            });

        }


        to = (EditText) v.findViewById(R.id.to);

        from = (EditText) v.findViewById(R.id.from);

        name = (TextView) v.findViewById(R.id.name);

        name.setVisibility(View.VISIBLE);

        name.setText(arrayList.get(i).getName());

        end = (TextView) v.findViewById(R.id.end);

        end.setVisibility(View.VISIBLE);



        p= (CheckBox) v.findViewById(R.id.Primary);
        s = (CheckBox) v.findViewById(R.id.secondary);
        sssc = (CheckBox) v.findViewById(R.id.ssc);
        hssc = (CheckBox) v.findViewById(R.id.hsc);
        ssci = (CheckBox) v.findViewById(R.id.sci);
        csci = (CheckBox) v.findViewById(R.id.csci);
        preee = (CheckBox) v.findViewById(R.id.preeng);
        premm = (CheckBox) v.findViewById(R.id.prem);
        ccom = (CheckBox) v.findViewById(R.id.comm);
        aarts = (CheckBox) v.findViewById(R.id.arts);

        button = (Button) v.findViewById(R.id.submit);

        button.setVisibility(View.GONE);

        to.setText(arrayList.get(i).getTo());

        from.setText(arrayList.get(i).getFrom());

        if(arrayList.get(i).getPrimary().equals("Primary")){

            p.setChecked(true);

        }
        if(arrayList.get(i).getSecondary().equals("Secondary")){

            s.setChecked(true);

        }
        if(arrayList.get(i).getSsc().equals("SSC")){

            sssc.setChecked(true);

        }
        if(arrayList.get(i).getHsc().equals("HSC")){

            hssc.setChecked(true);

        }
        if(arrayList.get(i).getScience().equals("SCI")){

            ssci.setChecked(true);

        }
        if(arrayList.get(i).getCommerce().equals("Commerce")){

            ccom.setChecked(true);

        }
        if(arrayList.get(i).getComputer().equals("Computer Science")){

            csci.setChecked(true);

        }
        if(arrayList.get(i).getPre_engingeering().equals("Pre - Engineering")){

            preee.setChecked(true);

        }
        if(arrayList.get(i).getPre_Medical().equals("Pre - Medical")){

            premm.setChecked(true);

        }
        if(arrayList.get(i).getArts().equals("Arts")){

            aarts.setChecked(true);

        }


        return v;
    }
}
