package com.gzfgeh.nbapp.Widget;

import android.support.design.widget.AppBarLayout;

import com.gzfgeh.nbapp.Common.AppBarStateType;

/**
 * Description:
 * Created by guzhenfu on 2016/11/8 16:48.
 */

public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener{
    private int mCurrentState = AppBarStateType.IDLE;

    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            if (mCurrentState != AppBarStateType.EXPANDED) {
                onStateChanged(appBarLayout, AppBarStateType.EXPANDED);
            }
            mCurrentState = AppBarStateType.EXPANDED;
        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != AppBarStateType.COLLAPSED) {
                onStateChanged(appBarLayout, AppBarStateType.COLLAPSED);
            }
            mCurrentState = AppBarStateType.COLLAPSED;
        } else {
            if (mCurrentState != AppBarStateType.IDLE) {
                onStateChanged(appBarLayout, AppBarStateType.IDLE);
            }
            mCurrentState = AppBarStateType.IDLE;
        }
    }

    public abstract void onStateChanged(AppBarLayout appBarLayout, @AppBarStateType.AppBarStateTypeChecker int type);
}
