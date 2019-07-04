package com.example.adsadf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

public class UKForcesActivity extends AppCompatActivity implements ForceAdapter.ForceAdapterOnClickHandler {
    private ForceFragment forceFragment;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ukforces);

        Intent intent= getIntent();
        bundle= intent.getExtras();


        forceFragment= new ForceFragment(this);

        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.auf_ll, forceFragment);
        transaction.commit();
    }

    @Override
    public void onClick(int position) {
        String  forceId= forceFragment.getForceId(position);
        Intent intent= new Intent(this, CrimesActivity.class);
        bundle.putString("forceId", forceId);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
