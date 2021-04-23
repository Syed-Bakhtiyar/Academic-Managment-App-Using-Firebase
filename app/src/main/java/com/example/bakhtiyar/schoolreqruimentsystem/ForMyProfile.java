package com.example.bakhtiyar.schoolreqruimentsystem;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ForMyProfile extends AppCompatActivity {

    MyAdapter myAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_my_profile);
        myAdapter = new MyAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.vp);
        viewPager.setAdapter(myAdapter);
    }
}


class MyAdapter extends FragmentPagerAdapter{
    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return null;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 1;
    }
}
