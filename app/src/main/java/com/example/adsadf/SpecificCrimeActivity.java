package com.example.adsadf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class SpecificCrimeActivity extends AppCompatActivity {
    String[] crimeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_crime);

        Intent intent= getIntent();
        crimeData= intent.getStringArrayExtra("crime");

        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.asc_ll, new SpecificCrimeFragment(crimeData));
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.asc_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        if (item.getItemId()== R.id.asc_show_map) {
            if (crimeData[4].equals("No Latitude Found") ||
                    crimeData[5].equals("No Longitude Found")) {
                Toast.makeText(this, "No Location Found", Toast.LENGTH_SHORT).show();
            }

            else {
                Intent intent= new Intent(android.content.Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:"+crimeData[4]+","+crimeData[5]));
                Intent chooser= Intent.createChooser(intent, "Launch Maps");
                startActivity(chooser);
            }
            return true;
        }
        else return super.onOptionsItemSelected(item);
    }
}
