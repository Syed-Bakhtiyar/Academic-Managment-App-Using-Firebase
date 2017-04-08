package com.example.bakhtiyar.schoolreqruimentsystem.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.bakhtiyar.schoolreqruimentsystem.Admin;
import com.example.bakhtiyar.schoolreqruimentsystem.ManagerLogin;
import com.example.bakhtiyar.schoolreqruimentsystem.StudentLogin;
import com.example.bakhtiyar.schoolreqruimentsystem.TeacherLogin;

/**
 * Created by Bakhtiyar on 2/11/2017.
 */
public class MainFragmentAdapter extends FragmentPagerAdapter {


    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
//            case 0:
//
//                return new Admin();

            case 0:

                return new ManagerLogin();

            case 1:

                return new TeacherLogin();

            case 2:

                return new StudentLogin();

            default:

                return null;



        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
//            case 0:
//
//                return "Admin";

            case 0:

                return "Manager";

            case 1:

                return "Teacher";

            case 2:

                return "Student";

            default:

                return null;



        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
