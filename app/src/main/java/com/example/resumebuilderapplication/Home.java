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
            this.loggedInUserName = extras.getString("LoggedInUserName");
        }
        ((TextView)findViewById(R.id.textView9)).setText("Welcome "+loggedInUserName);
        Button updateProfilebtn = (Button) findViewById(R.id.updateProfilebtninHomeScreen);
        updateProfilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db=new DBHelper(Home.this);

                Cursor cursor=db.getReadableDatabase().query("Users",null,"userName=?",new String[]{loggedInUserName},null,null,null);
                if(cursor.moveToFirst())
                {
                    //getting Data from database.
                    String fname,lname,uname,password,email,city,phone,gender;
                    fname=cursor.getString(cursor.getColumnIndex("firstName"));
                    lname=cursor.getString(cursor.getColumnIndex("lastName"));
                    uname=cursor.getString(cursor.getColumnIndex("userName"));
                    password=cursor.getString(cursor.getColumnIndex("password"));
                    email=cursor.getString(cursor.getColumnIndex("email"));
                    city=cursor.getString(cursor.getColumnIndex("city"));
                    phone=cursor.getString(cursor.getColumnIndex("phoneNumber"));
                    gender=cursor.getString(cursor.getColumnIndex("gender"));
                    //Passsing data to updaet Screen
                    Intent i = new Intent(Home.this, UpdateProfile.class);
                    i.putExtra("fname",fname);
                    i.putExtra("lname",lname);
                    i.putExtra("uname",uname);
                    i.putExtra("password",password);
                    i.putExtra("email",email);
                    i.putExtra("phone",phone);
                    i.putExtra("gender",gender);
                    i.putExtra("city",city);
                    startActivity(i);
                }

            }
        });
    }
}
