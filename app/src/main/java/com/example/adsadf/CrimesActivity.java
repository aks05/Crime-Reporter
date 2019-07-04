package com.example.adsadf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class CrimesActivity extends AppCompatActivity implements
        ForceAdapter.ForceAdapterOnTouchHandler {
    CrimesFragment crimesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crimes);

        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();

        Intent intent= getIntent();
        Bundle bundle= intent.getExtras();
        String mMonth= bundle.getString("Month");
        if (bundle.containsKey("Latitude")) {
            String latitude= bundle.getString("Latitude");
            String longitude= bundle.getString("Longitude");
            crimesFragment= new CrimesFragment(mMonth, latitude, longitude, this);
            transaction.add(R.id.ac_ll,crimesFragment);
            transaction.commit();
        }
        else if (bundle.containsKey("forceId")) {
            String force= bundle.getString("forceId");
            crimesFragment= new CrimesFragment(mMonth, force, this);
            transaction.add(R.id.ac_ll, crimesFragment);
            transaction.commit();
        }
        else {
            Intent mIntent= new Intent(this, UKForcesActivity.class);
            mIntent.putExtras(bundle);
            startActivity(mIntent);
            finish();
        }
    }

    @Override
    public void onTouch(int position) {
        Log.i("Ayush", "Database onTouch is called");
        CrimeDatabase crimeDatabase= new CrimeDatabase(this);
        if (crimeDatabase.addData(crimesFragment.getCrimeString(position))) {
            Toast.makeText(this, "Added to Favourites", Toast.LENGTH_SHORT).show();
            Log.i("Ayush", "if of onTouch is called");
        }
        else {
            Toast.makeText
                    (this, "Failed to Add to Favourites", Toast.LENGTH_SHORT).show();
            Log.i("Ayush", "else of onTouch is called");
        }
    }
}
