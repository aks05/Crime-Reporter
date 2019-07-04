package com.example.adsadf;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity
        implements ForceAdapter.ForceAdapterOnClickHandler ,
        ForceAdapter.ForceAdapterOnTouchHandler {
    private ForceFragment forceFragment;
    private CrimesFragment crimesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.am_pager);

        TabLayout tabLayout= findViewById(R.id.am_tl);
        tabLayout.setupWithViewPager(viewPager);

        FragmentManager fragmentManager= getSupportFragmentManager();
        viewPager.setAdapter(new PagerAdapter(fragmentManager));
    }

    @Override
    public void onClick(int position) {
        String forceId= forceFragment.getForceId(position);
        Intent intent= new Intent(this, SpecialForceActivity.class);
        intent.putExtra("forceId", forceId);
        startActivity(intent);
    }

    @Override
    public void onTouch(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Selected Item");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                CrimeDatabase crimeDatabase= new CrimeDatabase(MainActivity.this);
                crimeDatabase.deleteData(crimesFragment.getCrimeString(position));
                crimesFragment.refresh();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    class PagerAdapter extends FragmentPagerAdapter{

        PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0) {
                crimesFragment= new CrimesFragment(MainActivity.this);
                return crimesFragment;
            }
            if(position==1) {
             forceFragment= new ForceFragment(MainActivity.this);
             return forceFragment;
            }
            if(position==2)
                return new SearchCrimeFragment();
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return getString(R.string.fvf_title);
                case 1: return getString(R.string.ff_title);
                case 2: return getString(R.string.scf_title);
                default: return null;
            }
        }
    }
}
