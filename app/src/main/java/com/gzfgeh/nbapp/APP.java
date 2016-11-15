package com.gzfgeh.nbapp;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.v7.app.AppCompatDelegate;

import com.gzfgeh.nbapp.Common.Contants;
import com.gzfgeh.nbapp.Component.ApplicationComponent;
import com.gzfgeh.nbapp.Component.DaggerApplicationComponent;
import com.gzfgeh.nbapp.Module.ApplicationModule;
import com.gzfgeh.nbapp.Utils.LogUtils;
import com.gzfgeh.nbapp.Utils.ShareUtils;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

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

    {
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);

        context = getApplicationContext();
        if (debugMode()) {
            LogUtils.LEVEL = 0;
        } else {
            LogUtils.LEVEL = LogUtils.NOTHING;
        }
        initDayNightMode();
        initUmeng();
    }

    private void initUmeng() {
        Config.REDIRECT_URL = "您新浪后台的回调地址";
    }

    private void initDayNightMode() {
        if(ShareUtils.getValue(Contants.NIGHT_THEME_MODE, false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }


}