package com.gzfgeh.nbapp.Model;

import com.gzfgeh.nbapp.Bean.BaseBean;
import com.gzfgeh.nbapp.Bean.ResultBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitService {
    @GET("data/{type}/10/{page}")
    Observable<BaseBean<List<ResultBean>>> getNewsList(@Query("type") String type, @Query("page") int page);
}