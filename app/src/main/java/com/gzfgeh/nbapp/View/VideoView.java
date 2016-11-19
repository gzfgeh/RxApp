package com.gzfgeh.nbapp.View;

import com.gzfgeh.nbapp.Bean.VideoBean;
import com.gzfgeh.nbapp.View.BaseView;

import java.util.List;

/**
 * Created by
 */
public interface VideoView extends BaseView {
    void getVideoList(List<VideoBean> data);
}
