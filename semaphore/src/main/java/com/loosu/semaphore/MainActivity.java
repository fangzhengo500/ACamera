package com.loosu.semaphore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.loosu.semaphore.demo1.Demo1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jump2Demo1(View view) {
        Intent intent = new Intent(this, Demo1.class);
        startActivity(intent);
    }
}
