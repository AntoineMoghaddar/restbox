package com.example.restbox.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restbox.R;
import com.example.restbox.adapter.ShiftAdapter;
import com.example.restbox.model.PDFModule;
import com.example.restbox.model.RestboxModel;
import com.example.restbox.objects.Manager;
import com.example.restbox.objects.Person;

import java.util.ArrayList;

public class addShiftActivity extends AppCompatActivity {

    private final RestboxModel model = RestboxModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shift);

        ListView mListview = findViewById(R.id.exportList);

        ShiftAdapter adapter = new ShiftAdapter(this);
        mListview.setAdapter(adapter);

        Spinner mSpinnerMonths = findViewById(R.id.werkmaand);
        Spinner mSpinnerManager = findViewById(R.id.managers);
        Spinner mSpinnerDay = findViewById(R.id.werkdag);
        Spinner mSpinnerYear = findViewById(R.id.werkjaar);
        Button mExportButton = findViewById(R.id.exporteerbutton);

        Integer[] dagen = new Integer[]
                {
                        1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                        11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                        21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31
                };

        int eindjaar = 2021;
        Integer[] jaren = new Integer[10];
        for (int i = 0; eindjaar > 2011; i++) {
            jaren[i] = eindjaar;
            eindjaar--;
        }

        ArrayList<Person> managers = model.getManagers();
        ArrayList<String> managerNames = new ArrayList<>();
        for (Person p : managers) {
            managerNames.add(p.getName());
        }

        ArrayAdapter<CharSequence> adapterMaanden = ArrayAdapter.createFromResource(this, R.array.maanden_array, android.R.layout.simple_spinner_dropdown_item);
        adapterMaanden.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapterFuncties = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, managerNames);
        adapterFuncties.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<Integer> adapterDag = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dagen);
        adapterDag.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<Integer> adapterJaar = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, jaren);
        adapterJaar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerYear.setAdapter(adapterJaar);
        mSpinnerManager.setAdapter(adapterFuncties);
        mSpinnerMonths.setAdapter(adapterMaanden);
        mSpinnerDay.setAdapter(adapterDag);

        mExportButton.setOnClickListener(v -> {
            PDFModule p = new PDFModule(getApplicationContext());
            String dateformatting = "" + mSpinnerDay.getSelectedItem().toString() + "-"
                    + mSpinnerMonths.getSelectedItem().toString() + "-"
                    + mSpinnerYear.getSelectedItem().toString();

            if (managers.size() > 0) {
                for (Person q : managers) {
                    if (mSpinnerManager.getSelectedItem().toString().equals(q.getName())) {
                        if (p.fillPDF((Manager) q, dateformatting)) {
                            finish();
                        }
                    }
                }
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Voeg managers toe.", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        model.emptyQueue();
        this.finish();
    }
}