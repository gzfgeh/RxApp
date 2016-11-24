package com.gzfgeh.nbapp.Present;

import android.content.Context;

import com.gzfgeh.nbapp.Model.IonicModel;
import com.gzfgeh.nbapp.Utils.RxSubDialogUtils;
import com.gzfgeh.nbapp.View.IonicView;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by
 */
public class IonicPresenter extends BasePresenter<IonicView> {
    @Inject
    IonicModel model;

    @Inject
    IonicPresenter() {}

    public void getApkFinish(Context context){
        mCompositeSubscription.add(model.getApkFinish()
                .subscribe(new RxSubDialogUtils<Boolean>(mCompositeSubscription,context) {
                    @Override
                    protected void _onNext(Boolean aBoolean) {
                        getView().getFinish(aBoolean);
                    }

                    @Override
                    protected void _onError() {

                    }
                }));
    }
}
