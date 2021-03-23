package com.example.restbox.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.restbox.R;

public class juridischeInformatio extends AppCompatActivity {

    TextView auteur, omschrijving, contact, doelstelling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juridische_informatio);

        contact = findViewById(R.id.contact);
        omschrijving = findViewById(R.id.omschrijving);
        doelstelling = findViewById(R.id.doelstelling);
        auteur = findViewById(R.id.auteur);

        auteur.setText("Auteur RestBox App\nAntoine Moghaddar");
        doelstelling.setText("Het idee achet deze app is om gemakkelijk werkgeversverklaringen te kunnen downloaden.\nTijdens deze handelingen zijn de algemene wetten nageleefd.");
        omschrijving.setText("Met deze app kunnen geautomatiseerde werkgeversverklaringen\nworden gedownload. De verklaringen komen na download\nin de downloadfolder te staan.");
        contact.setText("Contact:\nEmail: hi@antoinemoghaddar.com\nGithub: https://github.com/AntoineMoghaddar");

    }
}