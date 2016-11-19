package com.gzfgeh.nbapp.Fragment;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.gzfgeh.GRecyclerView;
import com.gzfgeh.adapter.BaseViewHolder;
import com.gzfgeh.adapter.RecyclerArrayAdapter;
import com.gzfgeh.nbapp.Bean.VideoBean;
import com.gzfgeh.nbapp.R;
import com.gzfgeh.nbapp.View.VideoView;
import com.gzfgeh.nbapp.Present.VideoPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import static android.content.Context.SENSOR_SERVICE;

/**
 * create by
 */
public class VideoFragment extends BaseFragment implements VideoView {
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    @BindView(R.id.recyclerView)
    GRecyclerView recyclerView;

    @Inject
    VideoPresenter presenter;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    private RecyclerArrayAdapter<VideoBean> adapter;
    private SensorManager sensorManager;
    private JCVideoPlayer.JCAutoFullscreenListener sensorEventListener;

    public static VideoFragment newInstance(String param1) {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);//注意不要指定父视图
        ButterKnife.bind(this, view);
        getActivityComponent().inject(this);
        presenter.attachView(this);

        initRecyclerView();
        sensorManager = (SensorManager) getContext().getSystemService(SENSOR_SERVICE);
        sensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();
        fab.setOnClickListener(view1 ->
                recyclerView.getRecyclerView().getLayoutManager().scrollToPosition(0));
        return view;
    }

    private void initRecyclerView() {
        adapter = new RecyclerArrayAdapter<VideoBean>(getActivity(), R.layout.item_video) {

            @Override
            protected void convert(BaseViewHolder baseViewHolder, VideoBean videoBean) {
                JCVideoPlayerStandard jcVideo = (JCVideoPlayerStandard) baseViewHolder.getConvertView()
                                        .findViewById(R.id.video_player);
                jcVideo.setUp(videoBean.getVideoUrl(), JCVideoPlayer.SCREEN_LAYOUT_LIST
                                , videoBean.getVideoTitle());
                Glide.with(getContext())
                        .load(videoBean.getVideoImage())
                        .into(jcVideo.thumbImageView);
            }
        };

        recyclerView.setAdapterDefaultConfig(adapter, null);
        presenter.getDataList();
    }

    @Override
    public void onFail() {
        if (adapter.getCount() > 0) {
            adapter.pauseMore();
        } else {
            recyclerView.showError();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void getVideoList(List<VideoBean> data) {
        adapter.clear();
        adapter.addAll(data);
    }

    @Override
    public void onResume() {
        super.onResume();
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
        JCVideoPlayer.releaseAllVideos();
    }


}
