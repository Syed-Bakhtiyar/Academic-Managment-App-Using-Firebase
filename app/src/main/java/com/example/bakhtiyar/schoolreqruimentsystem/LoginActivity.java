package com.example.bakhtiyar.schoolreqruimentsystem;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.bakhtiyar.schoolreqruimentsystem.Adapters.MainFragmentAdapter;

import java.util.Timer;

public class LoginActivity extends AppCompatActivity {


    ViewPager viewPager;

    MainFragmentAdapter mainFragmentAdapter;

    Timer spTimer;

    final  long DELAY = 3000;

    boolean shedule = false;

    LinearLayout linearLayout;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        startActivity(new Intent(LoginActivity.this, LoginContainer.class));

//        imageView = (ImageView) findViewById(R.id.start);

//        linearLayout = (LinearLayout) findViewById(R.id.ll);
//

//
//        viewPager = (ViewPager) findViewById(R.id.vp1);
//
//        viewPager.setVisibility(View.GONE);

//        spTimer = new Timer();
//
//        spTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//
//
//                finish();
//
//
//
//                startActivity(new Intent(LoginActivity.this,MyloginActivity.class));
//
////                viewPager.setVisibility(View.VISIBLE);
////
////                imageView.setVisibility(View.GONE);
//
//
//            }
//        },DELAY);




    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(shedule) {
            spTimer.cancel();
        }
        spTimer.purge();
    }
}
