package com.gzfgeh.nbapp.Utils.Dagger.Component;

import android.app.Activity;

import com.gzfgeh.nbapp.APP;
import com.gzfgeh.nbapp.Utils.Dagger.Module.ActivityModule;

public final class ActivityComponentFactory {
    private ActivityComponentFactory() {
        //no op
    }

    public static ActivityComponent create(Activity activity) {
        return APP.get(activity).getComponent()
                .plus(new ActivityModule(activity));
    }
}