package com.hulk.voicetube.learning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hulk.voicetube.R;

public class ChannelsFragment extends Fragment {

    public ChannelsFragment() {
        // Requires empty public constructor
    }

    public static ChannelsFragment newInstance() {
        return new ChannelsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.channels_frag, container, false);
        return view;
    }
}

