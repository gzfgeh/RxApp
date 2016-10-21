package com.gzfgeh.nbapp;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.gzfgeh.nbapp.Component.ApplicationComponent;
import com.gzfgeh.nbapp.Component.DaggerApplicationComponent;
import com.gzfgeh.nbapp.Module.ApplicationModule;
import com.gzfgeh.nbapp.Utils.LogUtils;

public class APP extends Application {
    private static Context context;
    private ApplicationComponent applicationComponent;

    public static APP get(Context context) {
        return (APP) context.getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

    private static boolean debugMode() {
        ApplicationInfo info = getContext().getApplicationInfo();
        if ((info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
            return true;
        } else {
            return false;
        }
    }

    public ApplicationComponent getComponent() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }

        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        if (debugMode()) {
            LogUtils.LEVEL = 0;
        } else {
            LogUtils.LEVEL = LogUtils.NOTHING;
        }

    }
}