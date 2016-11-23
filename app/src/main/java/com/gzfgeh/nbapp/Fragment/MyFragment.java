package com.gzfgeh.nbapp.Fragment;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gzfgeh.nbapp.Activity.AboutActivity;
import com.gzfgeh.nbapp.Activity.EditActivity;
import com.gzfgeh.nbapp.Activity.SettingsActivity;
import com.gzfgeh.nbapp.R;
import com.gzfgeh.nbapp.Utils.RxUtils;
import com.gzfgeh.nbapp.Utils.Utils;
import com.gzfgeh.pullToZoom.PullToZoomScrollViewEx;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import rx.Observable;

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
    private RelativeLayout shareLayout, ionicLayout, commonLayout, rnLayout;
    private RelativeLayout aboutLayout, versionLayout, zanLayout, editLayout;

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
        shareLayout.setOnClickListener(view1 -> Utils.shareText(getContext(), getString(R.string.about_myself)));

        ionicLayout = (RelativeLayout) scrollView.getRootView().findViewById(R.id.ionic_layout);
        ionicLayout.setOnClickListener(view1 -> {
            if(Utils.copyApkFromAssets(getContext(), "ionic.apk")){
                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("WebApp")
                        .setContentText("是否安装Ionic项目APP？")
                        .setConfirmText("安装")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setDataAndType(Uri.parse("file://" + Environment.getExternalStorageDirectory().getAbsolutePath()+"/ionic.apk"),
                                        "application/vnd.android.package-archive");
                                getContext().startActivity(intent);
                            }
                        })
                        .show();
            }
        });

        rnLayout = (RelativeLayout) scrollView.getRootView().findViewById(R.id.rn_layout);
        rnLayout.setOnClickListener(view1 -> {
            Toast.makeText(getActivity(), "努力赶工中...", Toast.LENGTH_SHORT).show();
        });

        commonLayout = (RelativeLayout) scrollView.getRootView().findViewById(R.id.common_settings_layout);
        commonLayout.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), SettingsActivity.class));
        });

        aboutLayout = (RelativeLayout) scrollView.getRootView().findViewById(R.id.about_layout);
        aboutLayout.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), AboutActivity.class));
        });

        versionLayout = (RelativeLayout) scrollView.getRootView().findViewById(R.id.version_layout);
        versionLayout.setOnClickListener(view1 -> {
            SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Loading");
            pDialog.setCancelable(false);
            pDialog.show();

            Observable.timer(2000, TimeUnit.MILLISECONDS)
                    .compose(RxUtils.applyIOToMainThreadSchedulers())
                    .subscribe(aLong -> {
                        Toast.makeText(getContext(), "已是最新版本", Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();
                    });
        });

        editLayout = (RelativeLayout) scrollView.getRootView().findViewById(R.id.edit_layout);
        editLayout.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), EditActivity.class));
        });

        zanLayout = (RelativeLayout) scrollView.getRootView().findViewById(R.id.zan_layout);
        zanLayout.setOnClickListener(view1 -> {
            Utils.goToMarket(getActivity());
        });

        TextView version = (TextView) scrollView.getRootView().findViewById(R.id.version_text);
        version.setText("版本更新"+Utils.getVersionCode());

        return view;
    }

}
