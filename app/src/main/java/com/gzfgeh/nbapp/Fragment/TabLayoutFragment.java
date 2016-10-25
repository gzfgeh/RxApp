package com.gzfgeh.nbapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gzfgeh.nbapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabLayoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabLayoutFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    public static TabLayoutFragment newInstance(String param1) {
        TabLayoutFragment fragment = new TabLayoutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_layout, container, false);
    }

}
