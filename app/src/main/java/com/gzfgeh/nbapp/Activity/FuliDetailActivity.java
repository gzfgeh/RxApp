package com.gzfgeh.nbapp.Activity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.gzfgeh.nbapp.Common.Contants;
import com.gzfgeh.nbapp.R;
import com.gzfgeh.nbapp.Utils.Utils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:
 * Created by guzhenfu on 2016/11/16 13:43.
 */

public class FuliDetailActivity extends BaseActivity {
    @BindView(R.id.image_id)
    ImageView imageId;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private String imageUrl;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setEnterTransition(new Fade().setDuration(500));
            getWindow().setExitTransition(new Fade().setDuration(500));
        }
        setContentView(R.layout.activity_fuli_detail);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            imageUrl = getIntent().getStringExtra(Contants.FULI_DETAIL);
            Glide.with(this).load(imageUrl)
                    .asBitmap()
                    .placeholder(R.drawable.ic_loading)
                    .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {

                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                            ViewGroup.LayoutParams params = imageId.getLayoutParams();
                            params.height = bitmap.getHeight() * bitmap.getWidth() / Utils.getWidthInPx(FuliDetailActivity.this);
                            imageId.setLayoutParams(params);
                            imageId.setImageBitmap(bitmap);
                            file = Utils.getImageFile(bitmap);
                        }

                    });
        }

        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.image_id)
    void ImageClick(){
        if (toolbar.getAlpha() == 0) {
            ViewCompat.animate(toolbar)
                    .alpha(1).translationY(10)
                    .setDuration(500)
                    .start();
        }else {
            ViewCompat.animate(toolbar)
                    .alpha(0).translationY(-100)
                    .setDuration(500)
                    .start();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_photo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Utils.sharePic(this, Uri.fromFile(file));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
