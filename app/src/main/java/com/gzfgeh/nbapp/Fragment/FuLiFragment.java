package com.gzfgeh.nbapp.Fragment;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.gzfgeh.adapter.BaseViewHolder;
import com.gzfgeh.adapter.RecyclerArrayAdapter;
import com.gzfgeh.nbapp.Activity.FuliDetailActivity;
import com.gzfgeh.nbapp.Bean.ResultBean;
import com.gzfgeh.nbapp.Common.Contants;
import com.gzfgeh.nbapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FuLiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FuLiFragment extends BaseListFragment {

    public static FuLiFragment newInstance(String param1) {
        FuLiFragment fragment = new FuLiFragment();
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
        adapter = new RecyclerArrayAdapter<ResultBean>(getActivity(), R.layout.item_fu_li) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, ResultBean resultBean) {
                ImageView imageView = baseViewHolder.getView(R.id.image_id);
                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                params.height = (int) (300 + Math.random() * 200);
                imageView.setLayoutParams(params);
                baseViewHolder.setImageUrl(R.id.image_id, resultBean.getUrl(), R.drawable.ic_loading);
            }
        };
        adapter.setMore(this);
        adapter.setOnItemClickListener((view, i) -> {
            Intent intent = new Intent(getActivity(), FuliDetailActivity.class);
            intent.putExtra(Contants.FULI_DETAIL, adapter.getItem(i).getUrl());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                ImageView imageView = (ImageView) view.findViewById(R.id.image_id);
                startActivity(intent, ActivityOptions.
                        makeSceneTransitionAnimation(getActivity(), imageView, getString(R.string.transition_photos)).toBundle());
            }else{
                ActivityOptionsCompat options = ActivityOptionsCompat
                        .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
            }
        });
    }

    @Override
    protected void initRecyclerView() {
        initAdapter();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapterWithProgress(adapter);
        recyclerView.setRefreshListener(this);
        onRefresh();
    }
}
