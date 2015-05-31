package com.example.praveen.routofy_test;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;

/**
 * Created by praveen on 5/31/2015.
 */
public class TabPageAdapter extends FragmentStatePagerAdapter {
     public TabPageAdapter (FragmentManager fm) {
         super(fm);
     }

    @Override
    public ListFragment getItem(int i) {
        switch (i) {
            case 0:
                //Fragement for user id1 Tab
                return new UserId1();
            case 1:
                //Fragment for user id2 Tab
                return new UserId2();
            case 2:
                //Fragment for user id2 Tab
                return new UserId3();
        }
        return null;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 3; //No of Tabs
    }

}
