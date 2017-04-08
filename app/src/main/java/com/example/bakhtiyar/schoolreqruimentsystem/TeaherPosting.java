package com.example.bakhtiyar.schoolreqruimentsystem;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
//import org.apache.commons.io.FilenameUtils;

import com.example.bakhtiyar.schoolreqruimentsystem.MainFragments.TeachersPostClass;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class TeaherPosting extends AppCompatActivity {

    Bundle bundle;

    private static final int RC_PHOTO_PICKER = 2;

    StorageReference storage;

    StorageReference groupStorage;

    String extension;

    String post = null;

    String keyy;

    Uri uri = null;

    EditText editText;

    ImageButton submit;

    ImageButton att;

    int chk;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teaher_posting);

        bundle = getIntent().getExtras();

        chk = bundle.getInt("data");

        editText = (EditText) findViewById(R.id.writepost);

        att = (ImageButton) findViewById(R.id.attachment);

        submit = (ImageButton) findViewById(R.id.post);

        progressDialog = new ProgressDialog(this);

//        progressDialog.setMessage("Uploading");

        storage = FirebaseStorage.getInstance().getReference().child("TeacherPosts").child(StaticVariables.manuid);

        groupStorage = FirebaseStorage.getInstance().getReference().child("Group").child(StaticVariables.manuid);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                post = editText.getText().toString().trim();

                //
                if (TextUtils.isEmpty(post) && uri == null) {

                    Toast.makeText(getApplicationContext(), "Please complete your post", Toast.LENGTH_SHORT).show();

                    return;

                } else if (chk == 1) {


                    if (!post.equals(null) && uri == null) {


                        TeachersPostClass teachersPostClass1 = new TeachersPostClass(StaticVariables.mName, StaticVariables.uuid, StaticVariables.uuid, FirebaseDatabase.getInstance().getReference().child("TeacherPost").child(StaticVariables.manuid).child(StaticVariables.uuid).push().getKey(), null, post, "");

                        FirebaseDatabase.getInstance().getReference().child("TeacherPost").child(StaticVariables.manuid).child(StaticVariables.uuid).child(teachersPostClass1.getPush()).setValue(teachersPostClass1);

                        FirebaseDatabase.getInstance().getReference().child("PTeacherPost").child(StaticVariables.manuid).child(teachersPostClass1.getPush()).setValue(teachersPostClass1);

                        Toast.makeText(getApplicationContext(), "Posted", Toast.LENGTH_SHORT).show();
//

                        editText.setText("");


                    } else if (post.equals(null) && !(uri == null)) {


                        keyy = FirebaseDatabase.getInstance().getReference().child("TeacherPost").child(StaticVariables.manuid).child(StaticVariables.uuid).push().getKey();
                        final File f = new File(String.valueOf(uri));

                        progressDialog.show();

                        storage.child(keyy).child(f.getName()).putFile(uri).addOnSuccessListener(TeaherPosting.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

//                                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                                progressDialog.setTitle("Upload is " + progress + "% done");
//                                int currentprogress = (int) progress;


                                Log.d("ffname", "onSuccess: " + f.getName() + "uri" + uri);

                                TeachersPostClass teachersPostClass1 = new TeachersPostClass(StaticVariables.mName, StaticVariables.uuid, StaticVariables.uuid, keyy, taskSnapshot.getDownloadUrl().toString(), "", "");

                                Log.d("ext", "onSuccess: " + MimeTypeMap.getFileExtensionFromUrl(uri.toString()));

                                FirebaseDatabase.getInstance().getReference().child("TeacherPost").child(StaticVariables.manuid).child(StaticVariables.uuid).child(teachersPostClass1.getPush()).setValue(teachersPostClass1);

                                FirebaseDatabase.getInstance().getReference().child("PTeacherPost").child(StaticVariables.manuid).child(teachersPostClass1.getPush()).setValue(teachersPostClass1);

                                Toast.makeText(getApplicationContext(), "Posted", Toast.LENGTH_SHORT).show();

                                editText.setText("");

                                post = null;

                                uri = null;

                                progressDialog.dismiss();

                            }
                        }).addOnFailureListener(TeaherPosting.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {


                                Log.d("fail", "onFailure: " + e);


                                FirebaseDatabase.getInstance().getReference().child("TeacherPost").child(StaticVariables.manuid).child(StaticVariables.uuid).child(keyy).removeValue();


                            }
                        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                                progressDialog.setMessage("Upload is " +((int) progress) + "% done");

                            }
                        });;


