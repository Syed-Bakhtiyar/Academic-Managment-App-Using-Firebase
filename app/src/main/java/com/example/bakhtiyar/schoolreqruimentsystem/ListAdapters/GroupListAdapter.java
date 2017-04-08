package com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bakhtiyar.schoolreqruimentsystem.GroupClass;
import com.example.bakhtiyar.schoolreqruimentsystem.R;

import java.util.ArrayList;

/**
 * Created by Bakhtiyar on 3/4/2017.
 */
public class GroupListAdapter extends BaseAdapter {

    ArrayList<GroupClass> arrayList;

    Context context;

    LayoutInflater inflater;
    private TextView textView;

    ImageView imageView;

    public GroupListAdapter(ArrayList<GroupClass> arrayList, Context context) {
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
        view = inflater.from(context).inflate(R.layout.group_list,viewGroup,false);

        imageView = (ImageView) view.findViewById(R.id.img);

        Glide.with(imageView.getContext())
                .load(arrayList.get(i).getLink())
                .into(imageView);


        textView = (TextView) view.findViewById(R.id.user);

        textView.setText(arrayList.get(i).getGroupname());


        return view;

    }
}
