package com.gzfgeh.nbapp.Utils;

import com.gzfgeh.nbapp.Bean.BaseBean;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxUtils {
    private static FlowableTransformer ioToMainThreadSchedulerTransformer;
    private static FlowableTransformer newThreadToMainThreadSchedulerTransformer;

    static {
        ioToMainThreadSchedulerTransformer = createIOToMainThreadScheduler();
        newThreadToMainThreadSchedulerTransformer = createNewThreadToMainThreadScheduler();
    }

    private static <T> FlowableTransformer<T, T> createIOToMainThreadScheduler() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 从IO线程切换到主线程
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> applyIOToMainThreadSchedulers() {
        return ioToMainThreadSchedulerTransformer;
    }

    private static <T> FlowableTransformer<T, T> createNewThreadToMainThreadScheduler() {
        return tObservable -> tObservable.subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.computation())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> FlowableTransformer<T, T> applyNewThreadToMainThreadSchedulers() {
        return newThreadToMainThreadSchedulerTransformer;
    }

    /**
     * 处理服务器返回的数据，进一步处理错误信息
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<BaseBean<T>, T> handleResult(){
        return new FlowableTransformer<BaseBean<T>, T>() {
            @Override
            public Publisher<T> apply(@NonNull Flowable<BaseBean<T>> flowable) {
                return flowable.flatMap(new Function<BaseBean<T>, Publisher<T>>() {
                    @Override
                    public Publisher<T> apply(@NonNull BaseBean<T> tBaseBean) throws Exception {
                        if (!tBaseBean.isError()){
                            return createData(tBaseBean.getResults());
                        }else{
                            return Flowable.error(new ServerException("服务器返回错误"));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };

    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Flowable<T> createData(T data) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<T> flowableEmitter) throws Exception {
                try {
                    flowableEmitter.onNext(data);
                    flowableEmitter.onComplete();
                }catch (Exception e){
                    flowableEmitter.onError(e);
                }
            }
        }, BackpressureStrategy.ERROR);
    }

    /**
     * 自定义 服务器返回异常
     */
    public static class ServerException extends Throwable {
        public ServerException(String msg) {}
    }

}