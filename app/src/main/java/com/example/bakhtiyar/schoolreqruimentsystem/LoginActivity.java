package com.example.bakhtiyar.schoolreqruimentsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakhtiyar.schoolreqruimentsystem.Adapters.MainFragmentAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

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

        startActivity(new Intent(LoginActivity.this,MyloginActivity.class));

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
