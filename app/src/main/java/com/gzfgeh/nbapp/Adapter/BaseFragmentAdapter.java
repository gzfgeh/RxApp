package com.gzfgeh.nbapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gzfgeh.nbapp.Interface.LauncherBaseFragment;

import java.util.List;

/**
 * Created by guzhenfu on 15/9/22.
 */
public class BaseFragmentAdapter extends FragmentStatePagerAdapter {
    private List<LauncherBaseFragment> list;

    public BaseFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public BaseFragmentAdapter(FragmentManager fm, List<LauncherBaseFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
