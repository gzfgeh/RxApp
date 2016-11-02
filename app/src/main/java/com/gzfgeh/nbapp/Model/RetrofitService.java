package com.gzfgeh.nbapp.Model;

import com.gzfgeh.nbapp.Bean.ResultBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface RetrofitService {
    @GET("/{style}/index")
    Observable<BaseModel<ResultBean>> getNewsList(@Path("style")String style, @Query("type") String type, @Query("key") String key);
}