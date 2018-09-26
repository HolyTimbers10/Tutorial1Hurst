package com.example.waterfir.beautifulbulldogt1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class RankingsAdapter extends RecyclerView.Adapter<RankingsAdapter.VoteViewHolder> {
    private Context context;
    private RealmResults<Vote> votes;
    private RecyclerViewClickListener mListener;

    public RankingsAdapter(Context context, RealmResults<Vote> dataSet){
        this.context = context;
        this.votes = dataSet;
    }
    public static class VoteViewHolder extends RecyclerView.ViewHolder{
        public TextView dogNameView;
        public TextView voterNameView;
        public ImageView dogImageView;
        public TextView ratingView;
        //public TextView user;
        public User user;



        private RecyclerViewClickListener mListener;
        public VoteViewHolder(View v, RecyclerViewClickListener listener){
            super(v);
            dogNameView = v.findViewById(R.id.dog_name);
//            voterNameView = v.findViewById(R.id.vote_list);
            dogImageView = v.findViewById(R.id.imageView);
            ratingView = v.findViewById(R.id.vote_count);
        }
    }
    @Override
    public int getItemCount(){return votes.size();}

    @Override
    public RankingsAdapter.VoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        //create new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vote_cell, parent, false);
        VoteViewHolder vh = new VoteViewHolder(v, mListener);
        return vh;
    }
    @Override
    public void onBindViewHolder(VoteViewHolder holder, int position){
        holder.dogNameView.setText(votes.get(position).getBulldog().getName());
//        holder.voterNameView.setText(votes.get(position).getOwner().getUsername());
        holder.ratingView.setText(String.valueOf(votes.get(position).getRating()));

        if(votes.get(position).getBulldog().getImage()!=null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(votes.get(position).getBulldog().getImage(), 0, votes.get(position)
            .getBulldog().getImage().length);
            holder.dogImageView.setImageBitmap(bmp);
        }
    }
}



