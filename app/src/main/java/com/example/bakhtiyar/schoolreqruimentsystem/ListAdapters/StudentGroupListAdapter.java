package com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bakhtiyar.schoolreqruimentsystem.R;
import com.example.bakhtiyar.schoolreqruimentsystem.StudentInfo;

import java.util.ArrayList;

/**
 * Created by Syed_Bakhtiyar on 4/1/2017.
 */

public class StudentGroupListAdapter extends BaseAdapter {

    ArrayList<StudentInfo> arrayList;

    Context context;

    LayoutInflater inflater;

    TextView name, qual, exp,age,skill,gender,txt_fname,phone,email;

    ImageView imageView;

    public StudentGroupListAdapter(ArrayList<StudentInfo> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.from(context).inflate(R.layout.group_list_students,viewGroup,false);




        name = (TextView) view.findViewById(R.id.name);

        name.setText("Name: "+arrayList.get(i).getName());





        return view;
    }

}
