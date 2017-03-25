package com.gzfgeh.nbapp.Activity;

import android.os.Bundle;
import android.view.KeyEvent;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;
import com.gzfgeh.nbapp.BuildConfig;
import com.gzfgeh.nbapp.RnModule.RnProxy;

/**
 * Description:
 * Created by guzhenfu on 17/3/25.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */

public class RnActivity extends ReactActivity {
    //根视图
    private ReactRootView reactRootView;
    //RN管理类
    private ReactInstanceManager reactInstanceManager;

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "rnproject";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化
        reactRootView = new ReactRootView(this);
        //初始化,这部分可以看api文档，具体字段内容还是比较多的
        reactInstanceManager = ReactInstanceManager.builder()
                //应用上下文
                .setApplication(getApplication())
                //js打包的名字
                .setBundleAssetName("index.android.bundle")
                //js首页
                .setJSMainModuleName("index.android")
                //基础的各种manager
                .addPackage(new MainReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                //初始化执行的时间
                .setInitialLifecycleState(LifecycleState.BEFORE_RESUME)
                .build();
        reactRootView.startReactApplication(reactInstanceManager, "rnproject", null);
        //渲染
        setContentView(reactRootView);
    }

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new RnProxy(this, null);
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (reactInstanceManager != null) {
            reactInstanceManager.onHostPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (reactInstanceManager != null) {
            reactInstanceManager.onHostResume(this, this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (reactInstanceManager != null) {
            reactInstanceManager.onHostDestroy();
        }
    }

    @Override
    public void onBackPressed() {
        if (reactInstanceManager != null) {
            reactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && reactInstanceManager != null) {
//            reactInstanceManager.showDevOptionsDialog();
//            return true;
//        }
//        return super.onKeyUp(keyCode, event);
//    }
}
