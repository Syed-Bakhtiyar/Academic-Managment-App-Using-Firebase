package com.example.bakhtiyar.schoolreqruimentsystem;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters.CommentsListAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;

public class ActivityForComment extends AppCompatActivity {

    public static final int RC_SIGN_IN = 1;

    private static final int RC_PHOTO_PICKER =  2;


    CommentsClass commentsClass;

    String key;

    StorageReference storageReference;

    String comment;

    FirebaseStorage firebaseStorage;

    ListView listView;

    ArrayList<CommentsClass> arrayList;


    TextView name, post;

    LinearLayout lout;

    CardView cardView;

    CommentsListAdapter commentsListAdapter;

    LikesClass likesClass;

    EditText editText;

//    TextView textView;

    ImageButton imageButton;

    private Button button;
    private Uri uri;
    private DownloadManager downloadManager;

    ArrayList<LikesClass> likesClassArrayList;
    private int chk = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_for_comment);

//        textView = (TextView) findViewById(R.id.total);

        imageButton = (ImageButton) findViewById(R.id.star);

        listView = (ListView) findViewById(R.id.list);

        cardView = (CardView) findViewById(R.id.cardv);

        editText = (EditText) findViewById(R.id.messageEditText);

        button = (Button) findViewById(R.id.sendButton);

        arrayList = new ArrayList<>();

        likesClassArrayList = new ArrayList<>();

        func();


//
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ActivityForComment.this, "Hello", Toast.LENGTH_SHORT).show();
//            }
//        });

        commentsListAdapter = new CommentsListAdapter(arrayList,this);

        downloadManager = (DownloadManager) getSystemService(Activity.DOWNLOAD_SERVICE);


//        for (int i=0;i<likesClassArrayList.size();i++){
//
//            if(likesClassArrayList.get(i).getUid().equals(StaticVariables.uuid)){
//
//
//                imageButton.setImageResource(R.drawable.yellowstar);
//
//                break;
//
//            }
//
//
//        }

        name = (TextView) findViewById(R.id.naam);

        post = (TextView) findViewById(R.id.textpost);

        lout = (LinearLayout) findViewById(R.id.lout);

        firebaseStorage = FirebaseStorage.getInstance();

        storageReference = firebaseStorage.getReference();

        listView.setAdapter(commentsListAdapter);

        findViewById(R.id.mylikes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActivityForComment.this,MyTotalLikes.class);

//                intent.putExtra("key",StaticVariables.teachersPostClass.getPush());

                startActivity(intent);

//                Toast.makeText(ActivityForComment.this, "Ok done", Toast.LENGTH_SHORT).show();



            }
        });

        FirebaseDatabase.getInstance().getReference().child("Comments").child(StaticVariables.teachersPostClass.getPush()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                commentsClass = dataSnapshot.getValue(CommentsClass.class);

                Log.d("dd", "onChildAdded: "+commentsClass.getCommint()+" data "+dataSnapshot.getValue());


                arrayList.add(commentsClass);

                commentsListAdapter.notifyDataSetChanged();

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


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(chk == 1){

                    FirebaseDatabase.getInstance().getReference().child("Likes").child(StaticVariables.teachersPostClass.getPush()).child(StaticVariables.uuid).removeValue();

                    imageButton.setImageResource(R.drawable.blankstar);



                    chk=0;

                }
                else {

                    likesClass = new LikesClass(StaticVariables.mName, StaticVariables.uuid, StaticVariables.teachersPostClass.getPush());

                    FirebaseDatabase.getInstance().getReference().child("Likes").child(likesClass.getComid()).child(likesClass.getUid()).setValue(likesClass);

                    chk=1;
                }

                Toast.makeText(ActivityForComment.this, ""+"Star", Toast.LENGTH_SHORT).show();
            }
        });







        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence.toString().trim().length()>0){
                    button.setEnabled(true);
                }else {
                    button.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        if(StaticVariables.teachersPostClass.getUrl() == null){

            lout.setVisibility(View.GONE);


        }
        else {

            lout.setVisibility(View.VISIBLE);


            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(ActivityForComment.this, "Press Long To Download Attachments", Toast.LENGTH_SHORT).show();

                }
            });

            cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    uri = Uri.parse(StaticVariables.teachersPostClass.getUrl());

                    File ff = new File(String.valueOf(uri));


                    String extention = "none";



                    DownloadManager.Request request = new DownloadManager.Request(uri);

                    request.setDescription("Downloading.. " + ff.getName()).setTitle("Wait");


                    Toast.makeText(getApplicationContext(), " " + "downloading", Toast.LENGTH_SHORT).show();
                    request.setDestinationInExternalPublicDir("/Acadmic App/Group Post", ff.getName() + extention);


                    request.setVisibleInDownloadsUi(true);

                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI
                            | DownloadManager.Request.NETWORK_MOBILE);
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    downloadManager.enqueue(request);



