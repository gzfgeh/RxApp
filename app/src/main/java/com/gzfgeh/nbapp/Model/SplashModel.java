package com.gzfgeh.nbapp.Model;

import com.gzfgeh.nbapp.Utils.RxUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.annotations.NonNull;

@Singleton
public class SplashModel {
    @Inject
    public SplashModel() {
    }

    public Flowable<String> getUrl() {
        return Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> observableEmitter) throws Exception {
                observableEmitter.onNext("http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg");
                observableEmitter.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .compose(RxUtils.applyIOToMainThreadSchedulers());
    }
}