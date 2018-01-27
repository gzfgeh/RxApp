package com.gzfgeh.nbapp.Fragment;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gzfgeh.GRecyclerView;
import com.gzfgeh.adapter.BaseViewHolder;
import com.gzfgeh.adapter.RecyclerArrayAdapter;
import com.gzfgeh.nbapp.Activity.FuliDetailActivity;
import com.gzfgeh.nbapp.Bean.ResultBean;
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
 * Use the {@link FuLiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FuLiFragment extends BaseLazyFragment<NewsListPresenter> implements SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener, NewsListView {
    protected static final String ARG_PARAM1 = "param1";
    protected String mParam1;

    @BindView(R.id.recyclerView)
    GRecyclerView recyclerView;

    protected RecyclerArrayAdapter<ResultBean> adapter;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private int pageIndex = 1;

    public static FuLiFragment newInstance(String param1) {
        FuLiFragment fragment = new FuLiFragment();
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
        View view = inflater.inflate(R.layout.fragment_fuli, container, false);
        ButterKnife.bind(this, view);

        initRecyclerView();
        registerScrollToTopEvent();
        return view;
    }

    private void registerScrollToTopEvent() {
        fab.setOnClickListener(view -> recyclerView.getRecyclerView().getLayoutManager().scrollToPosition(0));
    }

    @Override
    public void onFirstUserVisible() {
        super.onFirstUserVisible();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        presenter.getListData(mParam1, pageIndex);
    }

    @Override
    public void onLoadMore() {
        pageIndex++;
        presenter.getListData(mParam1, pageIndex);
    }

    @Override
    public void getListData(List<ResultBean> list) {
        if (pageIndex == 1) {
            adapter.clear();
        }
        adapter.addAll(list);
    }


    @Override
    public void onFail(String msg) {
        if (adapter.getCount() > 0) {
            adapter.pauseMore();
        } else {
            recyclerView.showError();
        }
    }


    @Override
    protected void inject() {
        super.inject();
        getActivityComponent().inject(this);
    }

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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ImageView imageView = (ImageView) view.findViewById(R.id.image_id);
                startActivity(intent, ActivityOptions.
                        makeSceneTransitionAnimation(getActivity(), imageView, getString(R.string.transition_photos)).toBundle());
            } else {
                ActivityOptionsCompat options = ActivityOptionsCompat
                        .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
            }
        });
    }


    private void initRecyclerView() {
        initAdapter();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapterWithProgress(adapter);
        recyclerView.setRefreshListener(this);
    }
}
