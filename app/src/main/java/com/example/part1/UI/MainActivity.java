/**
 *
 *
 * WGU Scheduler App - by Brendan Matthews
 *
 *
 */


package com.example.part1.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import com.example.part1.R;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enterButton(View view) {
        Intent intent= new Intent(MainActivity.this, Terms.class);
        startActivity(intent);
    }
}