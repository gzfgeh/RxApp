package com.gzfgeh.nbapp.Activity;


import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gzfgeh.nbapp.APP;
import com.gzfgeh.nbapp.Common.Contants;
import com.gzfgeh.nbapp.Present.SettingsPresenter;
import com.gzfgeh.nbapp.R;
import com.gzfgeh.nbapp.Utils.RxUtils;
import com.gzfgeh.nbapp.Utils.ShareUtils;
import com.gzfgeh.nbapp.Utils.Utils;
import com.gzfgeh.nbapp.View.SettingsView;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;

public class SettingsActivity extends BaseActivity implements SettingsView {
    @BindView(R.id.switch_id)
    SwitchCompat switchId;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.history_layout)
    RelativeLayout historyLayout;
    @BindView(R.id.clear_id)
    TextView clearId;
    @BindView(R.id.cache_layout)
    RelativeLayout cacheLayout;

    @Inject
    SettingsPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        presenter.attachView(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("设置");

        historyLayout.setOnClickListener(view -> {
            Toast.makeText(this, "努力赶工中...", Toast.LENGTH_SHORT).show();
        });

        cacheLayout.setOnClickListener(view -> {
            // 必须在UI线程中调用
            Glide.get(SettingsActivity.this).clearMemory();
            presenter.clearCache(SettingsActivity.this);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setSwitchStatus();

        presenter.getCacheSize(this);
        switchId.setOnCheckedChangeListener((compoundButton, b) -> {
            ShareUtils.putValue(Contants.NIGHT_THEME_MODE, b);
            recreate();
        });
    }

    private void setSwitchStatus() {
        switchId.setChecked(ShareUtils.getValue(Contants.NIGHT_THEME_MODE, false));
    }

    @Override
    public void onFail() {

    }

    @Override
    public void getCacheSize(String data) {
        clearId.setText("当前缓存"+data + "Kb");
    }

    @Override
    public void clearCache(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}
