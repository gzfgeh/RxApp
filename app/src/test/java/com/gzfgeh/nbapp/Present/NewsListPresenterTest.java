package com.gzfgeh.nbapp.Present;

import com.gzfgeh.nbapp.Fragment.BaseListFragment;
import com.gzfgeh.nbapp.Fragment.NewsListFragment;
import com.gzfgeh.nbapp.JUnitDaggerMockRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import it.cosenonjaviste.daggermock.InjectFromComponent;

import static org.junit.Assert.*;

/**
 * Description:
 * Created by guzhenfu on 17/1/15.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */
public class NewsListPresenterTest {

    //@InjectFromComponent(BaseListFragment.class)
    NewsListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new NewsListPresenter();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getListData() throws Exception {
        presenter.getListData("Android", 1);
    }

}