//                    Toast.makeText(ActivityForComment.this, "Long Click", Toast.LENGTH_SHORT).show();

                    return false;
                }
            });

        }

        name.setText(StaticVariables.name+"");

        post.setText(StaticVariables.teachersPostClass.getPost()+"");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (StaticVariables.isteacher){

                    comment = editText.getText().toString().trim();

                    CommentsClass commentsClass = new CommentsClass( FirebaseDatabase.getInstance().getReference().child("Comments").child(StaticVariables.teachersPostClass.getPush()).push().getKey(), StaticVariables.teachersHire.getName(), StaticVariables.url,comment,null);

                    FirebaseDatabase.getInstance().getReference().child("Comments").child(StaticVariables.teachersPostClass.getPush()).child(commentsClass.getPush()).setValue(commentsClass);

                    editText.setText("");



                }
                else {

                    comment = editText.getText().toString().trim();

                    CommentsClass commentsClass = new CommentsClass( FirebaseDatabase.getInstance().getReference().child("Comments").child(StaticVariables.teachersPostClass.getPush()).push().getKey(), StaticVariables.studentInfo.getName(), "",comment,null);

                    FirebaseDatabase.getInstance().getReference().child("Comments").child(StaticVariables.teachersPostClass.getPush()).child(commentsClass.getPush()).setValue(commentsClass);

                    editText.setText("");




                }




            }
        });




        findViewById(R.id.mPhotoPickerButton).setOnClickListener(new View.OnClickListener() {
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);




        if(requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK){

            key = FirebaseDatabase.getInstance().getReference().child("Comments").child(StaticVariables.teachersPostClass.getPush()).push().getKey();

            Uri uri = data.getData();



           // StorageReference photoref = storageReference.child(uri.getLastPathSegment());

            storageReference.child("Comments").child(StaticVariables.teachersPostClass.getPush()).child(key).putFile(uri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {



                    Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    CommentsClass commentsClass = new CommentsClass( key, StaticVariables.mName, StaticVariables.url,null,downloadUrl.toString());

                    FirebaseDatabase.getInstance().getReference().child("Comments").child(StaticVariables.teachersPostClass.getPush()).child(commentsClass.getPush()).setValue(commentsClass);






                }
            });


        }




    }


    public void func(){

//        likesClassArrayList.clear();

//        FirebaseDatabase.getInstance().getReference().child("Likes").child(StaticVariables.teachersPostClass.getPush()).addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//                LikesClass lk = dataSnapshot.getValue(LikesClass.class);
//
//
//
//                likesClassArrayList.add(lk);
//
//
//                Log.d("arraylist", "onChildAdded: "+likesClassArrayList.size());
//
//                textView.setText(likesClassArrayList.size()+"");
//
//
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


        FirebaseDatabase.getInstance().getReference().child("Likes").child(StaticVariables.teachersPostClass.getPush()).child(StaticVariables.uuid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {



                if (dataSnapshot != null){
                    imageButton.setImageResource(R.drawable.yellowstar);

                chk = 1;


                }
//                else {
//
//
//
//                    chk = 0;
//
//                }
//                LikesClass lk = dataSnapshot.getValue(LikesClass.class);
//
//                likesClassArrayList.add(lk);

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




    }


}
