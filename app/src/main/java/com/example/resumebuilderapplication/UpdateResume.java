package com.example.resumebuilderapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateResume extends AppCompatActivity {
    String loggedInUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updateresume);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.loggedInUserName = extras.getString("loggedInUserName");
        }

        DBHelper db=new DBHelper(UpdateResume.this);
        //Autofill data..
        Cursor cursor= db.getReadableDatabase().query("resumes",null,"userName=?",new String[]{loggedInUserName},null,null,null);
        if(cursor.moveToFirst()) {
            String degree, college, experience, skill, website, CGPA, objective;
            Integer cgpa;

            //getting Data from databae
            degree = cursor.getString(cursor.getColumnIndex("degreeName"));
            college = cursor.getString(cursor.getColumnIndex("college"));
            experience = cursor.getString(cursor.getColumnIndex("experience"));
            objective = cursor.getString(cursor.getColumnIndex("objective"));
            skill = cursor.getString(cursor.getColumnIndex("skills"));
            website = cursor.getString(cursor.getColumnIndex("website"));
            cgpa = cursor.getInt(cursor.getColumnIndex("cgpa"));
            CGPA = cgpa.toString();

            //Auto fill
            ((EditText) findViewById(R.id.college)).setText(college);
            ((EditText) findViewById(R.id.degree)).setText(degree);
            ((EditText) findViewById(R.id.experience)).setText(experience);
            ((EditText) findViewById(R.id.skills)).setText(skill);
            ((EditText) findViewById(R.id.website)).setText(website);
            ((EditText) findViewById(R.id.obj)).setText(objective);
            ((EditText) findViewById(R.id.cgpa)).setText(CGPA);
        }
        else {
            Toast.makeText(UpdateResume.this,"Some Error ", Toast.LENGTH_LONG).show();
        }
        //Update Resume
        Button updateResumeBtn = (Button) findViewById(R.id.updateResumebtninUpdateResumeScreen);
        updateResumeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String degree,college,experience,skill,CGPA,website,objective;

                    degree=((Editable)((EditText) findViewById(R.id.degree)).getText()).toString().trim();
                    college=((Editable)((EditText) findViewById(R.id.college)).getText()).toString().trim();
                    experience=((Editable)((EditText) findViewById(R.id.experience)).getText()).toString().trim();
                    skill=((Editable)((EditText) findViewById(R.id.skills)).getText()).toString();
                    CGPA=((Editable)((EditText) findViewById(R.id.cgpa)).getText()).toString().trim();
                    website=((Editable)((EditText) findViewById(R.id.website)).getText()).toString().trim();
                    objective=((Editable)((EditText) findViewById(R.id.obj)).getText()).toString().trim();

                    //Update in Database.
                    ContentValues cv = new ContentValues();
                    DBHelper db = new DBHelper(UpdateResume.this);
                    cv.put("degreeName", degree);
                    cv.put("college", college);
                    cv.put("experience", experience);
                    cv.put("objective", objective);
                    cv.put("skills", skill);
                    cv.put("website", website);
                    cv.put("cgpa",CGPA);
                    final int users = db.getWritableDatabase().update("resumes", cv, "userName=?", new String[]{loggedInUserName});

                    Toast.makeText(UpdateResume.this,"Resume Updated Successfully", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(UpdateResume.this, Home.class);
                    i.putExtra("loggedInUserName", loggedInUserName);
                    startActivity(i);
                }
        });

    }
}
