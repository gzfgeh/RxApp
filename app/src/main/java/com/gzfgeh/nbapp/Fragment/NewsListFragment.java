package com.gzfgeh.nbapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gzfgeh.GRecyclerView;
import com.gzfgeh.adapter.BaseViewHolder;
import com.gzfgeh.adapter.RecyclerArrayAdapter;
import com.gzfgeh.nbapp.Bean.DataBean;
import com.gzfgeh.nbapp.Bean.ResultBean;
import com.gzfgeh.nbapp.Common.ApiConstants;
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
 * Use the {@link NewsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener, NewsListView {
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    @BindView(R.id.recyclerView)
    GRecyclerView recyclerView;

    @Inject
    NewsListPresenter presenter;

    private RecyclerArrayAdapter<ResultBean> adapter;
    private int pageIndex = 1;

    public static NewsListFragment newInstance(String param1) {
        NewsListFragment fragment = new NewsListFragment();
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
        View view = inflater.inflate(R.layout.fragment_tab_layout, container, false);
        ButterKnife.bind(this, view);
        getActivityComponent().inject(this);
        presenter.attachView(this);

        initRecyclerView();
        registerScrollToTopEvent();
        return view;
    }

    private void registerScrollToTopEvent() {
        RxBus.getInstance().toObservable(String.class)
                .subscribe(s -> {
                    recyclerView.getRecyclerView().getLayoutManager().scrollToPosition(0);
                });
    }

    private void initRecyclerView() {
        adapter = new RecyclerArrayAdapter<ResultBean>(getContext(), R.layout.item_news) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, ResultBean dataBean) {
                baseViewHolder.setImageUrl(R.id.image_id, dataBean.getImages().get(0), R.mipmap.ic_launcher);
                baseViewHolder.setText(R.id.title_id, dataBean.getDesc());
                baseViewHolder.setText(R.id.time_id, dataBean.getCreatedAt());
            }
        };

        recyclerView.setAdapterDefaultConfig(adapter, this, this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        presenter.getListData(ApiConstants.GANDK_IO_ANDROID, pageIndex);
    }

    @Override
    public void onLoadMore() {
        pageIndex++;
        presenter.getListData(ApiConstants.GANDK_IO_ANDROID, pageIndex);
    }

    @Override
    public void getListData(List<ResultBean> list) {
        if (pageIndex == 1){
            adapter.clear();
        }
        adapter.addAll(list);
    }


    @Override
    public void onFail() {
        adapter.pauseMore();
    }
}
