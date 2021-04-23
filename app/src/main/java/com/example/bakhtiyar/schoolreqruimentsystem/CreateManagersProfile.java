package com.example.bakhtiyar.schoolreqruimentsystem;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateManagersProfile extends AppCompatActivity {

    EditText txt_managname, txt_campusname,txt_address,txt_phone,txt_email,txt_password;

    String m_name,c_name, address, phone, email,password;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;

    ManagerInfo managerInfo;
    private FirebaseAuth firebaseAuthGlobal;

    ProgressDialog dialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_managers_profile);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Wait");
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseAuthGlobal = FirebaseAuth.getInstance();
        txt_managname = (EditText) findViewById(R.id.mname);
        txt_campusname = (EditText) findViewById(R.id.cname);
        txt_address = (EditText) findViewById(R.id.address);
        txt_phone = (EditText) findViewById(R.id.phone);
        txt_email = (EditText) findViewById(R.id.email);
        txt_password = (EditText) findViewById(R.id.password);

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                m_name = txt_managname.getText().toString().trim();
                c_name = txt_campusname.getText().toString().trim();
                address = txt_address.getText().toString().trim();
                phone = txt_phone.getText().toString().trim();
                email = txt_email.getText().toString().trim();
                password = txt_password.getText().toString().trim();

                if(TextUtils.isEmpty(m_name) ||
                        TextUtils.isEmpty(c_name) ||
                        TextUtils.isEmpty(address) ||
                        TextUtils.isEmpty(phone) ||
                        TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(CreateManagersProfile.this, "Please complete the form", Toast.LENGTH_SHORT).show();
                    txt_managname.setText("");
                    txt_campusname.setText("");
                    txt_address.setText("");
                    txt_phone.setText("");
                    txt_email.setText("");
                    txt_password.setText("");
                    return;
                }else {
                    dialog.show();
                    signup();
                }
            }
        });
    }

    public void signup() {
            validation(email,password);
    }

    public void validation( final String temp_email, String temp_password){

        firebaseAuthGlobal.createUserWithEmailAndPassword(temp_email,temp_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    FirebaseUser user = firebaseAuthGlobal.getCurrentUser();

                    StaticVariables.uuid = FirebaseAuth.getInstance().getCurrentUser().getUid();


                    managerInfo = new ManagerInfo(c_name,m_name,address,phone,email,StaticVariables.uuid);


                    databaseReference.child("Manager").child(managerInfo.getUid()).child("Managed").setValue(managerInfo);

                    FirebaseDatabase.getInstance().getReference().child("PublicManager").child(managerInfo.getUid()).setValue(managerInfo);

                    Toast.makeText(CreateManagersProfile.this, "Your A/c is created", Toast.LENGTH_SHORT).show();

                    dialog.hide();

                    finish();






                }
                else {

                    Toast.makeText(CreateManagersProfile.this, "Not Successful", Toast.LENGTH_SHORT).show();

                }


            }
        });

    }



}
