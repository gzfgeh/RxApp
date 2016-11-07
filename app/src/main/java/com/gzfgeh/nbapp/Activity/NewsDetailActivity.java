package com.gzfgeh.nbapp.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.transition.Explode;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gzfgeh.nbapp.Common.Contants;
import com.gzfgeh.nbapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by guzhenfu on 2016/11/7 13:54.
 */

public class NewsDetailActivity extends BaseActivity {
    @BindView(R.id.news_detail_photo_iv)
    ImageView newsDetailPhotoIv;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private String titleImageUrl, postId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setEnterTransition(new Explode().setDuration(500));
            getWindow().setExitTransition(new Explode().setDuration(500));
        }
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            titleImageUrl = getIntent().getStringExtra(Contants.NEWS_IMG_RES);
            postId = getIntent().getStringExtra(Contants.NEWS_POST_ID);
        }
        setTitleImageView(titleImageUrl);
    }

    private void setTitleImageView(String titleImageUrl) {
        if (titleImageUrl != null) {
            Glide.with(this).load(titleImageUrl).asBitmap()
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_load_fail)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(newsDetailPhotoIv);
        }
    }
}
