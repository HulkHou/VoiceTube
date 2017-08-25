package com.hulk.voicetube.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hulk.voicetube.login.LoginFragment;
import com.hulk.voicetube.R;

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";
    FragmentManager fragmentManager;

    public ProfileFragment() {
        // Requires empty public constructor
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_frag, container, false);

        //login_now
        final Button loginButton = view.findViewById(R.id.button_login_now);
        loginButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               fragmentManager = getActivity().getSupportFragmentManager();
                                               LoginFragment loginFragment = new LoginFragment();
                                               FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                               fragmentTransaction.replace(R.id.container_profile, loginFragment);
                                               fragmentTransaction.commit();
                                           }
                                       }
        );

        return view;
    }
}

