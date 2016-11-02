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
import com.gzfgeh.nbapp.Present.NewsListPresenter;
import com.gzfgeh.nbapp.R;
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

    private RecyclerArrayAdapter<DataBean> adapter;

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
        return view;
    }

    private void initRecyclerView() {
        adapter = new RecyclerArrayAdapter<DataBean>(getContext(), R.layout.item_news) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, DataBean dataBean) {
                baseViewHolder.setImageUrl(R.id.image_id, dataBean.getThumbnail_pic_s(), R.mipmap.ic_launcher);
                baseViewHolder.setText(R.id.title_id, dataBean.getTitle());
                baseViewHolder.setText(R.id.time_id, dataBean.getDate());
            }
        };

        recyclerView.setAdapterDefaultConfig(adapter, this, this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        presenter.getListData("toutiao", "top");
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void getListData(List<DataBean> list) {
        adapter.clear();
        adapter.addAll(list);
    }
}
