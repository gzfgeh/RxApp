package com.gzfgeh.nbapp.Present;

import android.content.Context;

import com.gzfgeh.nbapp.Model.SettingsModel;
import com.gzfgeh.nbapp.Utils.RxSubDialogUtils;
import com.gzfgeh.nbapp.View.SettingsView;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by
 */
public class SettingsPresenter extends BasePresenter<SettingsView> {
    @Inject
    SettingsModel model;

    @Inject
    SettingsPresenter() {}

    public void getCacheSize(Context context){
        mCompositeSubscription.add(model.getCacheSize()
            .subscribe(new RxSubDialogUtils<String>(mCompositeSubscription, context){

                @Override
                protected void _onNext(String o) {
                    getView().getCacheSize(o);
                }

                @Override
                protected void _onError() {
                    getView().onFail();
                }
            }));
    }


    public void clearCache(Context context){
        mCompositeSubscription.add(model.clearCache()
                .subscribe(new RxSubDialogUtils<String>(mCompositeSubscription, context){

                    @Override
                    protected void _onNext(String o) {
                        getView().clearCache(o);
                    }

                    @Override
                    protected void _onError() {
                        getView().onFail();
                    }
                }));
    }

}
