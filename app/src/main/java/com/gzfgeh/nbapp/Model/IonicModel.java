package com.gzfgeh.nbapp.Model;

import com.gzfgeh.nbapp.APP;
import com.gzfgeh.nbapp.Utils.RxUtils;
import com.gzfgeh.nbapp.Utils.Utils;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by
 */
@Singleton
public class IonicModel extends BaseModel {
    @Inject
    public IonicModel() {}

    public Flowable<Boolean> getApkFinish(){
        return Flowable.create(new FlowableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<Boolean> flowableEmitter) throws Exception {
                flowableEmitter.onNext(Utils.copyApkFromAssets(APP.getContext(), "ionic.apk"));
                flowableEmitter.onComplete();
            }
        }, BackpressureStrategy.ERROR)
        .compose(RxUtils.applyIOToMainThreadSchedulers());

    }
}
