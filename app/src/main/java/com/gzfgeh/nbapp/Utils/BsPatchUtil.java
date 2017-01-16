package com.gzfgeh.nbapp.Utils;

/**
 * Description:
 * Created by guzhenfu on 17/1/16.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */

public class BsPatchUtil {
    static {
        System.loadLibrary("bsdiff");
    }

    public static native int patch(String oldApk, String newApk, String patch);
}
