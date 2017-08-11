package com.hulk.voicetube.blog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hulk.voicetube.R;

public class BlogFragment extends Fragment {

    public BlogFragment() {
        // Requires empty public constructor
    }

    public static BlogFragment newInstance() {
        return new BlogFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.blog_frag, container, false);
        return view;
    }
}

