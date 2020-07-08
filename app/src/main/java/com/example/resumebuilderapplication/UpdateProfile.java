package com.example.resumebuilderapplication;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updateprofile);
        //getting data From Intent to auto fill in fields..
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String fname,lname,uname,password,email,gender,phone,city;
            fname=extras.getString("fname");
            lname=extras.getString("lname");
            uname=extras.getString("uname");
            password=extras.getString("password");
            email=extras.getString("email");
            gender=extras.getString("phone");
            phone=extras.getString("gender");
            city=extras.getString("city");
            //AutoFilling the fields...
            ((EditText)findViewById(R.id.firstName)).setText(fname);
            ((EditText) findViewById(R.id.lastName)).setText(lname);
            ((EditText) findViewById(R.id.userName)).setText(uname);
            ((EditText) findViewById(R.id.password)).setText(password);
            ((EditText) findViewById(R.id.Email)).setText(email);
            ((EditText) findViewById(R.id.Phone)).setText(phone);
            ((EditText) findViewById(R.id.City)).setText(city);
            if(gender.equals("Male"))
                ((RadioButton)findViewById(R.id.male)).setChecked(true);
            else
            {
                ((RadioButton)findViewById(R.id.male)).setChecked(false);
                ((RadioButton)findViewById(R.id.female)).setChecked(true);
            }
        }
    }
}