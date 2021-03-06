package com.example.resumebuilderapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    String loggedInUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);


        //getting username Coming from sign in Screen..
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.loggedInUserName = extras.getString("loggedInUserName");
        }
        ((TextView) findViewById(R.id.textView9)).setText("Welcome " + loggedInUserName);

        //Update Profile
        Button updateProfile = (Button) findViewById(R.id.updateProfilebtninHomeScreen);
        updateProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Home.this, UpdateProfile.class);
                i.putExtra("loggedInUserName", loggedInUserName);
                startActivity(i);
            }
        });


        //Create Resume Btn
        Button createResume = (Button) findViewById(R.id.createResumeBtnInHome);
        createResume.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Home.this, createResume.class);
                i.putExtra("loggedInUserName", loggedInUserName);
                startActivity(i);
            }
        });


        //Preview Templates
        Button previewTemplates = (Button) findViewById(R.id.previewTemplate);
        previewTemplates.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(Home.this, TemplatePreview.class);
                i.putExtra("loggedInUserName", loggedInUserName);
                startActivity(i);
            }
        });
        //Delete Resume Butn
        Button deleteResume = (Button) findViewById(R.id.deleteResumeBtn);
        deleteResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(Home.this);
                int rows = db.getWritableDatabase().delete("resumes", "userName=?", new String[]{loggedInUserName});
                if (rows == 1)
                    Toast.makeText(Home.this, "Resume Records Deleted Succesfull", Toast.LENGTH_LONG).show();
            }
        });

        //Update Resume
        Button updateResume = (Button) findViewById(R.id.updateResumeBtnInHome);
        updateResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(Home.this);
                Cursor cursor = db.getReadableDatabase().query("resumes", null, "userName=?", new String[]{loggedInUserName}, null, null, null);
                if (!cursor.moveToFirst())
                    Toast.makeText(Home.this, "No Resume To Update!Create First", Toast.LENGTH_LONG).show();
                else {
                    Intent i = new Intent(Home.this, UpdateResume.class);
                    i.putExtra("loggedInUserName", loggedInUserName);
                    startActivity(i);
                }

            }
        });
    }
}
