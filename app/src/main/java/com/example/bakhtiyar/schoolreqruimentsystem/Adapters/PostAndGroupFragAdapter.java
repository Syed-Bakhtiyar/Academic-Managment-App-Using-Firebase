package com.example.bakhtiyar.schoolreqruimentsystem.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.bakhtiyar.schoolreqruimentsystem.AllMyGroups;
import com.example.bakhtiyar.schoolreqruimentsystem.ReadAllPosts;
import com.example.bakhtiyar.schoolreqruimentsystem.TeacherPostFragment;
import com.example.bakhtiyar.schoolreqruimentsystem.TeachersGroup;

/**
 * Created by Bakhtiyar on 2/15/2017.
 */
public class PostAndGroupFragAdapter extends FragmentPagerAdapter {

    int a;


    public PostAndGroupFragAdapter(FragmentManager fm, int a) {
        super(fm);

        this.a = a;
    }

    @Override
    public CharSequence getPageTitle(int position) {

       if(a==1){


           switch (position){
               case 0:

                   return "Post";

               case 1:

                   return "Create Group";

               case 2:

                   return "All Groups";
               default:

                   return null;


           }

       }

        else {


           switch (position){
               case 0:

                   return "All Post";


               case 1:

                   return "All Groups";
               default:

                   return null;


           }

       }


    }

    @Override
    public Fragment getItem(int position) {


        if(a==1){

            switch (position){
                case 0:

                    return new TeacherPostFragment();

                case 1:

                    return new TeachersGroup();

                case 2:

                    return new AllMyGroups();

                default:

                    return null;

            }





        }
        else {

            switch (position){
                case 0:

                    return new ReadAllPosts();


                case 1:

                    return new AllMyGroups();

                default:

                    return null;

            }

        }







    }

    @Override
    public int getCount() {

        if(a==1){

            return 3;

        }
        else {

            return 2;

        }

    }
}
