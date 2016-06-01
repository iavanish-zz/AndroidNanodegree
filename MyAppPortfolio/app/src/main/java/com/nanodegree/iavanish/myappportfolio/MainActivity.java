package com.nanodegree.iavanish.myappportfolio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Button project1;
    private Button project2;
    private Button project3;
    private Button project4;
    private Button project5;
    private Button project6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        project1 = (Button)findViewById(R.id.project1);
        project2 = (Button)findViewById(R.id.project2);
        project3 = (Button)findViewById(R.id.project3);
        project4 = (Button)findViewById(R.id.project4);
        project5 = (Button)findViewById(R.id.project5);
        project6 = (Button)findViewById(R.id.project6);

        project1.setOnClickListener(this);
        project2.setOnClickListener(this);
        project3.setOnClickListener(this);
        project4.setOnClickListener(this);
        project5.setOnClickListener(this);
        project6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.project1) {
            Toast.makeText(getApplicationContext(), "This button will launch my POPULAR MOVIES app!", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId() == R.id.project2) {
            Toast.makeText(getApplicationContext(), "This button will launch my STOCK HAWK app!", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId() == R.id.project3) {
            Toast.makeText(getApplicationContext(), "This button will launch my BUILD IT BIGGER app!", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId() == R.id.project4) {
            Toast.makeText(getApplicationContext(), "This button will launch my MAKE YOUR APP MATERIAL app!", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId() == R.id.project5) {
            Toast.makeText(getApplicationContext(), "This button will launch my GO UBIQUITOUS app!", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId() == R.id.project6) {
            Toast.makeText(getApplicationContext(), "This button will launch my CAPSTONE app!", Toast.LENGTH_SHORT).show();
        }
    }
}
