package com.example.bakhtiyar.schoolreqruimentsystem;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentCreateProfile extends Fragment {


    int test=1;

    StorageReference photoref;

    private static final int RC_PHOTO_PICKER =  2;

    StorageReference storageReference;

    ProgressDialog progressDialog;

    Uri uri = null;

    private int REQUEST_IMAGE_CAPTURE = 1;

    byte[] image;

    EditText txt_name,txt_fname, txt_age, txt_qualification, txt_experience, txt_Skills, txt_Salary, txt_Email, txt_phone,txt_password;

    RadioButton male, female;

    String isMale = null;

    String name,fname, lastclass, dob, classs, phone, email,address,password;

    int age;

    double salary;

    TeachersHire teachersHire;

    FirebaseDatabase firebaseDatabase;

    FirebaseAuth firebaseAuth;

    DatabaseReference databaseReference;

    AlertDialog.Builder alert;

    FirebaseStorage firebaseStorage;

    String asdasd;

    ImageButton imageView;


    Uri downloadUrl;




    View v;


    public StudentCreateProfile() {
        // Required empty public constructor
    }

//FirebaseDatabase.getInstance().getReference().child("StudentHiring").child(StaticVariables.uuid).child("Stud");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

     //   check();
        v = inflater.inflate(R.layout.fragment_student_create_profile, container, false);



        alert = new AlertDialog.Builder(getContext());

        firebaseStorage = FirebaseStorage.getInstance();


        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(getContext());

        progressDialog.setMessage("Please Wait 2 to 3 Second...");

        txt_name = (EditText) v.findViewById(R.id.Name);
        txt_age = (EditText) v.findViewById(R.id.Age);
        txt_qualification = (EditText) v.findViewById(R.id.Qualification);
        txt_experience = (EditText) v.findViewById(R.id.dob);
        txt_Skills = (EditText) v.findViewById(R.id.classs);
        txt_Salary = (EditText) v.findViewById(R.id.address);
        txt_Email = (EditText) v.findViewById(R.id.Email);
        txt_phone = (EditText) v.findViewById(R.id.Phone);
        male = (RadioButton) v.findViewById(R.id.male);
        female = (RadioButton) v.findViewById(R.id.female);
        txt_password = (EditText) v.findViewById(R.id.password);
        txt_fname = (EditText) v.findViewById(R.id.fName);

        imageView = (ImageButton) v.findViewById(R.id.imgbtn);

        male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                isMale = "male";

            }
        });


        v.findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = txt_name.getText().toString();
                fname = txt_fname.getText().toString();

                try {
                    age = Integer.parseInt(txt_age.getText().toString().trim());
                }
                catch (Exception e){

                    Toast.makeText(getContext(), "Please type number", Toast.LENGTH_SHORT).show();
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

                    Toast.makeText(getContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();

                    txt_name.setText("");

                    return;

                }

                if(TextUtils.isEmpty(fname)){

                    Toast.makeText(getContext(), "Please Enter Guardian Name", Toast.LENGTH_SHORT).show();

                    txt_fname.setText("");

                    return;
                }
                if(TextUtils.isEmpty(lastclass)){

                    Toast.makeText(getContext(), "Please Enter Your last class you attend or not", Toast.LENGTH_SHORT).show();

                    txt_qualification.setText("");

                    return;
                }

                if(TextUtils.isEmpty(dob)){

                    Toast.makeText(getContext(), "Please Enter Your Date of birth", Toast.LENGTH_SHORT).show();

                    txt_experience.setText("");

                    return;
                }

                if(TextUtils.isEmpty(classs)){

                    Toast.makeText(getContext(), "Please Enter Class do you wan't to addmission", Toast.LENGTH_SHORT).show();

                    txt_Skills.setText("");

                    return;
                }

                if(TextUtils.isEmpty(phone)){

                    Toast.makeText(getContext(), "Please Enter Your or your Gardian Phone number", Toast.LENGTH_SHORT).show();

                    txt_experience.setText("");

                    return;
                }


                if(TextUtils.isEmpty(email)){

                    Toast.makeText(getContext(), "Please Enter Email or type none", Toast.LENGTH_SHORT).show();

                    txt_Email.setText("");

                    return;
                }

                if (TextUtils.isEmpty(isMale)){

                    Toast.makeText(getContext(), "Please Enter Your Gender", Toast.LENGTH_SHORT).show();

                    male.setChecked(false);

                    female.setChecked(false);

                    return;
                }

                if (TextUtils.isEmpty(address)){

                    Toast.makeText(getContext(), "Please Enter your Address", Toast.LENGTH_SHORT).show();

                    txt_Salary.setText("");

                }

                if (uri == null ||uri.equals(null)){

                    Toast.makeText(getContext(), "Please Upload Your Photo", Toast.LENGTH_SHORT).show();

                    return;
                }

                if(TextUtils.isEmpty(password)){

                    Toast.makeText(getContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();

                    txt_name.setText("");

                    return;

                }


                progressDialog.show();

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            /////////////////////////////

                            StaticVariables.uuid = firebaseAuth.getCurrentUser().getUid();

                            storageReference = firebaseStorage.getReference().child("StudentHiring").child(StaticVariables.uuid);


                            storageReference.putFile(uri).addOnSuccessListener(getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
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

                                    Toast.makeText(getContext(), "Posted", Toast.LENGTH_SHORT).show();

                                    getActivity().finish();

//                                    getActivity().onBackPressed();

                                    progressDialog.hide();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(getContext(), "No Submitted", Toast.LENGTH_SHORT).show();

                                    getActivity().finish();

                                }
                            });











                            //////////////////////////////

                        }
                        else {

                            Toast.makeText(getContext(), "Not Success", Toast.LENGTH_SHORT).show();

//                            getActivity().onBackPressed();

                            getActivity().finish();

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



        v.findViewById(R.id.imgbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);




            }
        });





        return v;
    }


    @Override
    public void onStart() {
        super.onStart();

        txt_experience.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){

                    Date d = new Date(view);

                    android.app.FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();

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



//    public void check(){
//
//        FirebaseDatabase.getInstance().getReference().child("StudentHiring").child(StaticVariables.uuid).addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//                StudentInfo studentInfo = dataSnapshot.getValue(StudentInfo.class);
//
//                if( !studentInfo.getName().equals("") || !studentInfo.getName().equals(null)){
//
//                    StaticVariables.studentInfo = studentInfo;
//
//                    test = 0;
//
//                }
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
//    }
//


}
