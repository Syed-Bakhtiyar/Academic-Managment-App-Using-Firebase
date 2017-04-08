package com.example.bakhtiyar.schoolreqruimentsystem;


import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters.TeacherPostListAdapter;
import com.example.bakhtiyar.schoolreqruimentsystem.MainFragments.TeachersPostClass;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherPostFragment extends Fragment {

    View v;

    int temp;

    File localFile;

    ListView listView;

    ArrayList<TeachersPostClass> arrayList;

    TeachersPostClass teachersPostClass;

    TeacherPostListAdapter teacherPostListAdapter;

    StorageReference storage;

    ProgressDialog progressDialog;

    AlertDialog.Builder alert;

    EditText posttxt;

    DownloadManager downloadManager;

    LinearLayout linearLayout;

    Uri uri;

    public TeacherPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_teacher_post, container, false);


        downloadManager = (DownloadManager) getContext().getSystemService(Activity.DOWNLOAD_SERVICE);


        storage = FirebaseStorage.getInstance().getReference().child("TeacherPosts").child(StaticVariables.manuid);


        listView = (ListView) v.findViewById(R.id.list);

        arrayList = new ArrayList<>();

        teacherPostListAdapter = new TeacherPostListAdapter(arrayList, getContext());

        listView.setAdapter(teacherPostListAdapter);

        FirebaseDatabase.getInstance().getReference().child("TeacherPost").child(StaticVariables.manuid).child(StaticVariables.uuid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                teachersPostClass = dataSnapshot.getValue(TeachersPostClass.class);

                arrayList.add(teachersPostClass);

                teacherPostListAdapter.notifyDataSetChanged();


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


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                temp = i;


                alert = new AlertDialog.Builder(getContext());
                try {


                    if (!arrayList.get(temp).getUrl().equals(null)) {
                        alert.setPositiveButton("Download Attachment", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String extention = arrayList.get(temp).getFiletype();

                                uri = Uri.parse(arrayList.get(temp).getUrl());


                                File ff = new File(String.valueOf(uri));


                                DownloadManager.Request request = new DownloadManager.Request(uri);

                                request.setDescription("Downloading.. " + ff.getName()).setTitle("Wait");


                                Toast.makeText(getContext(), "Downloading" , Toast.LENGTH_SHORT).show();
                                request.setDestinationInExternalPublicDir("/Acadmic App/Teacher Post", ff.getName() + extention);


                                request.setVisibleInDownloadsUi(true);

                                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI
                                        | DownloadManager.Request.NETWORK_MOBILE);
                                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                                downloadManager.enqueue(request);


                            }
                        });
                    }

                } catch (Exception e) {

                    Log.d("exc", "onItemClick: " + e);
                }

                alert.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        FirebaseStorage.getInstance().getReference().child("TeacherPosts").child(StaticVariables.manuid).child(arrayList.get(temp).getPush()).delete();


                        FirebaseDatabase.getInstance().getReference().child("TeacherPost").child(StaticVariables.manuid).child(StaticVariables.uuid).child(arrayList.get(temp).getPush()).removeValue();

                        arrayList.remove(temp);

                        teacherPostListAdapter.notifyDataSetChanged();



                        Toast.makeText(getContext(), "kkkk", Toast.LENGTH_SHORT).show();

                    }
                });

                alert.show();


            }
        });

        v.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), TeaherPosting.class);

                intent.putExtra("data",1);


                startActivity(intent);

            }
        });


        return v;
    }


}
