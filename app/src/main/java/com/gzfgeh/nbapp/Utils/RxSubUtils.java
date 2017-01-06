package com.gzfgeh.nbapp.Utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.gzfgeh.iosdialog.IOSDialog;
import com.gzfgeh.nbapp.APP;
import com.gzfgeh.nbapp.R;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Description:
 * Created by guzhenfu on 2016/11/1 16:52.
 */

public abstract class RxSubUtils<T> extends Subscriber<T> {
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private IOSDialog dialog;
    private String msg;

    public RxSubUtils(){}

    public RxSubUtils(CompositeSubscription mCompositeSubscription) {
        this.mCompositeSubscription = mCompositeSubscription;
    }

    /**
     * @param context context
     * @param msg     dialog message
     */
    public RxSubUtils(CompositeSubscription mCompositeSubscription, Context context, String msg) {
        this.mCompositeSubscription = mCompositeSubscription;
        this.mContext = context;
        this.msg = msg;
    }

    /**
     * @param context context
     */
    public RxSubUtils(CompositeSubscription mCompositeSubscription, Context context) {
        this(mCompositeSubscription, context, "请稍后...");
    }

    /**
     * 这个一定要有 Presenter的逻辑在这里处理
     * @param t
     */
    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (!NetWorkUtils.isNetworkAvailable()) {
            ToastUtil.show(R.string.net_error);
        } else if (e instanceof RxUtils.ServerException) {
//            String s = ((RxUtils.ServerException)e).getMsg();
//            //token 过期了
//            if(TextUtils.equals(s, RxUtils.TOKEN_OVER_TIME) && mContext != null){
//                Utils.startLoginActivity(mContext);
//            }
//            ToastUtil.show(s);
        } else {
            ToastUtil.show(R.string.error);
        }

        if (dialog!= null){
            dialog.dismiss();
            dialog = null;
        }
        _onError();
    }

    @Override
    public void onCompleted() {
        if (mCompositeSubscription != null)
            mCompositeSubscription.remove(this);

        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mContext != null && dialog == null) {
            dialog = new IOSDialog(mContext).builder()
                    .setLoadingView();
            dialog.show();
        }
    }

    protected abstract void _onNext(T t);

    /**
     * 错误处理，需要的话重写这个方法
     */
    protected void _onError(){

    }

}
