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
import com.example.bakhtiyar.schoolreqruimentsystem.TeachersHire;

import java.util.ArrayList;

/**
 * Created by Bakhtiyar on 2/12/2017.
 */
public class TeachersProfileListAdapter extends BaseAdapter {

    ArrayList<TeachersHire> arrayList;

    Context context;

    TextView name, qual, exp,age,skill,gender;

    ImageView imageView;


    LayoutInflater inflater;

    public TeachersProfileListAdapter(ArrayList<TeachersHire> arrayList, Context context) {
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
        view = inflater.from(context).inflate(R.layout.teachers_profile_list,viewGroup,false);

        name = (TextView) view.findViewById(R.id.name);
        qual = (TextView) view.findViewById(R.id.qualification);
        exp = (TextView) view.findViewById(R.id.experience);
        age = (TextView) view.findViewById(R.id.age);
        skill = (TextView) view.findViewById(R.id.TeachingSkills);
        gender = (TextView) view.findViewById(R.id.gender);
        imageView = (ImageView) view.findViewById(R.id.image);

        name.setText("Name: " + arrayList.get(i).getName());
        qual.setText("Qualification: " + arrayList.get(i).getQualification());
        exp.setText("Experience: " + arrayList.get(i).getExperience());
        age.setText("Age: " + arrayList.get(i).getAge());
        skill.setText("Skills: " + arrayList.get(i).getSkills());

        Glide.with(imageView.getContext())
                .load(arrayList.get(i).getImglink())
                .into(imageView);



        return view;
    }
}
