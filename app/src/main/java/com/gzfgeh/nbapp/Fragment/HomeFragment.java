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
import com.gzfgeh.nbapp.R;
import com.gzfgeh.nbapp.Utils.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
        initToolBar();
        initTabLayout();
        return view;
    }

    private void initToolBar() {
        toolbar.setTitle(mParam1);
        toolbar.inflateMenu(R.menu.menu_main);
    }

    private void initTabLayout() {
        mTabList = new ArrayList<>();
        mTabList.add("头条");
        mTabList.add("网易新闻");
        mTabList.add("娱乐资讯");

        newsFragmentList = new ArrayList<>();
        newsFragmentList.add(NewsListFragment.newInstance("头条"));
        newsFragmentList.add(NewsListFragment.newInstance("网易新闻"));
        newsFragmentList.add(NewsListFragment.newInstance("娱乐资讯"));

        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.addTab(tabs.newTab().setText(mTabList.get(0)));
        tabs.addTab(tabs.newTab().setText(mTabList.get(1)));
        tabs.addTab(tabs.newTab().setText(mTabList.get(2)));

        NewsFragmentPagerAdapter adapter = new NewsFragmentPagerAdapter(getChildFragmentManager(), mTabList, newsFragmentList);
        viewPager.setAdapter(adapter);
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