//

                    } else if (!post.equals(null) && !(uri == null)) {

                        keyy = FirebaseDatabase.getInstance().getReference().child("TeacherPost").child(StaticVariables.manuid).child(StaticVariables.uuid).push().getKey();

                        final File f = new File(String.valueOf(uri));

                        progressDialog.show();


                        storage.child(keyy).child(f.getName()).putFile(uri).addOnSuccessListener(TeaherPosting.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


//                                int currentprogress = (int) progress;


                                Log.d("ffname", "onSuccess: " + f.getPath() + "uri" + uri);

                                TeachersPostClass teachersPostClass1 = new TeachersPostClass(StaticVariables.mName, StaticVariables.uuid, StaticVariables.uuid, keyy, taskSnapshot.getDownloadUrl().toString(), post, "");

                                Log.d("ext", "onSuccess: " + MimeTypeMap.getFileExtensionFromUrl(uri.toString()));

                                FirebaseDatabase.getInstance().getReference().child("TeacherPost").child(StaticVariables.manuid).child(StaticVariables.uuid).child(teachersPostClass1.getPush()).setValue(teachersPostClass1);

                                FirebaseDatabase.getInstance().getReference().child("PTeacherPost").child(StaticVariables.manuid).child(teachersPostClass1.getPush()).setValue(teachersPostClass1);

                                Toast.makeText(getApplicationContext(), "Posted", Toast.LENGTH_SHORT).show();

                                progressDialog.dismiss();

                                editText.setText("");

//                            Toast.makeText(TeaherPosting.this, "Posted", Toast.LENGTH_SHORT).show();


                            }
                        }).addOnFailureListener(TeaherPosting.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {


                                Log.d("fail", "onFailure: " + e);


                                FirebaseDatabase.getInstance().getReference().child("TeacherPost").child(StaticVariables.manuid).child(StaticVariables.uuid).child(keyy).removeValue();


                            }
                        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                                progressDialog.setMessage("Upload is " +((int) progress) + "% done");

                            }
                        });


                    }


                }
                else if(chk == 2){

                    if (!post.equals(null) && uri == null) {


                        TeachersPostClass teachersPostClass1 = new TeachersPostClass(StaticVariables.mName, StaticVariables.uuid, StaticVariables.uuid, FirebaseDatabase.getInstance().getReference().child("GroupPost").child(StaticVariables.manuid).child(StaticVariables.groupClass.getPush()).push().getKey(), null, post, "");

                        FirebaseDatabase.getInstance().getReference().child("GroupPost").child(StaticVariables.manuid).child(StaticVariables.groupClass.getPush()).child(teachersPostClass1.getPush()).setValue(teachersPostClass1);

//                        FirebaseDatabase.getInstance().getReference().child("PTeacherPost").child(StaticVariables.manuid).child(teachersPostClass1.getPush()).setValue(teachersPostClass1);

                        Toast.makeText(getApplicationContext(), "Posted", Toast.LENGTH_SHORT).show();

                        editText.setText("");
//

                    } else if (post.equals(null) && !(uri == null)) {

                        progressDialog.show();


                        keyy = FirebaseDatabase.getInstance().getReference().child("GroupPost").child(StaticVariables.manuid).child(StaticVariables.groupClass.getPush()).push().getKey();
                        final File f = new File(String.valueOf(uri));


                        groupStorage.child(keyy).child(f.getName()).putFile(uri).addOnSuccessListener(TeaherPosting.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

//                                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                                progressDialog.setMessage("Upload is " + progress + "% done");
//                                int currentprogress = (int) progress;


                                Log.d("ffname", "onSuccess: " + f.getName() + "uri" + uri);

                                TeachersPostClass teachersPostClass1 = new TeachersPostClass(StaticVariables.mName, StaticVariables.uuid, StaticVariables.uuid, keyy, taskSnapshot.getDownloadUrl().toString(), "", "");

                                Log.d("ext", "onSuccess: " + MimeTypeMap.getFileExtensionFromUrl(uri.toString()));

                                FirebaseDatabase.getInstance().getReference().child("GroupPost").child(StaticVariables.manuid).child(StaticVariables.groupClass.getPush()).child(teachersPostClass1.getPush()).setValue(teachersPostClass1);

             //                   FirebaseDatabase.getInstance().getReference().child("PTeacherPost").child(StaticVariables.manuid).child(teachersPostClass1.getPush()).setValue(teachersPostClass1);

                                Toast.makeText(getApplicationContext(), "Posted", Toast.LENGTH_SHORT).show();

                                progressDialog.dismiss();

                                editText.setText("");

                                post = null;

                                uri = null;


                            }
                        }).addOnFailureListener(TeaherPosting.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {


                                Log.d("fail", "onFailure: " + e);


                                FirebaseDatabase.getInstance().getReference().child("GroupPost").child(StaticVariables.manuid).child(StaticVariables.groupClass.getPush()).child(keyy).removeValue();


                            }
                        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                                progressDialog.setMessage("Upload is " +((int) progress) + "% done");

                            }
                        });;


