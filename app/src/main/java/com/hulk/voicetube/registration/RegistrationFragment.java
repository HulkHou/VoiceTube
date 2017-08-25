package com.hulk.voicetube.registration;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.hulk.voicetube.login.LoginFragment;
import com.hulk.voicetube.R;

/**
 * Created by Administrator on 20/8/2017.
 */

public class RegistrationFragment extends Fragment {

    FragmentManager fragmentManager;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.reg_frag, container, false);

        final FrameLayout clear = view.findViewById(R.id.clear);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getActivity().getSupportFragmentManager();
                LoginFragment endFragment = new LoginFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_profile, endFragment);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

}