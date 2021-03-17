package com.example.restbox.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.restbox.model.RestboxModel;
import com.example.restbox.objects.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.restbox.R;

public class EmployeeListActivity extends AppCompatActivity {

    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = (ListView) findViewById(R.id.mList);

        ArrayAdapter<Person> arrayAdapter = new ArrayAdapter<Person>(
                this,
                android.R.layout.simple_list_item_1,
                RestboxModel.getInstance().getPeople());

        mListView.setAdapter(arrayAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Registering new Employee", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            Intent i = new Intent(EmployeeListActivity.this, RegisterPersonActivity.class);
            startActivity(i);
        });
    }
}