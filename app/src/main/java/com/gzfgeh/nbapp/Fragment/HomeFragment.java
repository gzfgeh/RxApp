package com.gzfgeh.nbapp.Fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gzfgeh.nbapp.Adapter.NewsFragmentPagerAdapter;
import com.gzfgeh.nbapp.Common.ApiConstants;
import com.gzfgeh.nbapp.R;
import com.gzfgeh.nbapp.Utils.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends BaseLazyFragment {
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private List<String> mTabList;
    private List<Fragment> newsFragmentList;

    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initTabLayout();
        return view;
    }

    private void initTabLayout() {
        mTabList = new ArrayList<>();
        mTabList.add(ApiConstants.GANDK_IO_ANDROID);
        mTabList.add(ApiConstants.GANDK_IO_IOS);
        mTabList.add(ApiConstants.GANDK_IO_GANHUO);

        newsFragmentList = new ArrayList<>();
        newsFragmentList.add(NewsListFragment.newInstance(ApiConstants.GANDK_IO_ANDROID));
        newsFragmentList.add(NewsListFragment.newInstance(ApiConstants.GANDK_IO_IOS));
        newsFragmentList.add(NewsListFragment.newInstance(ApiConstants.GANDK_IO_GANHUO));

        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.addTab(tabs.newTab().setText(mTabList.get(0)));
        tabs.addTab(tabs.newTab().setText(mTabList.get(1)));
        tabs.addTab(tabs.newTab().setText(mTabList.get(2)));

        NewsFragmentPagerAdapter adapter = new NewsFragmentPagerAdapter(getChildFragmentManager(), mTabList, newsFragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        tabs.setupWithViewPager(viewPager);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @OnClick(R.id.fab)
    void fabClick(){
        RxBus.getInstance().post("ToTop");
    }

}
