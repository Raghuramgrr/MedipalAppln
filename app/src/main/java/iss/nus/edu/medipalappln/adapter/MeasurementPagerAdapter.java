package iss.nus.edu.medipalappln.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import iss.nus.edu.medipalappln.fragment.MeasureBloodPressureFragment;

public class MeasurementPagerAdapter extends FragmentStatePagerAdapter {

    private int numTabs;
    String datePattern = "yyyy-MM-dd";

    public MeasurementPagerAdapter(FragmentManager fm, int numTabs) {
        super(fm);
        this.numTabs = numTabs;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment childFragment = null;

        switch (position) {
            case 0:
                childFragment = new MeasureBloodPressureFragment();
            case 1:
                childFragment = new MeasureBloodPressureFragment();
            case 2:
                childFragment = new MeasureBloodPressureFragment();
            case 3:
                childFragment = new MeasureBloodPressureFragment();
            default:
                childFragment = new MeasureBloodPressureFragment();
        }

        return childFragment;
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
