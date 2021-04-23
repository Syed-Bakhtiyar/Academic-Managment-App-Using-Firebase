package com.example.bakhtiyar.schoolreqruimentsystem;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.example.bakhtiyar.schoolreqruimentsystem.Adapters.MainFragmentAdapter;

public class LoginContainer extends AppCompatActivity {

    ViewPager viewPager;
    MainFragmentAdapter mainFragmentAdapter;
    private static final int REQUEST = 6;
    String[] permissions = new String[]{
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.ACCESS_FINE_LOCATION
    };

    public boolean hasCheck(){
        return (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
//        for(String permission : permissions){
//            if(ActivityCompat.checkSelfPermission(LoginContainer.this,permission) != PackageManager.PERMISSION_GRANTED){
//                return false;
//            }
//        }
//        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(LoginContainer.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylogin);
        getPermission();
        viewPager = (ViewPager) findViewById(R.id.vp1);
        mainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainFragmentAdapter);
    }

    private void getPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permissions != null) {
            while(!hasCheck()){
                ActivityCompat.requestPermissions(LoginContainer.this, permissions, REQUEST);
            }
        }
    }
}
