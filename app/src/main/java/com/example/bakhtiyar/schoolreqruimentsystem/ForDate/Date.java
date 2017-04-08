package com.example.bakhtiyar.schoolreqruimentsystem.ForDate;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Bakhtiyar on 2/13/2017.
 */
public class Date extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    EditText textView;

    public Date(View textView) {
        this.textView = (EditText) textView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar  c = Calendar.getInstance();

        int y = c.get(Calendar.YEAR);

        int m = c.get(Calendar.MONTH);

        int d = c.get(Calendar.DAY_OF_YEAR);

        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) this,y,m,d);

    }

    @Override
    public void onDateSet(DatePicker view, int y, int m, int d){
        String date =""+d+"/"+m+"/"+y;

        textView.setText(date);

    }




//
//    @Override
//    public void onClick(DialogInterface dialogInterface, int i) {
//
//    }
}
