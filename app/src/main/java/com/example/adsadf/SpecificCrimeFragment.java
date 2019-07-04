package com.example.adsadf;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SpecificCrimeFragment extends Fragment {
    private String[] crimeData;

    SpecificCrimeFragment(String[] crimeData) {
        this.crimeData= crimeData;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specific_crime, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        TextView[] textView= {getView().findViewById(R.id.scf_tv_category),
                getView().findViewById(R.id.scf_tv_month),
                getView().findViewById(R.id.scf_tv_location_type),
                getView().findViewById(R.id.scf_tv_location_subtype),
                getView().findViewById(R.id.scf_tv_latitude),
                getView().findViewById(R.id.scf_tv_longitude),
                getView().findViewById(R.id.scf_tv_street),
                getView().findViewById(R.id.scf_tv_context),
                getView().findViewById(R.id.scf_tv_outcome_status),
                getView().findViewById(R.id.scf_tv_date)};

        for (int i= 0; i< 10; i++){
            textView[i].setText(crimeData[i]);
        }
    }
}
