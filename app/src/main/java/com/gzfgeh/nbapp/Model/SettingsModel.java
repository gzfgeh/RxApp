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

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by
 */
@Singleton
public class SettingsModel extends BaseModel {
    @Inject
    public SettingsModel() {}

    public Flowable<String> getCacheSize(){
        return Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> flowableEmitter) throws Exception {
                long size = Utils.getFolderSize(new File(APP.getContext().getCacheDir(), "HttpCache"));
                size += Utils.getFolderSize(new File(APP.getContext().getCacheDir(), DiskCache.Factory.DEFAULT_DISK_CACHE_DIR));

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                flowableEmitter.onNext(((float)(size/1024))+"");
                flowableEmitter.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .compose(RxUtils.applyIOToMainThreadSchedulers());
    }

    public Flowable<String> clearCache(){
        return Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> flowableEmitter) throws Exception {
                //glide 缓存
                Glide.get(APP.getContext()).clearDiskCache();
                //okhttp 缓存
                Utils.deleteFolderFile(new File(APP.getContext().getCacheDir(), "HttpCache").getPath(), false);

                flowableEmitter.onNext("清除缓存完成");
                flowableEmitter.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .compose(RxUtils.applyIOToMainThreadSchedulers());
    }
}
