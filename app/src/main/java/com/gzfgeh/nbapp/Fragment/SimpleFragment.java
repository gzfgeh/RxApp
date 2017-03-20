package com.gzfgeh.nbapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gzfgeh.nbapp.Activity.MainActivity;
import com.gzfgeh.nbapp.Interface.LauncherBaseFragment;
import com.gzfgeh.nbapp.R;
import com.gzfgeh.nbapp.Utils.ShareUtils;

import butterknife.BindView;

/**
 * Created by guzhenfu on 16/6/6.
 */
public class SimpleFragment extends LauncherBaseFragment {
    @BindView(R.id.btn)
    Button btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_launch, container, false);

        btn.setOnClickListener(View -> {
            ShareUtils.putValue("isComeOver", true);
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
        });

        return view;
    }

    @Override
    public void startAnimation() {
    }

    @Override
    public void stopAnimation() {
    }
}
