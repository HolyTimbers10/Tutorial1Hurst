package com.example.waterfir.beautifulbulldogt1;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.io.Serializable;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class BulldogListFragment extends Fragment {

    private RecyclerView bulldogList;//this var is for the recycler view
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter bulldogAdapter;

    public BulldogListFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bulldog_list,container, false);

        //this will querry our realm for all bulldogs
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Bulldog> bulldogs = realm.where(Bulldog.class).findAll();

        Log.v("count", String.valueOf(bulldogs.size()));

        //this is adding a reference to the recycler view so we can find it
        bulldogList = (RecyclerView)view.findViewById(R.id.bulldog_list);

        layoutManager = new LinearLayoutManager(getContext());
        bulldogList.setLayoutManager(layoutManager);

        final MainActivity mainActivity = (MainActivity) this.getActivity();
        //this will pass the clicklistener into the adapter

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {

            @Override
            public void onClick(View view, int position) {

                Bulldog bulldog = (Bulldog) bulldogs.get(position);
                //this will pass the bulldog to the next activity
                Intent intent = new Intent(view.getContext(), BulldogActivity.class);
                intent.putExtra("username", mainActivity.user.getUsername());
                intent.putExtra("bulldog",bulldog.getId());
                startActivity(intent);
            }
        };

        bulldogAdapter = new BulldogAdapter(getContext(), bulldogs, listener);
        bulldogList.setAdapter(bulldogAdapter);
//bulldogs.add(bulldog1); will create an adapter inside the bulldog fragment
        return view;

    }

}
