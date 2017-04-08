package com.example.bakhtiyar.schoolreqruimentsystem;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bakhtiyar.schoolreqruimentsystem.Adapters.StudentFragmentAdapter;

public class StudentPanel extends AppCompatActivity {

    ViewPager viewPager;

    StudentFragmentAdapter studentFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_panel);

    studentFragmentAdapter = new StudentFragmentAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.tab);

        viewPager.setAdapter(studentFragmentAdapter);


    }
}
