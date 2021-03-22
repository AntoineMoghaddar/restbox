package com.example.restbox.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.restbox.R;
import com.example.restbox.adapter.PersonAdapter;
import com.example.restbox.model.RestboxModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EmployeeListActivity extends AppCompatActivity {
    RestboxModel model = RestboxModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_employee_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView mListView = findViewById(R.id.mList);
        Button export = findViewById(R.id.button5);

        PersonAdapter adapter = new PersonAdapter(this);
        mListView.setAdapter(adapter);

        /*
          Version 2.0 Dev
          TODO detailed activity per employee
         */
//        mListView.setOnItemClickListener((parent, view, position, id) -> {
//            Intent i = new Intent(EmployeeListActivity.this, PersonDetailActivity.class);
//            startActivity(i);
//        });

        export.setOnClickListener(v -> {
            if (model.enableExport()) {
                Intent intent = new Intent(EmployeeListActivity.this, addShiftActivity.class);
                startActivity(intent);

            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Voeg eerst mensen toe", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent i = new Intent(EmployeeListActivity.this, RegisterPersonActivity.class);
            startActivity(i);
        });
    }
}