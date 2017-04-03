package com.gzfgeh.nbapp;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;

import com.gzfgeh.nbapp.Common.Contants;
import com.gzfgeh.nbapp.Component.ApplicationComponent;
import com.gzfgeh.nbapp.Component.DaggerApplicationComponent;
import com.gzfgeh.nbapp.Module.ApplicationModule;
import com.gzfgeh.nbapp.Utils.LogUtils;
import com.gzfgeh.nbapp.Utils.ShareUtils;

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
        initDayNightMode();
    }

    private void initDayNightMode() {
        if(ShareUtils.getValue(Contants.NIGHT_THEME_MODE, false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(this);
//    }
}