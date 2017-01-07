package com.gzfgeh.nbapp.Widget.LoadingDialog;

/**
 * Description:
 * Created by guzhenfu on 17/1/8.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */

public class LoadingDialogManager {

    public static ILoadingDialog getLoadingDialog(){
        return IOSLoadingDialog.getInstance();
    }
}
