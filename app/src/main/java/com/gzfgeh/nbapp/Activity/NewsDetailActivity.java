package com.gzfgeh.nbapp.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Explode;

import com.gzfgeh.nbapp.R;

/**
 * Description:
 * Created by guzhenfu on 2016/11/7 13:54.
 */

public class NewsDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= 21){
            getWindow().setEnterTransition(new Explode().setDuration(500));
            getWindow().setExitTransition(new Explode().setDuration(500));
        }

        setContentView(R.layout.activity_news_detail);
    }
}
