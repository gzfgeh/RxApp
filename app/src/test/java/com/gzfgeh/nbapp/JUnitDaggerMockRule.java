package com.gzfgeh.nbapp;


import com.gzfgeh.nbapp.Component.ApplicationComponent;
import com.gzfgeh.nbapp.Module.ApplicationModule;
import com.gzfgeh.nbapp.View.NewsListView;

import it.cosenonjaviste.daggermock.DaggerMockRule;

/**
 * Description:
 * Created by guzhenfu on 17/1/15.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */

public class JUnitDaggerMockRule extends DaggerMockRule<ApplicationComponent> {
    public JUnitDaggerMockRule() {
        super(ApplicationComponent.class, new ApplicationModule(null));
        providesMock(NewsListView.class);
    }
}
