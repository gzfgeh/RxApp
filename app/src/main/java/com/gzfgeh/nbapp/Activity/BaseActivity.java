package com.gzfgeh.nbapp.Activity;

import com.gzfgeh.nbapp.Component.ActivityComponent;
import com.gzfgeh.nbapp.Component.ActivityComponentFactory;
import com.zhy.autolayout.AutoLayoutActivity;

public class BaseActivity extends AutoLayoutActivity {
    protected ActivityComponent activityComponent;

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = ActivityComponentFactory.create(this);
        }
        return activityComponent;
    }
}
