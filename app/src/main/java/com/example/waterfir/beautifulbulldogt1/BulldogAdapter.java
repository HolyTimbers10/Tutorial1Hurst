package com.example.waterfir.beautifulbulldogt1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class BulldogAdapter extends RecyclerView.Adapter<BulldogAdapter.BulldogViewHolder>{

    //creating vars
    private Context context;
    private RealmResults<Bulldog> bulldogs;
    private RecyclerViewClickListener mListener;

    //creates our constructors class to set vars
    public BulldogAdapter(Context context, RealmResults<Bulldog> dataSet, RecyclerViewClickListener clickListener){
        this.context = context;
        this.bulldogs = dataSet;
        this.mListener = clickListener;
    }
//defines the bulldogViewHolder for our adapter this is how we reference our ui elements in our activities and gragments
    public static class BulldogViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nameView;
        public TextView ageView;
        private RecyclerViewClickListener mListener;


        public BulldogViewHolder(View v,RecyclerViewClickListener listener){
            //the super calls a default constructor we cant see
            super(v);
                nameView = v.findViewById(R.id.name_view);
                ageView = v.findViewById(R.id.age_view);
                mListener = listener;
                v.setOnClickListener(this);
        }

        @Override
         public void onClick(View view){
         mListener.onClick(view, getAdapterPosition());
        }
     }
//this will implement the required functions to be a subclass of RecyclerView this will return the size
    //of the array
     public int getItemCount(){
            return bulldogs.size();
     }
//this inflate will inflate a cell to be used as our layout for ea indiv item in our list
    //this will set the adapter up for clicks
     @Override
        public BulldogAdapter.BulldogViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            //create a new view holder with an inflated view and return the viewholder

         View v = (View)LayoutInflater.from(parent.getContext())
                 .inflate(R.layout.bulldog_cell, parent, false);
         BulldogViewHolder vh = new BulldogViewHolder(v, mListener);
         return vh;
     }
//this iwll tie our viewholder to data
     @Override
        public void onBindViewHolder(BulldogViewHolder holder, int position){
            holder.nameView.setText(bulldogs.get(position).getName());
            holder.ageView.setText(bulldogs.get(position).getAge());
     }
    }

