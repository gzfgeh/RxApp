package com.gzfgeh.nbapp.Activity;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;

import com.gzfgeh.nbapp.Common.Contants;
import com.gzfgeh.nbapp.R;
import com.gzfgeh.nbapp.Utils.ShareUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends BaseActivity {
    @BindView(R.id.switch_id)
    SwitchCompat switchId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);
        ButterKnife.bind(this);
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
