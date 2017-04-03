package com.gzfgeh.nbapp.Model;

import com.gzfgeh.nbapp.Bean.ResultBean;
import com.gzfgeh.nbapp.Utils.RxUtils;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

/**
 * Description:
 * Created by guzhenfu on 2016/11/2 19:42.
 */
@Singleton
public class NewsListModel extends BaseModel{
    @Inject
    public NewsListModel() {}

    public Flowable<List<ResultBean>> getNewsList(String type, int page){
        return service.getNewsList(type, page)
                .compose(RxUtils.handleResult());

    }
}
