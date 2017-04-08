package com.example.bakhtiyar.schoolreqruimentsystem;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class Admin extends Fragment {

    View view;

    FirebaseAuth firebaseAuth;

    EditText txt_email,txt_password;

    String email,password;

    FirebaseAuth.AuthStateListener fiAuthStateListener;




    public Admin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_admin, container, false);


        firebaseAuth = FirebaseAuth.getInstance();


        txt_email = (EditText) view.findViewById(R.id.email);

        txt_password = (EditText) view.findViewById(R.id.password);

        view.findViewById(R.id.login1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = txt_email.getText().toString().trim();

                password = txt_password.getText().toString().trim();

                if(email.equals("admin@gmail.com")){

                    //     Toast.makeText(getContext(), "You ar not an admin", Toast.LENGTH_SHORT).show();




                    if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

                        txt_email.setText("");

                        txt_password.setText("");

                        Toast.makeText(getContext(), "Please Insert form", Toast.LENGTH_SHORT).show();

                        return;

                    } else {

                        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

//                                    StaticVariables.Uid = firebaseAuth.getCurrentUser().getUid();

                                    Toast.makeText(getContext(), "Successfull", Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(getActivity(), AdminPanel.class));

                                }
                            }
                        });
                    }
                }else {

                    Toast.makeText(getContext(), "You are not admin", Toast.LENGTH_SHORT).show();

                }
            }
        });


//        view.findViewById(R.id.create).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(),CreateUser.class));
//            }
//        });





        return view;
    }

}
