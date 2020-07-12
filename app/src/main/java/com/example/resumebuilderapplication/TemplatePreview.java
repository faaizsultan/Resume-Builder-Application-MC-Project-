package com.example.resumebuilderapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TemplatePreview extends AppCompatActivity {
    Button template1,template2,template3,template4;
    String loggedInUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.templatepreview);
//getting UserName to show relevant Data..
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.loggedInUserName = extras.getString("loggedInUserName");
        }
//template1
        template1 = (Button) findViewById(R.id.template1);
        template1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TemplatePreview.this, TemplatePlacer.class);
                intent.putExtra("loggedInUserName",loggedInUserName);
                intent.putExtra("templateNo","1");
                startActivity(intent);
            }
        });
        //template2
        template2 = (Button) findViewById(R.id.template2);
        template2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TemplatePreview.this, TemplatePlacer.class);
                intent.putExtra("loggedInUserName",loggedInUserName);
                intent.putExtra("templateNo","2");
                startActivity(intent);
            }
        });
        //template3
        template3 = (Button) findViewById(R.id.template3);
        template3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TemplatePreview.this, TemplatePlacer.class);
                intent.putExtra("loggedInUserName",loggedInUserName);
                intent.putExtra("templateNo","3");
                startActivity(intent);
            }
        });

    }
}
