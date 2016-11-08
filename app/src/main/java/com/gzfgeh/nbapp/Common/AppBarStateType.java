package com.gzfgeh.nbapp.Common;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Description:
 * Created by guzhenfu on 2016/11/8 16:57.
 */

public class AppBarStateType {
    public static final int EXPANDED = 0;
    public static final int COLLAPSED = 1;
    public static final int IDLE = 2;

    @IntDef({EXPANDED, COLLAPSED, IDLE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AppBarStateTypeChecker {}

}
