package com.gzfgeh.nbapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gzfgeh.nbapp.Interface.LauncherBaseFragment;

/**
 * Created by guzhenfu on 16/6/6.
 */
public class SimpleFragment extends LauncherBaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(40);
        tv.setText("just test");
        return tv;
    }

    @Override
    public void startAnimation() {
    }

    @Override
    public void stopAnimation() {
    }
}
