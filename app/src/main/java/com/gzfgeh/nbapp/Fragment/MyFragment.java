package com.gzfgeh.nbapp.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.bumptech.glide.Glide;
import com.gzfgeh.nbapp.Activity.AboutActivity;
import com.gzfgeh.nbapp.Common.Contants;
import com.gzfgeh.nbapp.R;
import com.gzfgeh.nbapp.Utils.RxBus;
import com.gzfgeh.nbapp.Utils.ShareUtils;
import com.gzfgeh.nbapp.Utils.Utils;
import com.gzfgeh.pullToZoom.PullToZoomScrollViewEx;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends BaseFragment {
    @BindView(R.id.scroll_view)
    PullToZoomScrollViewEx scrollView;

    private static final String ARG_PARAM1 = "param1";
    protected String mParam1;

    private ImageView userIcon;
    private RelativeLayout shareLayout, historyLayout, nightLayout, commonLayout;
    private RelativeLayout aboutLayout, versionLayout, zanLayout, editLayout;
    private SwitchCompat switchBtn;

    public static MyFragment newInstance(String param1) {
        MyFragment fragment = new MyFragment();
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
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);

        scrollView.setAllView(R.layout.pull_to_zoom_header, R.layout.pull_to_zoom_view, R.layout.pull_to_zoom_content, 0.4F);
        userIcon = (ImageView) scrollView.getRootView().findViewById(R.id.user_icon);
        Glide.with(this).load(R.mipmap.ic_launcher).bitmapTransform(new CropCircleTransformation(getActivity()))
                .into(userIcon);

        shareLayout = (RelativeLayout) scrollView.getRootView().findViewById(R.id.share_layout);
        shareLayout.setOnClickListener(view1 -> Utils.shareText(getContext(), "nihao"));

        historyLayout = (RelativeLayout) scrollView.getRootView().findViewById(R.id.history_layout);
        historyLayout.setOnClickListener(view1 -> {});

        switchBtn = (SwitchCompat) scrollView.getRootView().findViewById(R.id.switch_id);
        switchBtn.setOnCheckedChangeListener((compoundButton, b) -> {
            ShareUtils.putValue(Contants.NIGHT_THEME_MODE, b);
            //RxBus.getInstance().post("night");
        });

        commonLayout = (RelativeLayout) scrollView.getRootView().findViewById(R.id.common_settings_layout);
        commonLayout.setOnClickListener(view1 -> {});

        aboutLayout = (RelativeLayout) scrollView.getRootView().findViewById(R.id.about_layout);
        aboutLayout.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), AboutActivity.class));
        });

        versionLayout = (RelativeLayout) scrollView.getRootView().findViewById(R.id.version_layout);
        versionLayout.setOnClickListener(view1 -> {});

        editLayout = (RelativeLayout) scrollView.getRootView().findViewById(R.id.edit_layout);
        editLayout.setOnClickListener(view1 -> {});

        zanLayout = (RelativeLayout) scrollView.getRootView().findViewById(R.id.zan_layout);
        zanLayout.setOnClickListener(view1 -> {});

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        switchBtn.setChecked(ShareUtils.getValue(Contants.NIGHT_THEME_MODE, false));
    }
}
