package com.example.resumebuilderapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String logginInUserName;
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
                    Intent newscreen=new Intent(MainActivity.this,Home.class);
                    newscreen.putExtra("LoggedInUserName",logginInUserName);
                    startActivity(newscreen);
                }
                else
                    Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean signIn(){
        String password;
        logginInUserName = ((Editable)((EditText) findViewById(R.id.login)).getText()).toString().trim();
        password = ((Editable)((EditText) findViewById(R.id.password)).getText()).toString().trim();
        if(logginInUserName.isEmpty()||password.isEmpty()) {
            Toast.makeText(this, "Not All Fields Filled", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            DBHelper db=new DBHelper(this);
            Cursor cursor=db.getReadableDatabase().query("Users",null,"Username=? AND password=?",new String[]{logginInUserName,password},null,null,null);
            if(cursor.moveToFirst())
                return true;
            return false;
        }
    }
}
