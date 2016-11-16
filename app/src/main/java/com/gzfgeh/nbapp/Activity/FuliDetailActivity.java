package com.gzfgeh.nbapp.Activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gzfgeh.nbapp.Common.Contants;
import com.gzfgeh.nbapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by guzhenfu on 2016/11/16 13:43.
 */

public class FuliDetailActivity extends BaseActivity {
    @BindView(R.id.image_id)
    ImageView imageId;

    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuli_detail);
        ButterKnife.bind(this);

        if(getIntent() != null){
            imageUrl = getIntent().getStringExtra(Contants.FULI_DETAIL);
            Glide.with(this).load(imageUrl)
                    .centerCrop()
                    .into(imageId);
        }
    }
}
