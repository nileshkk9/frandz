package com.example.nileshkumar.frandz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void needToRegister(View view) {
        Intent in =new Intent(this,RegisterActivity.class);
        startActivity(in);
    }

    public void login(View view) {
        Intent in =new Intent(this,LoginActivity.class);
        startActivity(in);

    }
}
