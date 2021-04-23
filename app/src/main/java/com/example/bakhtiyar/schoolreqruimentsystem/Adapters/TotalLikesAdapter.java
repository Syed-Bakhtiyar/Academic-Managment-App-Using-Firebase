package com.example.bakhtiyar.schoolreqruimentsystem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bakhtiyar.schoolreqruimentsystem.LikesClass;
import com.example.bakhtiyar.schoolreqruimentsystem.R;

import java.util.ArrayList;

/**
 * Created by Syed_Bakhtiyar on 4/4/2017.
 */

public class TotalLikesAdapter extends BaseAdapter {


    LayoutInflater inflater;

    ArrayList<LikesClass> arrayList;

    Context context;

    TextView textView;

    public TotalLikesAdapter(ArrayList<LikesClass> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

         convertView = inflater.from(context).inflate(R.layout.group_list_students,parent,false);
         convertView.findViewById(R.id.selection).setVisibility(View.GONE);
         textView = (TextView) convertView.findViewById(R.id.name);
         textView.setText(arrayList.get(position).getName());
         return convertView;
    }
}
