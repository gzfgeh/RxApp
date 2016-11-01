package com.gzfgeh.nbapp.Model;

import android.util.SparseArray;

import com.gzfgeh.nbapp.APP;
import com.gzfgeh.nbapp.Common.ApiConstants;
import com.gzfgeh.nbapp.Common.HostType;
import com.gzfgeh.nbapp.Utils.LogUtils;
import com.gzfgeh.nbapp.Utils.NetWorkUtils;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description:
 * Created by guzhenfu on 2016/11/1 09:24.
 */

public class RetrofitManager {
    /**
     * 缓存时间 7天
     */
    private static final int CACHE_TIME = 60 * 60 * 24 * 7;
    /**
     * 设置网络请求超时时间
     */
    private static final int TIME_OUT = 10;
    private RetrofitService service;
    private static volatile OkHttpClient okHttpClient;
    private static SparseArray<RetrofitManager> sRetrofitManager = new SparseArray<>(HostType.TYPE_COUNT);

    public RetrofitManager(@HostType.HostTypeChecker int hostType){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiConstants.getHost(hostType))
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        service = retrofit.create(RetrofitService.class);
    }
    /**
     * 配置缓存策略
     * 有网络读服务器最新数据，没网读缓存
     */
    private final Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtils.isNetworkAvailable()){
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response response = chain.proceed(request);
            if (NetWorkUtils.isNetworkAvailable()){
                response.newBuilder().header("Cache-Control", "public, max-age=" + 0)
                        .removeHeader("Pragma")
                        .build();
            }else{
                response.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_TIME)
                        .removeHeader("Pragma")
                        .build();
            }

            return response;
        }
    };

    private final Interceptor loggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            LogUtils.i(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            LogUtils.i(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    };

    private OkHttpClient getOkHttpClient(){
        if (okHttpClient == null){
            synchronized (RetrofitManager.class){
                Cache cache = new Cache(new File(APP.getContext().getCacheDir(), "HttpCache"),
                        1024 * 1024 * 100);
                if (okHttpClient == null){
                    okHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                            .addInterceptor(interceptor)
                            .addNetworkInterceptor(interceptor)
                            .addInterceptor(loggingInterceptor)
                            .build();
                }
            }
        }
        return okHttpClient;
    }

    public static RetrofitManager getInstance(int hostType) {
        RetrofitManager retrofitManager = sRetrofitManager.get(hostType);
        if (retrofitManager == null) {
            retrofitManager = new RetrofitManager(hostType);
            sRetrofitManager.put(hostType, retrofitManager);
            return retrofitManager;
        }
        return retrofitManager;
    }

    public RetrofitService getRetrofitService(){
        return service;
    }
}
