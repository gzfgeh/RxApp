package com.gzfgeh.nbapp.Utils;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

import com.gzfgeh.nbapp.APP;

/**
 * @Description: (适配不同系统版本) 在低版本中可以线程同步的Toast，高版本中不适用，不要用
 */
public class ToastUtil {
    private static Toast mToast;
    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        public void run() {
            mToast.cancel();
        }
    };

    public static void showToast(Context mContext, String text, int duration) {
        mHandler.removeCallbacks(r);
        if (mToast != null)
            mToast.setText(text);
        else
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        mHandler.postDelayed(r, duration);

        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    public static void show(String s) {
        showToast(APP.getContext(), s, 1000);
    }

    public static void show(int resId) {
        showToast(APP.getContext(), APP.getContext().getString(resId), 1000);
    }

}