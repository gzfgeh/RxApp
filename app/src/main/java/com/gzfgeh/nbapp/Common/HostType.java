package com.gzfgeh.nbapp.Common;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Description:
 * Created by guzhenfu on 2016/10/31 17:38.
 */

public class HostType {

    /**
     * 多少种Host类型
     */
    public static final int TYPE_COUNT = 3;

    /**
     * 网易新闻视频的host
     */
    public static final int JU_HE_NEWS = 1;

    /**
     * 新浪图片的host
     */
    public static final int GANK_IO_DATA= 2;

    /**
     * 新闻详情html图片的host
     */
    public static final int NEWS_DETAIL_HTML_PHOTO = 3;

    /**
     * 替代枚举的方案，使用IntDef保证类型安全
     */
    @IntDef({JU_HE_NEWS, GANK_IO_DATA, NEWS_DETAIL_HTML_PHOTO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface HostTypeChecker {}
}
