package com.gzfgeh.nbapp.Fragment;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gzfgeh.GRecyclerView;
import com.gzfgeh.adapter.BaseViewHolder;
import com.gzfgeh.adapter.RecyclerArrayAdapter;
import com.gzfgeh.nbapp.Activity.NewsDetailActivity;
import com.gzfgeh.nbapp.Bean.DataBean;
import com.gzfgeh.nbapp.Bean.ResultBean;
import com.gzfgeh.nbapp.Common.ApiConstants;
import com.gzfgeh.nbapp.Common.Contants;
import com.gzfgeh.nbapp.Present.NewsListPresenter;
import com.gzfgeh.nbapp.R;
import com.gzfgeh.nbapp.Utils.RxBus;
import com.gzfgeh.nbapp.View.NewsListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsListFragment#} factory method to
 * create an instance of this fragment.
 */
public class NewsListFragment extends BaseListFragment {
    public static NewsListFragment newInstance(String param1) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void initAdapter() {
        adapter = new RecyclerArrayAdapter<ResultBean>(getContext(), R.layout.item_news) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, ResultBean dataBean) {
                baseViewHolder.setImageUrl(R.id.image_id, dataBean.getImages().get(0), R.drawable.ic_loading);
                baseViewHolder.setText(R.id.title_id, dataBean.getDesc());
                baseViewHolder.setText(R.id.time_id, dataBean.getCreatedAt());
            }
        };
    }
}
