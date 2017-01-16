package com.gzfgeh.nbapp.Model;

import com.gzfgeh.nbapp.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Description:
 * Created by guzhenfu on 17/1/15.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class NewsListModelTest {

    private NewsListModel model;

    @Before
    public void setUp() throws Exception {
        model = new NewsListModel();
    }

    @Test
    public void getNewsList() throws Exception {
        model.getNewsList("Android", 1);
    }

}