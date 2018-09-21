package com.example.waterfir.beautifulbulldogt1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
//the main activity holds our fragments we created for our tabs( Rankings and bulldogs)
    //the main activity also serves as the main controller for the app upon successful login

    public User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Realm realm = Realm.getDefaultInstance();
        String username = (String) getIntent().getStringExtra("username");
        user = realm.where(User.class).equalTo("username", username).findFirst();

        //automatically generated floating action button code
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //this will allow the floating button to see our current location and know when clicked we
                //want to go to the bulldog activity class code.
                Intent intent = new Intent(view.getContext(), NewBulldogActivity.class);
                startActivity(intent);

               // Realm realm = Realm.getDefaultInstance();
                //String username = (String) getIntent().getStringExtra("username");
               // user = realm.where(User.class).equalTo("username", username).findFirst();

            }
        });
        //adding tabs to the layout the findView will get access to the tabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        //add tab command adds new tabs to our TabLayout
        tabLayout.addTab(tabLayout.newTab().setText("Bulldogs"));
        tabLayout.addTab(tabLayout.newTab().setText("Rankings"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //this will tie our tabs to our viewPager with an Adapter
        final ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        final TabPagerAdapter adapter = new TabPagerAdapter
                (getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        //these deal with page change and tab selected listeners this is the location it needs to be
        //at below the set adapter
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
             public void onTabSelected(TabLayout.Tab tab){
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
             public void onTabUnselected(TabLayout.Tab tab){
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab){
            }
        });
    }
}


