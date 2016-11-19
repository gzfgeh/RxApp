package com.gzfgeh.nbapp.Model;

import com.gzfgeh.nbapp.Bean.VideoBean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by
 */
@Singleton
public class VideoModel extends BaseModel {
    @Inject
    public VideoModel() {}

    public Observable<List<VideoBean>> getDataList(){
        return Observable.create(new Observable.OnSubscribe<List<VideoBean>>() {
            @Override
            public void call(Subscriber<? super List<VideoBean>> subscriber) {
                List<VideoBean> list = createData();
                subscriber.onNext(list);
                subscriber.onCompleted();
            }
        });
    }

    private List<VideoBean> createData() {
        List<VideoBean> list = new ArrayList<>();
        for(int i=0; i<10; i++){
            VideoBean bean = new VideoBean();
            bean.setVideoTitle("搞笑视频"+i+1);
            bean.setVideoUrl(videoUrls[i]);
            bean.setVideoImage(videoThumbs[i]);
            list.add(bean);
        }
        return list;
    }

    public String[] videoUrls = {
            "http://v.cctv.com/flash/mp4video6/TMS/2011/01/05/cf752b1c12ce452b3040cab2f90bc265_h264818000nero_aac32-1.mp4",
            "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4",
            "http://krtv.qiniudn.com/150522nextapp",
            "http://video.jiecao.fm/5/1/%E8%87%AA%E5%8F%96%E5%85%B6%E8%BE%B1.mp4",
            "http://v.cctv.com/flash/mp4video6/TMS/2011/01/05/cf752b1c12ce452b3040cab2f90bc265_h264818000nero_aac32-1.mp4",
            "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4",
            "http://krtv.qiniudn.com/150522nextapp",
            "http://video.jiecao.fm/5/1/%E8%87%AA%E5%8F%96%E5%85%B6%E8%BE%B1.mp4",
            "http://video.jiecao.fm/5/1/%E8%87%AA%E5%8F%96%E5%85%B6%E8%BE%B1.mp4",
            "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4"};

    public String[] videoThumbs = {
            "http://pubimage.360doc.com/cntv.jpg",
            "http://cos.myqcloud.com/1000264/qcloud_video_attachment/842646334/vod_cover/cover1458036374.jpg",
            "http://pubimage.360doc.com/cntv.jpg",
            "http://cos.myqcloud.com/1000264/qcloud_video_attachment/842646334/vod_cover/cover1458036374.jpg",
            "http://img4.jiecaojingxuan.com/2016/5/1/3430ec64-e6a7-4d8e-b044-9d408e075b7c.jpg",
            "http://cos.myqcloud.com/1000264/qcloud_video_attachment/842646334/vod_cover/cover1458036374.jpg",
            "http://img4.jiecaojingxuan.com/2016/5/1/3430ec64-e6a7-4d8e-b044-9d408e075b7c.jpg",
            "http://cos.myqcloud.com/1000264/qcloud_video_attachment/842646334/vod_cover/cover1458036374.jpg",
            "http://img4.jiecaojingxuan.com/2016/5/1/3430ec64-e6a7-4d8e-b044-9d408e075b7c.jpg",
            "http://cos.myqcloud.com/1000264/qcloud_video_attachment/842646334/vod_cover/cover1458036374.jpg"};

}
