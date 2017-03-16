package iss.nus.edu.medipalappln.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numTabs;
    String datePattern = "yyyy-MM-dd";

    public PagerAdapter(FragmentManager fm, int numTabs) {
        super(fm);
        this.numTabs = numTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                //MeasureBloodPressureFragment bloodPressureFragment = new MeasureBloodPressureFragment();
                //return bloodPressureFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
