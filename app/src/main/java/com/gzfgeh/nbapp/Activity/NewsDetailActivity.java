package com.gzfgeh.nbapp.Activity;

import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gzfgeh.nbapp.Bean.ResultBean;
import com.gzfgeh.nbapp.Common.Contants;
import com.gzfgeh.nbapp.Present.BasePresenter;
import com.gzfgeh.nbapp.R;
import com.gzfgeh.nbapp.View.BaseView;

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
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.myProgressBar)
    ProgressBar myProgressBar;
    private ResultBean resultBean;

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
            resultBean = (ResultBean) getIntent().getSerializableExtra(Contants.NEWS_OBJ);
        }
        setTitleImageView(resultBean.getImages().get(0));
        setTitleText(resultBean.getWho());
        setSupportActionBar(toolbar);
        initWebView(resultBean.getUrl());

        fab.setOnClickListener(view -> {
            Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
        });
    }

    private void startFabAnimation() {
        ViewCompat.animate(fab)
                .translationX(240)
                .setStartDelay(300)
                .setDuration(1000)
                .setInterpolator(new DecelerateInterpolator(1.2f))
                .start();
    }

    private void initWebView(String url) {
        webView.getSettings().setDomStorageEnabled(true);
        //缩放
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setVerticalScrollbarOverlay(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {
             @Override
             public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                 view.loadUrl(url);
                 return true;
             }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    myProgressBar.setVisibility(View.GONE);
                    fab.setVisibility(View.VISIBLE);
                    startFabAnimation();
                } else {
                    if (myProgressBar.getVisibility() == View.GONE) {
                        myProgressBar.setVisibility(View.VISIBLE);
                    }
                    myProgressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    private void setTitleText(String who) {
        toolbarLayout.setTitle(who);
        toolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.white));
        toolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
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
