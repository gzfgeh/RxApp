package com.gzfgeh.nbapp.Model;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.gzfgeh.nbapp.APP;
import com.gzfgeh.nbapp.Bean.BaseBean;
import com.gzfgeh.nbapp.Utils.RxUtils;
import com.gzfgeh.nbapp.Utils.Utils;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by
 */
@Singleton
public class SettingsModel extends BaseModel {
    @Inject
    public SettingsModel() {}

    public Observable<String> getCacheSize(){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                long size = Utils.getFolderSize(new File(APP.getContext().getCacheDir(), "HttpCache"));
                size += Utils.getFolderSize(new File(APP.getContext().getCacheDir(), DiskCache.Factory.DEFAULT_DISK_CACHE_DIR));

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                subscriber.onNext(((float)(size/1024))+"");
                subscriber.onCompleted();
            }
        }).flatMap(s -> {
            BaseBean<String> bean = new BaseBean<>();
            bean.setResults(s);
            bean.setError(false);
            return Observable.just(bean);
        }).compose(RxUtils.handleResult());
    }

    public Observable<String> clearCache(){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //glide 缓存
                Glide.get(APP.getContext()).clearDiskCache();
                //okhttp 缓存
                Utils.deleteFolderFile(new File(APP.getContext().getCacheDir(), "HttpCache").getPath(), false);

                subscriber.onNext("清除缓存完成");
                subscriber.onCompleted();
            }
        }).flatMap(s -> {
            BaseBean<String> bean = new BaseBean<>();
            bean.setResults(s);
            bean.setError(false);
            return Observable.just(bean);
        }).compose(RxUtils.handleResult());
    }
}
