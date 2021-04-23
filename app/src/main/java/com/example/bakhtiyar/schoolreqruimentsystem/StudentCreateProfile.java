package com.example.bakhtiyar.schoolreqruimentsystem;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.bakhtiyar.schoolreqruimentsystem.ForDate.Date;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class StudentCreateProfile extends AppCompatActivity {

    private static final int RC_PHOTO_PICKER =  2;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    Uri uri = null;
    EditText txt_name,txt_fname, txt_age, txt_qualification, txt_experience, txt_Skills, txt_Salary, txt_Email, txt_phone,txt_password;
    RadioButton male, female;
    String isMale = null;
    String name,fname, lastclass, dob, classs, phone, email,address,password;
    int age;
    FirebaseAuth firebaseAuth;
    AlertDialog.Builder alert;
    FirebaseStorage firebaseStorage;
    ImageButton imageView;
    Uri downloadUrl;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_student_create_profile);

        alert = new AlertDialog.Builder(StudentCreateProfile.this);
        firebaseStorage = FirebaseStorage.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(StudentCreateProfile.this);
        progressDialog.setMessage("Please Wait 2 to 3 Second...");
        txt_name = (EditText) findViewById(R.id.Name);
        txt_age = (EditText) findViewById(R.id.Age);
        txt_qualification = (EditText) findViewById(R.id.Qualification);
        txt_experience = (EditText) findViewById(R.id.dob);
        txt_Skills = (EditText) findViewById(R.id.classs);
        txt_Salary = (EditText) findViewById(R.id.address);
        txt_Email = (EditText) findViewById(R.id.Email);
        txt_phone = (EditText) findViewById(R.id.Phone);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        txt_password = (EditText) findViewById(R.id.password);
        txt_fname = (EditText) findViewById(R.id.fName);
        imageView = (ImageButton) findViewById(R.id.imgbtn);

        male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isMale = "male";
            }
        });
        
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = txt_name.getText().toString();
                fname = txt_fname.getText().toString();

                try {
                    age = Integer.parseInt(txt_age.getText().toString().trim());
                }
                catch (Exception e){
                    Toast.makeText(StudentCreateProfile.this, "Please type number", Toast.LENGTH_SHORT).show();
                    txt_age.setText("");
                }

                lastclass = txt_qualification.getText().toString().trim();
                dob = txt_experience.getText().toString();
                classs  = txt_Skills.getText().toString();
                phone = txt_phone.getText().toString();
                email = txt_Email.getText().toString();
                address = txt_Salary.getText().toString().trim();
                password = txt_password.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(StudentCreateProfile.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                    txt_name.setText("");
                    return;
                }

                if(TextUtils.isEmpty(fname)){
                    Toast.makeText(StudentCreateProfile.this, "Please Enter Guardian Name", Toast.LENGTH_SHORT).show();
                    txt_fname.setText("");
                    return;
                }

                if(TextUtils.isEmpty(lastclass)){
                    Toast.makeText(StudentCreateProfile.this, "Please Enter Your last class you attend or not", Toast.LENGTH_SHORT).show();
                    txt_qualification.setText("");
                    return;
                }

                if(TextUtils.isEmpty(dob)){
                    Toast.makeText(StudentCreateProfile.this, "Please Enter Your Date of birth", Toast.LENGTH_SHORT).show();
                    txt_experience.setText("");
                    return;
                }

                if(TextUtils.isEmpty(classs)){
                    Toast.makeText(StudentCreateProfile.this, "Please Enter Class do you wan't to addmission", Toast.LENGTH_SHORT).show();
                    txt_Skills.setText("");
                    return;
                }

                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(StudentCreateProfile.this, "Please Enter Your or your Gardian Phone number", Toast.LENGTH_SHORT).show();
                    txt_experience.setText("");
                    return;
                }


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(StudentCreateProfile.this, "Please Enter Email or type none", Toast.LENGTH_SHORT).show();
                    txt_Email.setText("");
                    return;
                }

                if (TextUtils.isEmpty(isMale)){
                    Toast.makeText(StudentCreateProfile.this, "Please Enter Your Gender", Toast.LENGTH_SHORT).show();
                    male.setChecked(false);
                    female.setChecked(false);
                    return;
                }

                if (TextUtils.isEmpty(address)){
                    Toast.makeText(StudentCreateProfile.this, "Please Enter your Address", Toast.LENGTH_SHORT).show();
                    txt_Salary.setText("");
                }

                if (uri == null ||uri.equals(null)){
                    Toast.makeText(StudentCreateProfile.this, "Please Upload Your Photo", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(StudentCreateProfile.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                    txt_name.setText("");
                    return;
                }


                progressDialog.show();

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(StudentCreateProfile.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            StaticVariables.uuid = firebaseAuth.getCurrentUser().getUid();
                            storageReference = firebaseStorage.getReference().child("StudentHiring").child(StaticVariables.uuid);
                            
                            storageReference.putFile(uri).addOnSuccessListener(StudentCreateProfile.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    downloadUrl = taskSnapshot.getDownloadUrl();
                                    StudentInfo studentInfo = new StudentInfo(StaticVariables.uuid,"",name,fname,lastclass,dob,classs,address,email,phone,isMale,downloadUrl.toString(),age);
                                    FirebaseDatabase.getInstance().getReference().child("StudentHiring").child(StaticVariables.uuid).child("Stud").setValue(studentInfo);

                                    txt_name.setText("");
                                    txt_fname.setText("");
                                    txt_age.setText("");
                                    txt_qualification.setText("");
                                    txt_experience.setText("");
                                    txt_Skills.setText("");
                                    txt_Salary.setText("");
                                    txt_Email.setText("");
                                    txt_phone.setText("");
                                    male.setChecked(false);
                                    female.setChecked(false);
                                    imageView.setImageResource(R.drawable.i);
                                    
                                    Toast.makeText(StudentCreateProfile.this, "Posted", Toast.LENGTH_SHORT).show();
                                    finish();
                                    progressDialog.hide();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(StudentCreateProfile.this, "No Submitted", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                        }
                        else {
                            Toast.makeText(StudentCreateProfile.this, "Not Success", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
            }
        });

        female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isMale = "Female";
            }
        });



        findViewById(R.id.imgbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        txt_experience.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    Date d = new Date(view);
                    android.app.FragmentTransaction ft = StudentCreateProfile.this.getFragmentManager().beginTransaction();
                    d.show(ft,"DatePicker");
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_PHOTO_PICKER && resultCode == Activity.RESULT_OK){
            uri = data.getData();
            imageView.setImageURI(uri);
        }
    }
}
