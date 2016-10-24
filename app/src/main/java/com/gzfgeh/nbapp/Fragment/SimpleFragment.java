package com.gzfgeh.nbapp.Fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gzfgeh.nbapp.Activity.MainActivity;
import com.gzfgeh.nbapp.Activity.SplashActivity;
import com.gzfgeh.nbapp.Interface.LauncherBaseFragment;
import com.gzfgeh.nbapp.R;
import com.gzfgeh.nbapp.Utils.ShareUtils;
import com.gzfgeh.nbapp.databinding.FragmentLaunchBinding;

/**
 * Created by guzhenfu on 16/6/6.
 */
public class SimpleFragment extends LauncherBaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentLaunchBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_launch, container, false);
        binding.setBtnText("立即使用");
        binding.setBtnClick(View-> {
            ShareUtils.putValue("isComeOver", true);
            startActivity(new Intent(getActivity(), MainActivity.class));
        });
        return binding.getRoot();
    }

    @Override
    public void startAnimation() {
    }

    @Override
    public void stopAnimation() {
    }
}
