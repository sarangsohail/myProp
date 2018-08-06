package com.example.propertymanagment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.widget.Switch;

class SectionsPagerAdapter extends FragmentPagerAdapter {

     public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    //
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                RequestFragment requestFragment = new RequestFragment();
                return requestFragment;
            case 1:
                ChatFragment chatFragment = new ChatFragment();
                return chatFragment;
            case 2:
                TenantsFragment tenantsFragment = new TenantsFragment();
                return tenantsFragment;

                default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
