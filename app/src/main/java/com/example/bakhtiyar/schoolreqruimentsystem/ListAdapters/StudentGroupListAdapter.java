package com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
        final int index = i;
        if(arrayList.get(index).isSelected()){
            view.findViewById(R.id.container).setBackgroundColor(Color.parseColor("#ededed"));
        }
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.selection);
        checkBox.setChecked(arrayList.get(index).isSelected());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                arrayList.get(index).setSelected(isChecked);
            }
        });
        name = (TextView) view.findViewById(R.id.name);
        name.setText(arrayList.get(i).getName());
        System.out.println(arrayList);
        return view;
    }

}
