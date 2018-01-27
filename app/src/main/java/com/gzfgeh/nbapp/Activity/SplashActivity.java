package com.gzfgeh.nbapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gzfgeh.nbapp.Present.SplashPresent;
import com.gzfgeh.nbapp.Utils.LogUtils;
import com.gzfgeh.nbapp.Utils.ShareUtils;
import com.gzfgeh.nbapp.Utils.Utils;
import com.gzfgeh.nbapp.View.SplashView;
import com.gzfgeh.nbapp.R;

import net.youmi.android.normal.spot.SpotListener;

import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class SplashActivity extends BaseActivity<SplashPresent> implements SplashView {
    private static final int STARTUP_DELAY = 300; // 启动延迟
    private static final int ANIM_ITEM_DURATION = 2000;

    private ImageView tempPage;
    private ImageView ivLogo;
    private TextView tvLogoText;
    private ViewPropertyAnimatorCompat viewAnimator;
    private int secondTime = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        presenter.getUrl();

        tempPage = (ImageView) findViewById(R.id.temp_page);
        ivLogo = (ImageView) findViewById(R.id.onboard_iv_logo);
        tvLogoText = (TextView) findViewById(R.id.tv_logo_text);
        logoAnimation();

    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    /**
     * 向上移动
     */
    private void logoAnimation() {
        ViewCompat.animate(ivLogo)
                .translationY(-200)
                .setStartDelay(STARTUP_DELAY)
                .setDuration(ANIM_ITEM_DURATION)
                .setInterpolator(new DecelerateInterpolator(1.2f))
                .start();

        viewAnimator = ViewCompat.animate(tvLogoText)
                .translationY(100).alpha(1)
                .setStartDelay(500)
                .setDuration(ANIM_ITEM_DURATION);
        viewAnimator.setInterpolator(new DecelerateInterpolator()).start();
    }

    @Override
    public void getUrlData(String data) {
        Glide.with(SplashActivity.this)
                .load(data)
                .into(tempPage);

        Flowable.timer(secondTime, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Subscription subscription) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        tvLogoText.setVisibility(View.GONE);
                        ivLogo.setVisibility(View.GONE);
                        goToNextActivity();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        goToNextActivity();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void goToNextActivity() {
        boolean isComeOver = ShareUtils.getValue("isComeOver", false);
        if (isComeOver) {
            FrameLayout layout = (FrameLayout)findViewById(R.id.content);
            Utils.inAdvert(SplashActivity.this, layout,
                    new SpotListener() {
                        @Override
                        public void onShowSuccess() {
                            LogUtils.i("onShowSuccess");
                            Observable.timer(6, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                                    .map(aLong -> {
                                        finish();
                                        return aLong;
                                    }).subscribe();
                        }

                        @Override
                        public void onShowFailed(int i) {
                            LogUtils.i("onShowFailed:" + i);
                            layout.setVisibility(View.GONE);
                            tempPage.setVisibility(View.VISIBLE);
                            finish();
                        }

                        @Override
                        public void onSpotClosed() {
                            LogUtils.i("onSpotClosed");
                        }

                        @Override
                        public void onSpotClicked(boolean b) {
                            LogUtils.i("onSpotClicked");
                        }
                    });
        } else {
            startActivity(new Intent(SplashActivity.this, LaunchActivity.class));
            finish();
        }
    }

    @Override
    public void onFail(String msg) {

    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.i("onResume");
    }
}
