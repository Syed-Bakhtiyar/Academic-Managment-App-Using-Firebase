package com.example.bakhtiyar.schoolreqruimentsystem;

import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bakhtiyar.schoolreqruimentsystem.Adapters.ForTeachersFragAdapter;

public class TeachersHiring extends AppCompatActivity {


    ViewPager viewPager;

    TabLayout tabLayout;

    ForTeachersFragAdapter forTeachersFragAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_hiring);

        forTeachersFragAdapter = new ForTeachersFragAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.vp);

        tabLayout = (TabLayout) findViewById(R.id.tab);

        viewPager.setAdapter(forTeachersFragAdapter);

        tabLayout.setupWithViewPager(viewPager);

    }

}
