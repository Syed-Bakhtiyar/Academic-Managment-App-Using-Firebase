package com.example.bakhtiyar.schoolreqruimentsystem;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bakhtiyar.schoolreqruimentsystem.Adapters.TeachersFragmentAdapter;

public class TeacherPanel extends AppCompatActivity {

    ViewPager vp;

    TeachersFragmentAdapter teachersFragmentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_panel);

        teachersFragmentAdapter = new TeachersFragmentAdapter(getSupportFragmentManager());

        vp = (ViewPager) findViewById(R.id.vp);

        vp.setAdapter(teachersFragmentAdapter);

    }
}
