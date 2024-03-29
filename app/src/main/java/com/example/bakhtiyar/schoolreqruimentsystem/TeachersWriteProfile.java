package com.example.bakhtiyar.schoolreqruimentsystem;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class TeachersWriteProfile extends AppCompatActivity {

    StorageReference photoref;
    private static final int RC_PHOTO_PICKER = 2;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    Uri uri = null;
    EditText txt_name, txt_age, txt_qualification, txt_experience, txt_Skills, txt_Salary, txt_Email, txt_phone, txt_password;
    RadioButton male, female;
    Boolean isMale = null;
    String name, qualification, experience, skills, phone, email, password;
    int age;
    double salary;
    TeachersHire teachersHire;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    AlertDialog.Builder alert;
    FirebaseStorage firebaseStorage;
    ImageButton imageView;
    Uri downloadUrl;
    FirebaseAuth firebaseAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_teachers_write_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("TeachersHiring");
        firebaseStorage = FirebaseStorage.getInstance();

        alert = new AlertDialog.Builder(TeachersWriteProfile.this);
        progressDialog = new ProgressDialog(TeachersWriteProfile.this);
        progressDialog.setMessage("Please Wait 2 to 3 Second...");

        txt_name = (EditText) findViewById(R.id.Name);
        txt_age = (EditText) findViewById(R.id.Age);
        txt_qualification = (EditText) findViewById(R.id.Qualification);
        txt_experience = (EditText) findViewById(R.id.Experience);
        txt_Skills = (EditText) findViewById(R.id.TeachingSkills);
        txt_Salary = (EditText) findViewById(R.id.Salary);
        txt_Email = (EditText) findViewById(R.id.Email);
        txt_phone = (EditText) findViewById(R.id.Phone);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        txt_password = (EditText) findViewById(R.id.password);
        imageView = (ImageButton) findViewById(R.id.imgbtn);

        male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isMale = true;
            }
        });

        female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isMale = false;
            }
        });

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    progressDialog.show();
                    name = txt_name.getText().toString().trim();
                    age = Integer.parseInt(txt_age.getText().toString().trim());
                    qualification = txt_qualification.getText().toString().trim();
                    experience = txt_experience.getText().toString().trim();
                    skills = txt_Skills.getText().toString().trim();
                    phone = txt_phone.getText().toString().trim();
                    email = txt_Email.getText().toString().trim();
                    salary = Double.parseDouble(txt_Salary.getText().toString().trim());
                    password = txt_password.getText().toString().trim();

                    if (TextUtils.isEmpty(name) ||
                            TextUtils.isEmpty(qualification) ||
                            TextUtils.isEmpty(experience) ||
                            TextUtils.isEmpty(skills) ||
                            TextUtils.isEmpty(phone) ||
                            TextUtils.isEmpty(email) ||
                            isMale == null
                            || TextUtils.isEmpty(password))

                    {
                        Toast.makeText(TeachersWriteProfile.this, "Please Complete the form", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        submit();
                    }
                } catch (Exception e) {
                    Log.d("Exception", "onClick: " + e);
                }
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_PHOTO_PICKER && resultCode == Activity.RESULT_OK) {
            uri = data.getData();
            imageView.setImageURI(uri);
        }
    }


    public void submit() {
        if (uri == null) {
            Toast.makeText(TeachersWriteProfile.this, "Please Select your Picture", Toast.LENGTH_SHORT).show();
        } else {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(TeachersWriteProfile.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        StaticVariables.uuid = firebaseAuth.getCurrentUser().getUid();
                        storageReference = firebaseStorage.getReference().child("TeachersHiring").child(StaticVariables.uuid);
                        photoref = storageReference.child(uri.getLastPathSegment());
                        photoref.putFile(uri).addOnSuccessListener(TeachersWriteProfile.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                downloadUrl = taskSnapshot.getDownloadUrl();
                                teachersHire = new TeachersHire(isMale, name, qualification, experience, skills, phone, email, downloadUrl.toString(), databaseReference.push().getKey(), age, salary, StaticVariables.uuid);
                                databaseReference.child(StaticVariables.uuid).child("Teach").setValue(teachersHire);
                                FirebaseDatabase.getInstance().getReference().child("PublicTeachers").child(teachersHire.getPushKey()).setValue(teachersHire);
                                txt_name.setText("");
                                txt_age.setText("");
                                txt_experience.setText("");
                                txt_Email.setText("");
                                txt_qualification.setText("");
                                txt_phone.setText("");
                                txt_Salary.setText("");
                                txt_Skills.setText("");
                                txt_password.setText("");

                                imageView.setImageResource(R.drawable.i);
                                Toast.makeText(TeachersWriteProfile.this, "Submitted", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                TeachersWriteProfile.this.onBackPressed();
                            }
                        });
                    } else {
                        progressDialog.hide();
                        Toast.makeText(TeachersWriteProfile.this, "Already Created", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
