package com.gzfgeh.nbapp.Model;

import com.gzfgeh.nbapp.Bean.BaseBean;
import com.gzfgeh.nbapp.Bean.ResultBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitService {
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @GET("data/{type}/20/{page}")
    Observable<BaseBean<List<ResultBean>>> getNewsList(@Path("type") String type, @Path("page") int page);
}