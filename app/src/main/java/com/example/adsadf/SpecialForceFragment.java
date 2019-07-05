package com.example.adsadf;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.adsadf.Api.BASE_URL;

public class SpecialForceFragment extends Fragment {

    private SpecialForce specialForce;
    private String forceName;
    private String mUrl;
    private String mDescription;
    private String mName;
    private String mTelephone;

    SpecialForceFragment(String forceName) {
        this.forceName= forceName;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_special_force, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvName= getView().findViewById(R.id.sff_tv_name);
        TextView tvDesc= getView().findViewById(R.id.sff_tv_desc);
        TextView tvUrl= getView().findViewById(R.id.sff_tv_url);
        TextView tvTelephone= getView().findViewById(R.id.sff_tv_tele);

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<SpecialForce> call = api.getSpecialForce(forceName);
        call.enqueue(new Callback<SpecialForce>() {
            @Override
            public void onResponse(@NonNull Call<SpecialForce> call, @NonNull Response<SpecialForce> response) {
                specialForce = response.body();
                if (specialForce!= null) {
                    mDescription= specialForce.getDescription();
                    if(mDescription==null || mDescription.equals(""))
                        mDescription="No Description Found. ";
                    mName= specialForce.getName();
                    mTelephone= specialForce.getTelephone();
                    mUrl= specialForce.getUrl();

                    tvName.setText(mName);
                    tvDesc.setText(mDescription);
                    tvUrl.setText(mUrl);
                    tvTelephone.setText(mTelephone);
                }

                else {
                    Toast.makeText(getActivity(), "Failed to Load",Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }

            }

            @Override
            public void onFailure(@NonNull Call<SpecialForce> call, @NonNull Throwable t) {
                Toast.makeText(getContext(),"Request Failed", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });
    }
}
