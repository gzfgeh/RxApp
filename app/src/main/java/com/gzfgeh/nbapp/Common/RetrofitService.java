package com.gzfgeh.nbapp.Common;

import com.gzfgeh.nbapp.Bean.BaseBean;
import com.gzfgeh.nbapp.Bean.ResultBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RetrofitService {
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @GET("data/{type}/20/{page}")
    Flowable<BaseBean<List<ResultBean>>> getNewsList(@Path("type") String type, @Path("page") int page);
}