package com.example.android.karta.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.android.karta.Fragments.Tab1Fragment;
import com.example.android.karta.Fragments.Tab2Fragment;
import com.example.android.karta.Fragments.Tab3Fragment;
import com.example.android.karta.Fragments.Tab4Fragment;

/**
 * Created by Root on 31/07/2017.
 */

public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int numberOfTabs;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabs) {
        super(fm);

        this.numberOfTabs = tabs;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                return new Tab1Fragment();
            case 1:
                return new Tab2Fragment();
            case 2:
                return new Tab3Fragment();
            case 3:
                return new Tab4Fragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
