package com.gzfgeh.nbapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.gzfgeh.nbapp.R;
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
    private RelativeLayout shareLayout;

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

        return view;
    }

}
