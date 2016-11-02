package com.gzfgeh.nbapp.Component;

import com.gzfgeh.nbapp.Fragment.NewsListFragment;
import com.gzfgeh.nbapp.Module.ActivityModule;
import com.gzfgeh.nbapp.Activity.SplashActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {ActivityModule.class})
@ActivityScope
public interface ActivityComponent {
    void inject(SplashActivity activity);
    void inject(NewsListFragment fragment);
}