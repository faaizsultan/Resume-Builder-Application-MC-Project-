package com.example.resumebuilderapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class createResume extends AppCompatActivity {

    String loggedInUserName;
    Context context;
    DBHelper db=new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume);
        Button createBtn= (Button) findViewById(R.id.createResumeBtn);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveResume();
            }
        });
    }
    public void saveResume(){
       // Toast.makeText(this, "Save Resume Function", Toast.LENGTH_SHORT).show();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.loggedInUserName = extras.getString("loggedInUserName");
        }
        //getting all data from text fields
        String college,degree,cgpa,objective,experience,skills,website;
        college = ((Editable)((EditText) findViewById(R.id.college)).getText()).toString().trim();
        degree = ((Editable)((EditText) findViewById(R.id.degree)).getText()).toString().trim();
        cgpa=  ((Editable)((EditText) findViewById(R.id.cgpa)).getText()).toString().trim();
        objective = ((Editable)((EditText) findViewById(R.id.obj)).getText()).toString().trim();
        experience = ((Editable)((EditText) findViewById(R.id.experience)).getText()).toString().trim();
        skills = ((Editable)((EditText) findViewById(R.id.skills)).getText()).toString().trim();
        website = ((Editable)((EditText) findViewById(R.id.website)).getText()).toString().trim();

        //check if data is empty
        if(college.isEmpty()||degree.isEmpty()||cgpa.isEmpty()||objective.isEmpty()||experience.isEmpty()||skills.isEmpty()||website.isEmpty())
        {
            Toast.makeText(this,"Please Fill Out the Missing Data",Toast.LENGTH_SHORT).show();
        }
        else
        {
            //creating content values
            ContentValues cv=new ContentValues();
            cv.put("userName",this.loggedInUserName);
            cv.put("college",college);
            cv.put("degreeName",degree);
            cv.put("cgpa",cgpa);
            cv.put("objective",objective);
            cv.put("experience",experience);
            cv.put("skills",skills);
            cv.put("website",website);
            //db.execSQL("create table resumes (college TEXT NOT NULL,degreeName TEXT NOT NULL,cgpa INTEGER NOT NULL,objective TEXT NOT NULL,experience TEXT NOT NULL,skills TEXT NOT NULL,"+
              //      "website TEXT NOT NULL)");

            //inserting data
            long rows=db.getWritableDatabase().insert("resumes",null,cv);
            Toast.makeText(this, "SuccessFully Saved!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(createResume.this, Home.class);
            i.putExtra("loggedInUserName",loggedInUserName);
            startActivity(i);
        }

    }
}
