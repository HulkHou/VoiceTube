package com.hulk.voicetube.blog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hulk.voicetube.R;

public class CollectionFragment extends Fragment {

    public CollectionFragment() {
        // Requires empty public constructor
    }

    public static CollectionFragment newInstance() {
        return new CollectionFragment();
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

