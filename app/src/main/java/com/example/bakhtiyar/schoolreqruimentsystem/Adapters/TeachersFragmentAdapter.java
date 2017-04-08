package com.example.bakhtiyar.schoolreqruimentsystem.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.bakhtiyar.schoolreqruimentsystem.Invitation;
import com.example.bakhtiyar.schoolreqruimentsystem.TeacherViewVacancy;
import com.example.bakhtiyar.schoolreqruimentsystem.TeachersProfile;
import com.example.bakhtiyar.schoolreqruimentsystem.TeachersWriteProfile;
import com.example.bakhtiyar.schoolreqruimentsystem.ViewNotification;

/**
 * Created by Bakhtiyar on 2/12/2017.
 */
public class TeachersFragmentAdapter extends FragmentPagerAdapter {
    public TeachersFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
//            case 0:
//
//                return new TeachersWriteProfile();

            case 0:

                return new TeachersProfile();

            case 1:

                return new TeacherViewVacancy();

            case 2:

                return new ViewNotification();
            case 3:

                return new Invitation();

            default:

                return null;




        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
//            case 0:
//
//                return "Create Your Profile";

            case 0:

                return "View Your Profile";

            case 1:

                return "View Vacancies";

            case 2:

                return "Notification";
            case 3:

                return "Your Academy :)";
            default:

                return null;




        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
