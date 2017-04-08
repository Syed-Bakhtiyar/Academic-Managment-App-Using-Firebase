package com.example.bakhtiyar.schoolreqruimentsystem;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bakhtiyar.schoolreqruimentsystem.Adapters.MainFragmentAdapter;

public class MyloginActivity extends AppCompatActivity {

    ViewPager viewPager;

    MainFragmentAdapter mainFragmentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylogin);


        viewPager = (ViewPager) findViewById(R.id.vp1);



        mainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager());



        viewPager.setAdapter(mainFragmentAdapter);


    }
}
