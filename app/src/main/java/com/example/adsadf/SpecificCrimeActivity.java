package com.example.adsadf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

public class SpecificCrimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_crime);

        Intent intent= getIntent();
        String[] crimeData= intent.getStringArrayExtra("crime");

        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.asc_ll, new SpecificCrimeFragment(crimeData));
        transaction.commit();
    }
}
