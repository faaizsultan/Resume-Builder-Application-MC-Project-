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
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class signUp extends AppCompatActivity {
    Context context;
    DBHelper db=new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        DBHelper db=new DBHelper(this);
        Button signInbtn = (Button) findViewById(R.id.signInbtnFromSignUp);
        signInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signUp.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //Regitering a User.

        Button signUpbtn= (Button) findViewById(R.id.signUpbtn);
        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              registerUser();
            }
        });
    }
    public void registerUser(){
        String fname,lname,uname,password,email,gender,phone,city;
        fname = ((Editable)((EditText) findViewById(R.id.firstName)).getText()).toString().trim();
        lname = ((Editable)((EditText) findViewById(R.id.lastName)).getText()).toString().trim();
        uname=  ((Editable)((EditText) findViewById(R.id.userName)).getText()).toString().trim();
        password = ((Editable)((EditText) findViewById(R.id.password)).getText()).toString().trim();
        email = ((Editable)((EditText) findViewById(R.id.Email)).getText()).toString().trim();
        phone = ((Editable)((EditText) findViewById(R.id.Phone)).getText()).toString().trim();
        city = ((Editable)((EditText) findViewById(R.id.City)).getText()).toString().trim();
        RadioButton male,female;
        male=(RadioButton)findViewById(R.id.male);
        female=(RadioButton)findViewById(R.id.female);
        if(male.isChecked())
            gender="Male";
        else
           gender="Female";
        if(fname.isEmpty()||lname.isEmpty()||uname.isEmpty()||password.isEmpty()||email.isEmpty()||phone.isEmpty()||city.isEmpty())
        {
            Toast.makeText(this,"Please Fill Out the Missing Data",Toast.LENGTH_SHORT).show();
        }
        else {
           Cursor cursor=db.getReadableDatabase().query("Users",null,"Username=?",new String[]{uname},null,null,null);
            if(cursor.moveToFirst()) {
                Toast.makeText(this, "User Already Exists", Toast.LENGTH_SHORT).show();
            }
            else
            {
                ContentValues cv=new ContentValues();
                cv.put("firstName",fname);
                cv.put("lastName",lname);
                cv.put("userName",uname);
                cv.put("password",password);
                cv.put("email",email);
                cv.put("gender",gender);
                cv.put("phoneNumber",phone);
                cv.put("city",city);
                long rows=db.getWritableDatabase().insert("Users",null,cv);
                Toast.makeText(this, "SuccessFull SignUP!"+rows, Toast.LENGTH_SHORT).show();
            }
        }
    }
}