package com.hulk.voicetube.login;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hulk.voicetube.R;
import com.hulk.voicetube.registration.RegistrationFragment;

/**
 * Created by Administrator on 20/8/2017.
 */

public class LoginFragment extends Fragment implements LoginContract.View{

    FragmentManager fragmentManager;

    private static final String TAG = "LoginFragment";

    private LoginContract.Presenter mPresenter;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new LoginPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.login_frag, container, false);

        //login
        final Button loginButton = view.findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               mPresenter.doLogin();
                                           }
                                       }
        );

        //registration
        final FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       fragmentManager = getActivity().getSupportFragmentManager();
                                       RegistrationFragment endFragment = new RegistrationFragment();
                                       FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                       fragmentTransaction.replace(R.id.container_profile, endFragment);
                                       fragmentTransaction.commit();
                                   }
                               }
        );
        return view;
    }
}