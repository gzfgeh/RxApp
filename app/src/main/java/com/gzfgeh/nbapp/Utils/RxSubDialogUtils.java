package com.gzfgeh.nbapp.Utils;

import android.content.Context;
import android.content.DialogInterface;

import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.subscriptions.CompositeSubscription;

/**
 * Description:
 * Created by guzhenfu on 2016/11/1 17:27.
 */

public abstract class RxSubDialogUtils<T> extends RxSubUtils<T> {
    private Context mContext;
    private SweetAlertDialog dialog;
    private String msg;


    /**
     * @param context context
     * @param msg     dialog message
     */
    public RxSubDialogUtils(CompositeSubscription mCompositeSubscription, Context context, String msg) {
        super(mCompositeSubscription);
        this.mCompositeSubscription = mCompositeSubscription;
        this.mContext = context;
        this.msg = msg;
    }

    /**
     * @param context context
     */
    public RxSubDialogUtils(CompositeSubscription mCompositeSubscription, Context context) {
        this(mCompositeSubscription, context, "请稍后...");
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }
    @Override
    public void onStart() {
        super.onStart();
        if (dialog == null) {
            dialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE)
                    .setTitleText(msg);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    if(!isUnsubscribed()){
                        unsubscribe();
                    }
                }
            });
            dialog.show();
        }
    }

}
