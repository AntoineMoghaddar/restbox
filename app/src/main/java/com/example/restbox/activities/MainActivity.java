package com.example.restbox.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.restbox.R;
import com.example.restbox.model.RestboxModel;


public class MainActivity extends AppCompatActivity {

    Button button,
            button2,
            button3,
            button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, EmployeeListActivity.class);
            startActivity(i);
        });

        button2.setOnClickListener(v -> {
            RestboxModel.getInstance().refreshList();
        });

    }
}