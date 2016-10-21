package com.gzfgeh.nbapp.Module;

import android.app.Application;

import com.gzfgeh.nbapp.Model.RetrofitConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    protected final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    RetrofitConfig provideFreyRetrofit() {
        return new RetrofitConfig();
    }

}