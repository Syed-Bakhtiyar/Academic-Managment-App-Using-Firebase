package com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bakhtiyar.schoolreqruimentsystem.MainFragments.TeachersPostClass;
import com.example.bakhtiyar.schoolreqruimentsystem.R;

import java.util.ArrayList;

/**
 * Created by Bakhtiyar on 2/15/2017.
 */
public class TeacherPostListAdapter extends BaseAdapter {

    ArrayList<TeachersPostClass> arrayList;

    LinearLayout linearLayout;

    Context context;

    LayoutInflater inflater;

    TextView name, uri, post;




    public TeacherPostListAdapter(ArrayList<TeachersPostClass> arrayList, Context context) {
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

        view = inflater.from(context).inflate(R.layout.teacher_post,viewGroup,false);

        name = (TextView) view.findViewById(R.id.naam);

        linearLayout = (LinearLayout) view.findViewById(R.id.lout);

        post = (TextView) view.findViewById(R.id.textpost);

        name.setText(arrayList.get(i).getName());

        if(arrayList.get(i).getUrl() == null){

            linearLayout.setVisibility(View.GONE);
        }
        else {

            linearLayout.setVisibility(View.VISIBLE);




        }

        post.setText(arrayList.get(i).getPost());

        return view;
    }
}
