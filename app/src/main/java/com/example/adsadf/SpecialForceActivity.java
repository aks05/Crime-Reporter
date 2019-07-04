package com.example.adsadf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

public class SpecialForceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_force);

        Intent intent= getIntent();
        String forceId= intent.getStringExtra("forceId");

        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.af_ll, new SpecialForceFragment(forceId));
        transaction.commit();
    }
}
