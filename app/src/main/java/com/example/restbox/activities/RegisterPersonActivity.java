package com.example.restbox.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restbox.R;
import com.example.restbox.model.RestboxModel;

public class RegisterPersonActivity extends AppCompatActivity {

    private Spinner mSpinnerMonths, mSpinnerFunction, mSpinnerYear, mSpinnerDay;
    private Button mAddPerson;
    private TextView mTelText;
    private EditText mName, mTel;
    private final RestboxModel restboxModel;

    public RegisterPersonActivity() {
        restboxModel = RestboxModel.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_person);

        mSpinnerMonths = findViewById(R.id.maand);
        mSpinnerFunction = findViewById(R.id.functieprofiel);
        mSpinnerDay = findViewById(R.id.dag);
        mSpinnerYear = findViewById(R.id.jaar);
        mAddPerson = findViewById(R.id.toevoegenbutton);
        mName = findViewById(R.id.Naam);
        mTel = findViewById(R.id.mTel);
        mTelText = findViewById(R.id.textviewTel);

        Integer[] dagen = new Integer[]
                {
                        1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                        11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                        21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31
                };

        int eindjaar = 2010;
        Integer[] jaren = new Integer[50];
        for (int i = 0; eindjaar > 1960; i++) {
            jaren[i] = eindjaar;
            eindjaar--;
        }

        ArrayAdapter<CharSequence> adapterMaanden = ArrayAdapter.createFromResource(this, R.array.maanden_array, android.R.layout.simple_spinner_dropdown_item);
        adapterMaanden.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapterFuncties = ArrayAdapter.createFromResource(this, R.array.functie_array, android.R.layout.simple_spinner_dropdown_item);
        adapterFuncties.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<Integer> adapterDag = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dagen);
        adapterDag.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<Integer> adapterJaar = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, jaren);
        adapterJaar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerYear.setAdapter(adapterJaar);
        mSpinnerFunction.setAdapter(adapterFuncties);
        mSpinnerMonths.setAdapter(adapterMaanden);
        mSpinnerDay.setAdapter(adapterDag);

        mSpinnerFunction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mSpinnerFunction.getSelectedItem().equals("Bedrijfsleider") || mSpinnerFunction.getSelectedItem().equals("Assistent Bedrijfsleider")) {
                    mTel.setVisibility(View.VISIBLE);
                    mTelText.setVisibility(View.VISIBLE);
                } else {
                    mTel.setVisibility(View.INVISIBLE);
                    mTelText.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mTel.setVisibility(View.INVISIBLE);
                mTelText.setVisibility(View.INVISIBLE);

            }
        });

        mAddPerson.setOnClickListener(v -> {
            if (!mName.getText().toString().isEmpty()) {
                restboxModel.updateFirebase(restboxModel.newPerson(mName.getText().toString(),
                        Integer.parseInt(mSpinnerDay.getSelectedItem().toString()),
                        mSpinnerMonths.getSelectedItem().toString(),
                        Integer.parseInt(mSpinnerYear.getSelectedItem().toString()),
                        mSpinnerFunction.getSelectedItem().toString(),
                        (mSpinnerFunction.getSelectedItem().toString().contains("Bedrijfsleider") ? mTel.getText().toString() : "-1")));

                Toast toast = Toast.makeText(getApplicationContext(), "Persoon " + mName.getText().toString() + " aangemaakt.", Toast.LENGTH_LONG);
                toast.show();
                this.finish();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Vul alle gegevens in.", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }


}