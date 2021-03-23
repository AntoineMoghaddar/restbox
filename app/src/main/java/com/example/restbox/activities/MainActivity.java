package com.example.restbox.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.restbox.R;
import com.example.restbox.model.RestboxModel;


public class MainActivity extends AppCompatActivity {

    Button button,
            button2,
            button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button4 = findViewById(R.id.button4);

        button.setClickable(false);
        button.setEnabled(false);
        button.setBackgroundColor(ContextCompat.getColor(this, R.color.common_google_signin_btn_text_dark_disabled));

        button2.setOnClickListener(v -> {
            button.setClickable(true);
            button.setEnabled(true);
            button.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.purple_500));
            RestboxModel.getInstance();
        });

        button.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, EmployeeListActivity.class);
            startActivity(i);
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, juridischeInformatio.class);
                startActivity(i);
            }
        });


    }
}