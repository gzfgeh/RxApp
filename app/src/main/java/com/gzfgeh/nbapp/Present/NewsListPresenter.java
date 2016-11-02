package com.gzfgeh.nbapp.Present;

import com.gzfgeh.nbapp.Bean.DataBean;
import com.gzfgeh.nbapp.Model.NewsListModel;
import com.gzfgeh.nbapp.Utils.RxSubUtils;
import com.gzfgeh.nbapp.View.NewsListView;

import java.util.List;

import javax.inject.Inject;

/**
 * Description:
 * Created by guzhenfu on 2016/11/2 20:04.
 */

public class NewsListPresenter extends BasePresenter<NewsListView> {
    @Inject
    NewsListModel newsListModel;

    @Inject
    public NewsListPresenter() {}

    public void getListData(String style, String type){
        mCompositeSubscription.add(newsListModel.getNewsList(style, type)
                .subscribe(new RxSubUtils<List<DataBean>>(mCompositeSubscription) {
                    @Override
                    protected void _onNext(List<DataBean> dataBeen) {
                        getView().getListData(dataBeen);
                    }
                }));
    }
}
