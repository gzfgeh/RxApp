package com.gzfgeh.nbapp.Activity;

import android.support.v7.app.AppCompatActivity;

import com.gzfgeh.nbapp.Component.ActivityComponent;
import com.gzfgeh.nbapp.Component.ActivityComponentFactory;

public class BaseActivity extends AppCompatActivity {
    protected ActivityComponent activityComponent;

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = ActivityComponentFactory.create(this);
        }
        return activityComponent;
    }
}
