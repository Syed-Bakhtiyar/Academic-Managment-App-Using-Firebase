package com.example.bakhtiyar.schoolreqruimentsystem.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.bakhtiyar.schoolreqruimentsystem.ManagerPostFragment;
import com.example.bakhtiyar.schoolreqruimentsystem.PostForAdmission;
import com.example.bakhtiyar.schoolreqruimentsystem.ViewMyAddmission;
import com.example.bakhtiyar.schoolreqruimentsystem.ViewStudentsWhoApplied;

/**
 * Created by Bakhtiyar on 2/20/2017.
 */
public class PostAdmissionFragAdapter extends FragmentPagerAdapter {
    public PostAdmissionFragAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){

            case 0:

                return "Post your Addmission";

            case 1:

                return "View Your Addmission";

            case 2:

                return "Applied";


            default:

                return null;





        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:

                return new ManagerPostFragment();

            case 1:

                return new ViewMyAddmission();

            case 2:

                return  new ViewStudentsWhoApplied();

            default:

                return null;





        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
