package com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bakhtiyar.schoolreqruimentsystem.MainFragments.InterViewClass;
import com.example.bakhtiyar.schoolreqruimentsystem.R;

import java.util.ArrayList;

/**
 * Created by Bakhtiyar on 2/14/2017.
 */
public class InterViewClassList extends BaseAdapter {

    TextView name, date, time, desc;

    ArrayList<InterViewClass> arrayList;

    Context context;

    LayoutInflater inflater;

    public InterViewClassList(ArrayList<InterViewClass> arrayList, Context context) {
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

        view = inflater.from(context).inflate(R.layout.interview_list,viewGroup,false);

        name = (TextView) view.findViewById(R.id.name);

        date = (TextView) view.findViewById(R.id.date);

        time = (TextView) view.findViewById(R.id.time);

        desc = (TextView) view.findViewById(R.id.desc);

        name.setText(arrayList.get(i).getName());

        date.setText(arrayList.get(i).getDate());

        time.setText(arrayList.get(i).getTime());

        desc.setText(arrayList.get(i).getDiscription());




        return view;
    }
}
