package com.example.bakhtiyar.schoolreqruimentsystem.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.bakhtiyar.schoolreqruimentsystem.TeacherStaff;
import com.example.bakhtiyar.schoolreqruimentsystem.TeaherApproval;

/**
 * Created by Bakhtiyar on 2/13/2017.
 */
public class ForTeachersFragAdapter extends FragmentPagerAdapter {
    public ForTeachersFragAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){

            case 0:

                return "Your Staff Teachers";
            case 1:

                return "Pending For Approval";
            default:
                return null;
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:

                return new TeacherStaff();
            case 1:

                return new TeaherApproval();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
