package com.example.bakhtiyar.schoolreqruimentsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class JobPostActivity extends AppCompatActivity {

    EditText qual, skills, exp;
    private WriteYourVacancies writeYourVacancies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_post);


        qual = (EditText) findViewById(R.id.qalification);
        skills = (EditText) findViewById(R.id.skills);
        exp = (EditText) findViewById(R.id.experience);


        findViewById(R.id.post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q,e,s;
                q = qual.getText().toString().trim();
                e = exp.getText().toString().trim();
                s = skills.getText().toString().trim();
                if(TextUtils.isEmpty(q)|| TextUtils.isEmpty(e)||TextUtils.isEmpty(s)){
                    Toast.makeText(JobPostActivity.this, "Please complete your Vacancies", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    writeYourVacancies = new WriteYourVacancies(q,e,s,StaticVariables.uuid, FirebaseDatabase.getInstance().getReference().child("Job").child(StaticVariables.uuid).push().getKey(),StaticVariables.managerInfo.getCampusname());
                    FirebaseDatabase.getInstance().getReference().child("Job").child(StaticVariables.uuid).child(writeYourVacancies.getPush()).setValue(writeYourVacancies);
                    FirebaseDatabase.getInstance().getReference().child("PublicJob").child(writeYourVacancies.getPush()).setValue(writeYourVacancies);
                    Toast.makeText(JobPostActivity.this, "Submitted", Toast.LENGTH_SHORT).show();
                    qual.setText("");
                    skills.setText("");
                    exp.setText("");
                    finish();
                }

            }
        });

        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
