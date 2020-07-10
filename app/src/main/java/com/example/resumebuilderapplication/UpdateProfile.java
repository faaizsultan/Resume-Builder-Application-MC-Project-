package com.example.resumebuilderapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class UpdateProfile extends AppCompatActivity {
String loggedInUserName,firstName,lastName,Gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updateprofile);
        //AutoFilling Details
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            loggedInUserName=extras.getString("loggedInUserName");
            DBHelper db=new DBHelper(UpdateProfile.this);
            Cursor cursor=db.getReadableDatabase().query("Users",null,"userName=?",new String[]{loggedInUserName},null,null,null);
            //AutoFilling Data
            if(cursor.moveToFirst()) {
                String  uname,password, email, phone, city;
                    //getting Data from databae
                    firstName=cursor.getString(cursor.getColumnIndex("firstName"));
                    lastName=cursor.getString(cursor.getColumnIndex("lastName"));
                    uname=cursor.getString(cursor.getColumnIndex("userName"));
                    password=cursor.getString(cursor.getColumnIndex("password"));
                    email=cursor.getString(cursor.getColumnIndex("email"));
                    city=cursor.getString(cursor.getColumnIndex("city"));
                    phone=cursor.getString(cursor.getColumnIndex("phoneNumber"));
                    Gender=cursor.getString(cursor.getColumnIndex("gender"));
                //AutoFilling the fields...
                ((EditText) findViewById(R.id.firstName)).setText(firstName);
                ((EditText) findViewById(R.id.lastName)).setText(lastName);
                ((EditText) findViewById(R.id.userName)).setText(uname);
                ((EditText) findViewById(R.id.password)).setText(password);
                ((EditText) findViewById(R.id.Email)).setText(email);
                ((EditText) findViewById(R.id.Phone)).setText(phone);
                ((EditText) findViewById(R.id.City)).setText(city);
                if(Gender.equals("Female")) {
                    ((RadioButton) findViewById(R.id.male)).setChecked(false);
                    ((RadioButton)findViewById(R.id.female)).setChecked(true);
                }
                else
                    ((RadioButton) findViewById(R.id.male)).setChecked(true);
            }
        }
        Button updateProfilebtn=(Button)findViewById(R.id.updateProfileBtninUpdateScreen);
        updateProfilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname, lname, uname, password, email, gender, phone, city;
                fname=((Editable)((EditText) findViewById(R.id.firstName)).getText()).toString().trim();
                lname=((Editable)((EditText) findViewById(R.id.lastName)).getText()).toString().trim();
                uname=((Editable)((EditText) findViewById(R.id.userName)).getText()).toString().trim();
                password=((Editable)((EditText) findViewById(R.id.password)).getText()).toString();
                email=((Editable)((EditText) findViewById(R.id.Email)).getText()).toString().trim();
                phone=((Editable)((EditText) findViewById(R.id.Phone)).getText()).toString().trim();
                city=((Editable)((EditText) findViewById(R.id.City)).getText()).toString().trim();
                RadioButton male,female;
                male=(RadioButton)findViewById(R.id.male);
                female=(RadioButton)findViewById(R.id.female);
                if(male.isChecked())
                    gender="Male";
                else
                    gender="Female";
                if(uname.equals(loggedInUserName)&& fname.equals(firstName)&&lname.equals(lastName) && gender.equals(Gender)) {
                    ContentValues cv = new ContentValues();
                    DBHelper db = new DBHelper(UpdateProfile.this);
                    cv.put("firstName", fname);
                    cv.put("lastName", lname);
                    cv.put("userName", uname);
                    cv.put("password", password);
                    cv.put("email", email);
                    cv.put("gender", gender);
                    cv.put("phoneNumber", phone);
                    cv.put("city", city);
                    final int users = db.getWritableDatabase().update("users", cv, "userName=?", new String[]{loggedInUserName});

                   Toast.makeText(UpdateProfile.this,"Profile Updated Successfully", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(UpdateProfile.this, Home.class);
                    i.putExtra("loggedInUserName", loggedInUserName);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(UpdateProfile.this, "Cant Change:\nFirst Name\nLast Name\nUser Name\nGender", Toast.LENGTH_LONG).show();
                }
            }
        });
        Button cancelBtn = (Button) findViewById(R.id.cancelBtnInUpdateScreen);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UpdateProfile.this, loggedInUserName, Toast.LENGTH_SHORT).show();
                 Intent i = new Intent(UpdateProfile.this, Home.class);
                i.putExtra("loggedInUserName",loggedInUserName);
                startActivity(i);
            }
        });
    }
}