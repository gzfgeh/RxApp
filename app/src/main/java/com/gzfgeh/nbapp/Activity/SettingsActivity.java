package com.gzfgeh.nbapp.Activity;


import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gzfgeh.nbapp.Common.Contants;
import com.gzfgeh.nbapp.R;
import com.gzfgeh.nbapp.Utils.ShareUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends BaseActivity {
    @BindView(R.id.switch_id)
    SwitchCompat switchId;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.history_layout)
    RelativeLayout historyLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("设置");

        historyLayout.setOnClickListener(view -> {
            Toast.makeText(this, "努力赶工中...", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setSwitchStatus();

        switchId.setOnCheckedChangeListener((compoundButton, b) -> {
            ShareUtils.putValue(Contants.NIGHT_THEME_MODE, b);
            recreate();
        });
    }

    private void setSwitchStatus() {
        switchId.setChecked(ShareUtils.getValue(Contants.NIGHT_THEME_MODE, false));
    }
}
