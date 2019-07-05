package com.example.adsadf;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.adsadf.Api.BASE_URL;

public class ForceFragment extends Fragment implements ForceAdapter.ForceAdapterOnTouchHandler {
    private ForceAdapter.ForceAdapterOnClickHandler mClickHandler;
    private ArrayList<Forces> forces;
    private ForceAdapter mForceAdapter;
    private EditText editText;

    public ForceFragment() {
        //empty constructor required
    }

    ForceFragment(ForceAdapter.ForceAdapterOnClickHandler mClickHandler) {
        this.mClickHandler= mClickHandler;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_force_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView mRecyclerView = getView().findViewById(R.id.ff_rv);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mForceAdapter = new ForceAdapter(mClickHandler, this, getContext());
        mRecyclerView.setAdapter(mForceAdapter);

        editText= getView().findViewById(R.id.editTextSearchForce);

        load();
    }

    @Override
    public void onStart(){
        super.onStart();
        if (forces== null || forces.size()==0)
            load();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (forces == null || forces.size() == 0)
            load();
    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<Forces> filterdNames = new ArrayList<>();

        //looping through existing elements
        if (forces!= null){
            for (Forces s : forces) {
                //if the existing elements contains the search input
                if (s.getName().toLowerCase().contains(text.toLowerCase())) {
                    //adding the element to filtered list
                    filterdNames.add(s);
                }
            }
            mForceAdapter.setForceData(filterdNames);
        }
    }

    String getForceId(int position) {
        return mForceAdapter.getForces().get(position).getId();
    }

    @Override
    public void onTouch(int position) {
        //do nothing
    }

    private void load() {
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<ArrayList<Forces>> call = api.getForces();
        call.enqueue(new Callback<ArrayList<Forces>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Forces>> call,
                                   @NonNull Response<ArrayList<Forces>> response) {
                forces = response.body();
                if (forces != null && forces.size()!=0) {
                    mForceAdapter.setForceData(forces);
                }
                else {
                    Toast.makeText(getActivity(), "No Forces Found", Toast.LENGTH_SHORT)
                            .show();
                    editText.setEnabled(false);
                    if(getActivity().getClass().equals(UKForcesActivity.class))
                        getActivity().finish();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Forces>> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(),
                        "Request Failed\nCheck your Internet Connection",
                        Toast.LENGTH_SHORT).show();
                editText.setEnabled(false);
                if(getActivity().getClass().equals(UKForcesActivity.class))
                    getActivity().finish();
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString());
            }
        });
    }
}
