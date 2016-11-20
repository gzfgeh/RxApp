package com.gzfgeh.nbapp.View;

import com.gzfgeh.nbapp.View.BaseView;

/**
 * Created by
 */
public interface SettingsView extends BaseView {
    void getCacheSize(String data);
    void clearCache(String data);
}
