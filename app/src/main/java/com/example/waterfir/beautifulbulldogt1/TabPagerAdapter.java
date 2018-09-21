package com.example.waterfir.beautifulbulldogt1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class TabPagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public TabPagerAdapter(FragmentManager fm, int NumOfTabs) {
        //this calls the default constructor we cannot see will need to put this in
        super(fm);
        this.mNumOfTabs = NumOfTabs;

    }

    @Override
    public Fragment getItem(int position){
//this iwll return a specific fragment where getCount will return the num of tabs
        switch (position){
            //bulldogListFrag is set to zero
            case 0:
                BulldogListFragment tab1 = new BulldogListFragment();
                return tab1;
                //rankings is set to 1
            case 1:
                RankingsFragment tab2 = new RankingsFragment();
                return tab2;
            default:
                return null;
        }
    }
    @Override
    public int getCount(){
        return mNumOfTabs;
    }
}
