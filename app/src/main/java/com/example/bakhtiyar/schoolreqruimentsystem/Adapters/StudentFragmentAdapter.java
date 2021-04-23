package com.example.bakhtiyar.schoolreqruimentsystem.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.bakhtiyar.schoolreqruimentsystem.MySchool;
import com.example.bakhtiyar.schoolreqruimentsystem.StudentCreateProfile;
import com.example.bakhtiyar.schoolreqruimentsystem.ViewAcademicAddmission;
import com.example.bakhtiyar.schoolreqruimentsystem.ViewAllAcademy;
import com.example.bakhtiyar.schoolreqruimentsystem.ViewProfile;

/**
 * Created by Bakhtiyar on 2/18/2017.
 */
public class StudentFragmentAdapter extends FragmentPagerAdapter {
    public StudentFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
//            case 0:
//
//                return "Create Your Profile";

            case 0:

                return "Profile";

            case 1:

                return "View All Academy";

            case 2:

                return "Notification";
            case 3:

                return "Your Academy";
            default:

                return null;




        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
//            case 0:
//
//                return new StudentCreateProfile();

            case 0:

                return new ViewProfile();

            case 1:

                return new ViewAllAcademy();

            case 2:

                return new ViewAcademicAddmission();
            case 3:

                return new MySchool();
            default:

                return null;




        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
