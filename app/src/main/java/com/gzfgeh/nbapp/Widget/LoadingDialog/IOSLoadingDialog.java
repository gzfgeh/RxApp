package com.gzfgeh.nbapp.Widget.LoadingDialog;

import android.content.Context;

import com.gzfgeh.iosdialog.IOSDialog;

/**
 * Description:
 * Created by guzhenfu on 17/1/8.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */

public class IOSLoadingDialog implements ILoadingDialog {
    private IOSDialog dialog;

    public static IOSLoadingDialog getInstance(){
        return IOSLoadingDialogHolder.instance;
    }

    private static class IOSLoadingDialogHolder{
        private static final IOSLoadingDialog instance = new IOSLoadingDialog();
    }

    @Override
    public void showDialog(Context context) {
        if (dialog == null){
            dialog = new IOSDialog(context).builder()
                    .setLoadingView();
            dialog.show();
        }
    }

    @Override
    public void hideDialog() {
        if (dialog != null){
            dialog.dismiss();
            dialog = null;
        }
    }
}
