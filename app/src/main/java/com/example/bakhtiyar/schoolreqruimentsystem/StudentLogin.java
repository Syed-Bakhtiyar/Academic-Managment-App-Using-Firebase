package com.example.bakhtiyar.schoolreqruimentsystem;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentLogin extends Fragment {

    FirebaseAuth firebaseAuthGlobal;

    FirebaseAuth.AuthStateListener fiAuthStateListener;

    String email, password;

    FirebaseUser firebaseUser;

    EditText txt_email, txt_password;

    Button login;

    ProgressDialog progressDialog;

    TextView textView;



    View v;



    public StudentLogin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       v = inflater.inflate(R.layout.fragment_student_login, container, false);


        firebaseAuthGlobal = FirebaseAuth.getInstance();



//        StaticVariables.uuid =  firebaseAuthGlobal.getCurrentUser().getUid();


        login = (Button) v.findViewById(R.id.btn_login);

        textView = (TextView) v.findViewById(R.id.link_signup);

        txt_email = (EditText) v.findViewById(R.id.input_email);

        txt_password = (EditText) v.findViewById(R.id.input_password);




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                login() ;


            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StudentCreateProfile someFragment = new StudentCreateProfile();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack("ok");  // if written, this transaction will be added to backstack
                transaction.commit();




              startActivity(new Intent(getActivity(),ForMyProfile.class));

            }
        });







        return v;
    }



    public void login(){


        email = txt_email.getText().toString().trim();

        password = txt_password.getText().toString().trim();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){

            Toast.makeText(getContext(), "Please insert email or password", Toast.LENGTH_SHORT).show();

            return;
        }else {

            validation();

        }

    }

    public void validation(){

        progressDialog = new ProgressDialog(getContext());

        progressDialog.setMessage("Wait");

        progressDialog.show();


        firebaseAuthGlobal.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    progressDialog.dismiss();

                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();



                    StaticVariables.uuid =  firebaseAuthGlobal.getCurrentUser().getUid();

                    startActivity(new Intent(getContext(), StudentPanel.class));


                }else {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Not Success", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }



}
