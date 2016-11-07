package com.gzfgeh.nbapp.Utils;

import android.widget.Toast;

import com.gzfgeh.nbapp.APP;
import com.gzfgeh.nbapp.R;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Description:
 * Created by guzhenfu on 2016/11/1 16:52.
 */

public abstract class RxSubUtils<T> extends Subscriber<T> {
    protected CompositeSubscription mCompositeSubscription;

    public RxSubUtils(CompositeSubscription mCompositeSubscription) {
        this.mCompositeSubscription = mCompositeSubscription;
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }
    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (!NetWorkUtils.isNetworkAvailable()) {
            Toast.makeText(APP.getContext(), APP.getContext().getString(R.string.net_error), Toast.LENGTH_SHORT).show();
        } else if (e instanceof RxUtils.ServerException) {
            Toast.makeText(APP.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(APP.getContext(), APP.getContext().getString(R.string.error), Toast.LENGTH_SHORT).show();
        }
        _onError();
    }

    @Override
    public void onCompleted() {
        if (mCompositeSubscription != null)
            mCompositeSubscription.remove(this);
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError();
}
