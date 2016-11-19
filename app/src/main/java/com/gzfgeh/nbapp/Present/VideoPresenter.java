package com.gzfgeh.nbapp.Present;

import com.gzfgeh.nbapp.Bean.VideoBean;
import com.gzfgeh.nbapp.Model.VideoModel;
import com.gzfgeh.nbapp.Utils.RxSubUtils;
import com.gzfgeh.nbapp.View.VideoView;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by
 */
public class VideoPresenter extends BasePresenter<VideoView> {
    @Inject
    VideoModel model;

    @Inject
    VideoPresenter() {}

    public void getDataList(){
        mCompositeSubscription.add(model.getDataList()
                .subscribe(new RxSubUtils<List<VideoBean>>(mCompositeSubscription) {
                    @Override
                    protected void _onNext(List<VideoBean> videoBeen) {
                        getView().getVideoList(videoBeen);
                    }

                    @Override
                    protected void _onError() {
                        getView().onFail();
                    }
                }));
    }
}
