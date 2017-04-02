package com.gzfgeh.nbapp.Present;

import android.text.TextUtils;

import com.gzfgeh.nbapp.Bean.ResultBean;
import com.gzfgeh.nbapp.Common.ApiConstants;
import com.gzfgeh.nbapp.Model.NewsListModel;
import com.gzfgeh.nbapp.Utils.RxSubUtils;
import com.gzfgeh.nbapp.View.NewsListView;

import java.util.ArrayList;
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

    public void getListData(String type, int page){
        compositeDisposable.add(newsListModel.getNewsList(type,page)
                .subscribeWith(new RxSubUtils<List<ResultBean>>(compositeDisposable) {
                    @Override
                    protected void _onNext(List<ResultBean> dataBeen) {
                        if (TextUtils.equals(type, ApiConstants.GANDK_IO_MEIZI)) {
                            getView().getListData(dataBeen);
                        } else {
                            List<ResultBean> list = new ArrayList<>();
                            for (ResultBean bean : dataBeen) {
                                if (bean.getImages() != null)
                                    list.add(bean);
                            }
                            getView().getListData(list);
                        }
                    }

                    @Override
                    protected void _onError() {
                        getView().onFail();
                    }


                }));
    }
}
