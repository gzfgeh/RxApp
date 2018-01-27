package com.gzfgeh.nbapp.Present;

import android.content.Context;

import com.gzfgeh.nbapp.Model.SettingsModel;
import com.gzfgeh.nbapp.Utils.RxSubUtils;
import com.gzfgeh.nbapp.View.SettingsView;

import javax.inject.Inject;

/**
 * Created by
 */
public class SettingsPresenter extends BasePresenter<SettingsView> {
    @Inject
    SettingsModel model;

    @Inject
    SettingsPresenter() {}

    public void getCacheSize(Context context){
        compositeDisposable.add(model.getCacheSize()
            .subscribeWith(new RxSubUtils<String>(compositeDisposable, context){

                @Override
                protected void _onNext(String o) {
                    getView().getCacheSize(o);
                }

                @Override
                protected void _onError(String msg) {
                    getView().onFail(msg);
                }
            }));
    }


    public void clearCache(Context context){
        compositeDisposable.add(model.clearCache()
                .subscribeWith(new RxSubUtils<String>(compositeDisposable, context){

                    @Override
                    protected void _onNext(String o) {
                        getView().clearCache(o);
                    }

                    @Override
                    protected void _onError(String msg) {
                        getView().onFail(msg);
                    }
                }));
    }

}
