package com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bakhtiyar.schoolreqruimentsystem.R;
import com.example.bakhtiyar.schoolreqruimentsystem.WriteYourVacancies;

import java.util.ArrayList;

/**
 * Created by Bakhtiyar on 2/12/2017.
 */
public class ManagerViewPostList extends BaseAdapter {

    TextView name, qual,exp,skills;


    ArrayList<WriteYourVacancies> arrayList;

    Context context;

    LayoutInflater inflater;

    public ManagerViewPostList(ArrayList<WriteYourVacancies> arrayList, Context context) {
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

        view = inflater.from(context).inflate(R.layout.view_posts,viewGroup,false);

        name = (TextView) view.findViewById(R.id.name);

        qual = (TextView) view.findViewById(R.id.qual);

        exp = (TextView) view.findViewById(R.id.experience);

        skills = (TextView) view.findViewById(R.id.skills);

        name.setText(arrayList.get(i).getName());

        qual.setText(arrayList.get(i).getQual());

        exp.setText(arrayList.get(i).getExp());

        skills.setText(arrayList.get(i).getSkills());

        return view;
    }
}
