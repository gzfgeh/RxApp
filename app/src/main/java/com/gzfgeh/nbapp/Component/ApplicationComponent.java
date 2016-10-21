package com.gzfgeh.nbapp.Component;

import android.support.annotation.NonNull;

import com.gzfgeh.nbapp.APP;
import com.gzfgeh.nbapp.Module.ActivityModule;
import com.gzfgeh.nbapp.Module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    @NonNull
    ActivityComponent plus(@NonNull ActivityModule activityModule);

    @NonNull
    void inject(@NonNull APP app);

}