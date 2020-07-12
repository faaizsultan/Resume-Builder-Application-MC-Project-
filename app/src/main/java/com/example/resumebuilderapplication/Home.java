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
        ((TextView)findViewById(R.id.textView9)).setText("Welcome "+loggedInUserName);

        //Update Profile
        Button updateProfilebtn = (Button) findViewById(R.id.updateProfilebtninHomeScreen);
        updateProfilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, UpdateProfile.class);
                    i.putExtra("loggedInUserName",loggedInUserName);
                    startActivity(i);
            }
        });
        //Create Resume Btn
        Button createResume = (Button) findViewById(R.id.createResumeBtn);
        createResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, createResume.class);
                i.putExtra("loggedInUserName",loggedInUserName);
                startActivity(i);
            }
        });

        //Preview Templates
        Button previewTemplates = (Button) findViewById(R.id.previewTemplate);
        previewTemplates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, TemplatePreview.class);
                i.putExtra("loggedInUserName",loggedInUserName);
                startActivity(i);
            }
        });

    }
}
