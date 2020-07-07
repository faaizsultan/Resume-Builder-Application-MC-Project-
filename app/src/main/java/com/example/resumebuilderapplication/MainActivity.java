package com.example.resumebuilderapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button signUpbtn = (Button) findViewById(R.id.signUpbtnFromSignIn);
        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, signUp.class);
                startActivity(intent);
            }
        });
        final Button signIntbn = (Button) findViewById(R.id.signIn);
        signIntbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(signIn())
                {
                    Toast.makeText(MainActivity.this, "Sign In Succcessfull", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this, "OOPS ERRORs", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean signIn(){
        String uname,password;
        uname = ((Editable)((EditText) findViewById(R.id.login)).getText()).toString().trim();
        password = ((Editable)((EditText) findViewById(R.id.password)).getText()).toString().trim();
        if(uname.isEmpty()||password.isEmpty()) {
            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            DBHelper db=new DBHelper(this);
            Cursor cursor=db.getReadableDatabase().query("Users",null,"Username=? AND password=?",new String[]{uname,password},null,null,null);
            if(cursor.moveToFirst()) {
                Toast.makeText(this,"SuccessFull Login!",Toast.LENGTH_SHORT).show();
                return true;
            }
            else {
                Toast.makeText(this, "Not A Registered User", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }
}
