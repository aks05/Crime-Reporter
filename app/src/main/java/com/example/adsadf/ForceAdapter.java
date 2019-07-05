package com.example.adsadf;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class ForceAdapter extends RecyclerView.Adapter<ForceAdapter.ForceAdapterViewHolder>{
    private ArrayList<String> mForceData;
    private ForceAdapterOnClickHandler mClickHandler;
    private ForceAdapterOnTouchHandler mTouchHandler;
    private Context context;
    private ArrayList<Crime> crimes;
    private ArrayList<Forces> forces;

    interface ForceAdapterOnClickHandler {
        void onClick(int position);
    }

    interface ForceAdapterOnTouchHandler {
        void onTouch(int position);
    }

    ForceAdapter(ForceAdapterOnClickHandler clickHandler,
                 ForceAdapterOnTouchHandler touchHandler, Context context) {
        mClickHandler = clickHandler;
        mTouchHandler = touchHandler;
        this.context= context;
    }

    class ForceAdapterViewHolder extends RecyclerView.ViewHolder
            implements View.OnTouchListener{
        final TextView mForceTextView;
        private GestureDetectorCompat mDetector;

        ForceAdapterViewHolder(View view) {
            super(view);
            mForceTextView =  view.findViewById(R.id.tv_force_data);
            this.mDetector = new GestureDetectorCompat
                    (ForceAdapter.this.context, new MyGestureListener());
            view.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
                this.mDetector.onTouchEvent(event);
                return true;
            }

            class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
                private static final int SWIPE_THRESHOLD = 50;
                private static final int SWIPE_VELOCITY_THRESHOLD = 50;

                @Override
                public boolean onDown(MotionEvent event) {
                    return true;
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2,
                                       float velocityX, float velocityY) {
                    try {
                        float diffY = e2.getY() - e1.getY();
                        float diffX = e2.getX() - e1.getX();
                        if (Math.abs(diffX) > Math.abs(diffY)) {
                            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                                int position= getAdapterPosition();
                                mTouchHandler.onTouch(position);
                            }
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    return false;
                }

                @Override
                public boolean onSingleTapConfirmed(MotionEvent event) {
                    int position= getAdapterPosition();
                    mClickHandler.onClick(position);
                    return false;
                }
                @Override
                public void onLongPress(MotionEvent event) {
                    int position= getAdapterPosition();
                    mTouchHandler.onTouch(position);
                }
            }
    }

    @NonNull
    @Override
    public ForceAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.force_list_item, viewGroup, false);
        return new ForceAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForceAdapterViewHolder forceAdapterViewHolder, int position) {
        String forceName = mForceData.get(position);
        forceAdapterViewHolder.mForceTextView.setText(forceName);
    }

    @Override
    public int getItemCount() {
        if (null == mForceData) return 0;
        return mForceData.size();
    }

    void setForceData(ArrayList<Forces> forceData) {
        mForceData= new ArrayList<>();
        this.forces= forceData;
        for (int i= 0; i< forceData.size(); i++)
            mForceData.add(forceData.get(i).getName());
        notifyDataSetChanged();
    }

    void setCrimeData (ArrayList<Crime> crimes) {
        mForceData= new ArrayList<>();
        this.crimes= crimes;
        if (crimes!=null && crimes.size()!=0) {
            for (int i= 0; i< crimes.size(); i++)
                mForceData.add(crimes.get(i).getCategory());
            notifyDataSetChanged();
        }
    }

    void setHandler(ForceAdapter.ForceAdapterOnClickHandler mClickHandler,
                    ForceAdapterOnTouchHandler mTouchHandler) {
        this.mClickHandler= mClickHandler;
        this.mTouchHandler= mTouchHandler;
    }

    ArrayList<Crime> getCrimes () {
        return crimes;
    }

    ArrayList<Forces> getForces () {
        return forces;
    }
}
