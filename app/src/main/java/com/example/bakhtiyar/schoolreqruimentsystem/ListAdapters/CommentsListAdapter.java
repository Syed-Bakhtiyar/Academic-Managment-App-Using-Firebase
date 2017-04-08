package com.example.bakhtiyar.schoolreqruimentsystem.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bakhtiyar.schoolreqruimentsystem.CommentsClass;
import com.example.bakhtiyar.schoolreqruimentsystem.R;

import java.util.ArrayList;

/**
 * Created by Bakhtiyar on 3/4/2017.
 */
public class CommentsListAdapter extends BaseAdapter {

   ArrayList<CommentsClass> arrayList;

   Context context;

    LayoutInflater inflater;

    TextView name, commint;

    ImageView image;

    public CommentsListAdapter(ArrayList<CommentsClass> arrayList, Context context) {
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

        view = inflater.from(context).inflate(R.layout.commints_list,viewGroup,false);

        image = (ImageView) view.findViewById(R.id.image);

        name = (TextView) view.findViewById(R.id.naam);

        commint = (TextView) view.findViewById(R.id.comment);

        name.setText(arrayList.get(i).getName());

        if(arrayList.get(i).getCommint() == null){

            commint.setVisibility(View.GONE);

            image.setVisibility(View.VISIBLE);

            Glide.with(image.getContext())
                    .load(arrayList.get(i).getUrl())
                    .into(image);

        }
        else {

            commint.setVisibility(View.VISIBLE);

            image.setVisibility(View.GONE);

            commint.setText(arrayList.get(i).getCommint());



        }


        return view;
    }
}
