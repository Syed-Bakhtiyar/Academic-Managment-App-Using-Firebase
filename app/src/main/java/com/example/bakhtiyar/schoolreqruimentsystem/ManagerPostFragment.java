package com.example.bakhtiyar.schoolreqruimentsystem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakhtiyar.schoolreqruimentsystem.ForDate.Date;
import com.example.bakhtiyar.schoolreqruimentsystem.MainFragments.AdmissionPostingClass;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class ManagerPostFragment extends Fragment {

    View v;

    EditText to,from;

    CheckBox p,s,sssc,hssc, ssci,csci,preee,premm,ccom,aarts;


    AdmissionPostingClass admissionPostingClass;



    String chk_pri = ""
            ,
            chk_sec = "",
            chk_ssc="",
            chk_hsc="";

    String sci ="", compsci="", preeng="",premed="",commerce="",arts="";

    String date_to="", date_from="";

    boolean pr=false,se=false,ss=false,hs=false,sc=false,csc=false,pe=false,pm=false,com=false,ar=false;

    TextView end;

    TextView name;

    public ManagerPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_manager_post, container, false);

        v.findViewById(R.id.apply).setVisibility(View.GONE);


        name = (TextView) v.findViewById(R.id.name);

        name.setVisibility(View.GONE);

        to = (EditText) v.findViewById(R.id.to);

        end = (TextView) v.findViewById(R.id.end);

        end.setVisibility(View.GONE);

        from = (EditText) v.findViewById(R.id.from);

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

        aarts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ar = true;

                arts = "Arts";
            }
        });

        premm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                pm = true;
                premed = "Pre - Medical";

            }
        });


        preee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                pe = true;

                preeng = "Pre - Engineering";
            }
        });

        csci.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                csc = true;
                compsci = "Computer Science";

            }
        });


        ccom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                com=true;
                commerce = "Commerce";
            }
        });

        ssci.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sc=true;
                sci = "SCI";

            }
        });

        hssc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                hs=false;

                chk_hsc = "HSC";

            }
        });

        sssc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sc= true;

                chk_ssc = "SSC";
            }
        });


        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sc = true;
                chk_sec = "Secondary";
            }
        });

        p.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                chk_pri = "Primary";
                pr = true;



            }
        });


        v.findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                date_to = to.getText().toString();

                date_from =  from.getText().toString();


                if(TextUtils.isEmpty(date_from) || TextUtils.isEmpty(date_to)){

                    Toast.makeText(getContext(), "Please Select Date", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(pr==false&&se==false&&ss==false&&hs==false&&sc==false&&csc==false&&pe==false&&pm==false&&com==false&&ar==false){

                    Toast.makeText(getContext(), "Please complete form", Toast.LENGTH_SHORT).show();
                    return;

                }

                admissionPostingClass = new AdmissionPostingClass(StaticVariables.managerInfo.getCampusname(),chk_pri,chk_sec,chk_ssc,chk_hsc,sci,compsci,preeng,premed,commerce,arts,StaticVariables.uuid,FirebaseDatabase.getInstance().getReference().child("MyAddmission").child(StaticVariables.uuid).push().getKey(),date_to,date_from);

                FirebaseDatabase.getInstance().getReference().child("MyAddmission").child(admissionPostingClass.getUid()).child(admissionPostingClass.getPush()).setValue(admissionPostingClass);

//                admissionPostingClass.setPush(FirebaseDatabase.getInstance().getReference().child("PublicMyAddmission").push().getKey());

                FirebaseDatabase.getInstance().getReference().child("PublicMyAddmission").child(admissionPostingClass.getPush()).setValue(admissionPostingClass);

                Toast.makeText(getContext(), "Posted", Toast.LENGTH_SHORT).show();

                p.setChecked(false);
                s.setChecked(false);
                sssc.setChecked(false);
                hssc.setChecked(false);
                ssci.setChecked(false);
                csci.setChecked(false);
                preee.setChecked(false);
                premm.setChecked(false);
                ccom.setChecked(false);
                aarts.setChecked(false);

                to.setText("");

                from.setText("");




            }
        });

        return v;
    }


    @Override
    public void onStart() {
        super.onStart();

        //to  = (EditText) v.findViewById(R.id.calender);


        to.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){

                    Date d = new Date(view);

                    android.app.FragmentTransaction ft =getActivity().getFragmentManager().beginTransaction();

                    d.show(ft,"DatePicker");


                }
            }
        });

        from.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){

                    Date d = new Date(view);

                    android.app.FragmentTransaction ft =getActivity().getFragmentManager().beginTransaction();

                    d.show(ft,"DatePicker");


                }
            }
        });



    }
}
