package com.gzfgeh.nbapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by guzhenfu on 2016/10/25 13:41.
 */

public class NewsFragmentPagerAdapter extends FragmentPagerAdapter {

    private final List<String> mTitles;
    private List<Fragment> mNewsFragmentList = new ArrayList<>();

    public NewsFragmentPagerAdapter(FragmentManager fm, List<String> titles, List<Fragment> newsFragmentList) {
        super(fm);
        mTitles = titles;
        mNewsFragmentList = newsFragmentList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mNewsFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mNewsFragmentList.size();
    }

}
