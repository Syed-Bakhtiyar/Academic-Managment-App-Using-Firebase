package com.example.bakhtiyar.schoolreqruimentsystem;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bakhtiyar.schoolreqruimentsystem.Adapters.PostAdmissionFragAdapter;

public class PostForAdmission extends AppCompatActivity {

    PostAdmissionFragAdapter postAdmissionFragAdapter;

    TabLayout tabLayout;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_for_admission);

        postAdmissionFragAdapter = new PostAdmissionFragAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.vp);

        tabLayout = (TabLayout) findViewById(R.id.tab);

        viewPager.setAdapter(postAdmissionFragAdapter);

        tabLayout.setupWithViewPager(viewPager);

    }
}
