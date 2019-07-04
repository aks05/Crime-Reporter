package com.example.adsadf;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchCrimeFragment extends Fragment implements View.OnClickListener {
    private EditText etLat, etLong, etMonth;

    public SearchCrimeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_crime, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etLat= getView().findViewById(R.id.etLat);
        etLong= getView().findViewById(R.id.etLong);
        etMonth= getView().findViewById(R.id.etMonth);
        Button btnSearch= getView().findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String mLat= etLat.getText().toString();
        String mLong= etLong.getText().toString();
        String mMonth= etMonth.getText().toString();
        if(mMonth.length()!=0) {
            Bundle bundle= new Bundle();
            Intent intent= new Intent(getActivity(), CrimesActivity.class);
            bundle.putString("Month", mMonth);
            if(mLat.length()!=0 && mLong.length()!=0) {
                bundle.putString("Latitude", mLat);
                bundle.putString("Longitude", mLong);
            }
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else Toast.makeText(getContext(),"Enter valid Month", Toast.LENGTH_SHORT).show();
    }
}