//

                    } else if (!post.equals(null) && !(uri == null)) {

                        progressDialog.show();

                        keyy = FirebaseDatabase.getInstance().getReference().child("GroupPost").child(StaticVariables.manuid).child(StaticVariables.groupClass.getPush()).push().getKey();

                        final File f = new File(String.valueOf(uri));


                        groupStorage.child(keyy).child(f.getName()).putFile(uri).addOnSuccessListener(TeaherPosting.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

//                                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                                progressDialog.setMessage("Upload is " + progress + "% done");
//                                int currentprogress = (int) progress;


                                Log.d("ffname", "onSuccess: " + f.getPath() + "uri" + uri);

                                TeachersPostClass teachersPostClass1 = new TeachersPostClass(StaticVariables.mName, StaticVariables.uuid, StaticVariables.uuid, keyy, taskSnapshot.getDownloadUrl().toString(), post, "");

                                Log.d("ext", "onSuccess: " + MimeTypeMap.getFileExtensionFromUrl(uri.toString()));
                                progressDialog.dismiss();
                                FirebaseDatabase.getInstance().getReference().child("GroupPost").child(StaticVariables.manuid).child(StaticVariables.groupClass.getPush()).child(teachersPostClass1.getPush()).setValue(teachersPostClass1);

//                                FirebaseDatabase.getInstance().getReference().child("PTeacherPost").child(StaticVariables.manuid).child(teachersPostClass1.getPush()).setValue(teachersPostClass1);

                                Toast.makeText(getApplicationContext(), "Posted", Toast.LENGTH_SHORT).show();



//                            Toast.makeText(TeaherPosting.this, "Posted", Toast.LENGTH_SHORT).show();


                            }
                        }).addOnFailureListener(TeaherPosting.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {


                                Log.d("fail", "onFailure: " + e);


                                FirebaseDatabase.getInstance().getReference().child("GroupPost").child(StaticVariables.manuid).child(StaticVariables.groupClass.getPush()).child(keyy).removeValue();


                            }
                        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                                progressDialog.setMessage("Upload is " +((int) progress) + "% done");

                            }
                        });;


                    }





                }


            }
        });


        att.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
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

            File file = new File(uri.toString());

            String fname = file.getName();

            if (fname.lastIndexOf(".") != -1 && fname.lastIndexOf(".") != 0) {

                extension = fname.substring(fname.lastIndexOf(".") + 1);

                Log.d("extension", "onActivityResult: " + extension);


            }

            Log.d("file", "onActivityResult: " + file.getName() + file.getPath());

            //  imageView.setImageURI(uri);


        }


    }


}
