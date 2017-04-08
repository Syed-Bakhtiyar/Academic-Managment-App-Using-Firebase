package com.example.bakhtiyar.schoolreqruimentsystem;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bakhtiyar.schoolreqruimentsystem.Adapters.PostAndGroupFragAdapter;

public class TeacherProfile extends AppCompatActivity {

   // String mkey;

    PostAndGroupFragAdapter postAndGroupFragAdapter;

    ViewPager vp;

    TabLayout tabLayout;

    int a;

    Bundle bundle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);

        bundle = getIntent().getExtras();

        a = bundle.getInt("key");


        vp = (ViewPager) findViewById(R.id.vp);

        tabLayout = (TabLayout) findViewById(R.id.tb);

        postAndGroupFragAdapter = new PostAndGroupFragAdapter(getSupportFragmentManager(),a);

        vp.setAdapter(postAndGroupFragAdapter);

        tabLayout.setupWithViewPager(vp);


    }
}
