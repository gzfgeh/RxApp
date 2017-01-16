package com.gzfgeh.nbapp;

import org.junit.Rule;

/**
 * Description:
 * Created by guzhenfu on 17/1/15.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */

public class BaseTest {
    @Rule
    public JUnitDaggerMockRule rule;

    public BaseTest() {
        rule = new JUnitDaggerMockRule();
    }
}
