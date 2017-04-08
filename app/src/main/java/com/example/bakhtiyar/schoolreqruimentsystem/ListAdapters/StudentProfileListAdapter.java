package com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters;

import android.content.Context;
import android.support.v7.widget.CardView;
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
 * Created by Bakhtiyar on 2/19/2017.
 */
public class StudentProfileListAdapter extends BaseAdapter {
    ArrayList<StudentInfo> arrayList;

    Context context;

    LayoutInflater inflater;

    TextView name, qual, exp,age,skill,gender,txt_fname,phone,email;

    ImageView imageView;

    public StudentProfileListAdapter(ArrayList<StudentInfo> arrayList, Context context) {
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
     view = inflater.from(context).inflate(R.layout.fragment_view_profile,viewGroup,false);

        txt_fname = (TextView) view.findViewById(R.id.fname);

        phone = (TextView) view.findViewById(R.id.phone);
        email = (TextView) view.findViewById(R.id.email);



        name = (TextView) view.findViewById(R.id.name);
        qual = (TextView) view.findViewById(R.id.qualification);
        exp = (TextView) view.findViewById(R.id.dob);
        age = (TextView) view.findViewById(R.id.age);
        skill = (TextView) view.findViewById(R.id.classs);
        gender = (TextView) view.findViewById(R.id.gender);
        imageView = (ImageView) view.findViewById(R.id.image);



        Glide.with(imageView.getContext())
                .load(arrayList.get(i).getUrl())
                .into(imageView);



        name.setText("Name: "+arrayList.get(i).getName());
        txt_fname.setText("Gardian: "+arrayList.get(i).getFname());
        qual.setText("Last Class: "+arrayList.get(i).getQual());
        exp.setText("Date of Birth: "+arrayList.get(i).getDateOfBirth());
        age.setText("Age: "+arrayList.get(i).getAge()+"");
        skill.setText("Class Want: "+arrayList.get(i).getClasss());
        gender.setText("Gender: "+arrayList.get(i).getIsmale()+"");
        phone.setText("Phone: "+arrayList.get(i).getPhone());
        email.setText("Email: "+arrayList.get(i).getEmail());





        return view;
    }
}
