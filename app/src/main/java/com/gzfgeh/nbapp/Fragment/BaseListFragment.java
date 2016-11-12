package com.gzfgeh.nbapp.Fragment;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
 * Use the {@link BaseListFragment} factory method to
 * create an instance of this fragment.
 */
public abstract class BaseListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener, NewsListView {
    protected static final String ARG_PARAM1 = "param1";
    protected String mParam1;

    @BindView(R.id.recyclerView)
    GRecyclerView recyclerView;

    @Inject
    NewsListPresenter presenter;

    protected RecyclerArrayAdapter<ResultBean> adapter;
    private int pageIndex = 1;


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
        initInject();
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

    protected void initRecyclerView() {
        initAdapter();
        adapter.setOnItemClickListener((view, i) -> {
            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Contants.NEWS_OBJ, adapter.getItem(i));
            intent.putExtras(bundle);
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

        recyclerView.setAdapterDefaultConfig(adapter, this, this);
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
        if (pageIndex == 1){
            adapter.clear();
        }
        adapter.addAll(list);
    }


    @Override
    public void onFail() {
        if(adapter.getCount() > 0) {
            adapter.pauseMore();
        }else{
            recyclerView.showError();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    public abstract void initInject();
    public abstract void initAdapter();
}
