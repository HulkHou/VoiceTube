package com.hulk.voicetube.Login;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.transition.TransitionInflater;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hulk.voicetube.R;

/**
 * Created by Administrator on 20/8/2017.
 */

public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    String TransitionName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.login_frag, container, false);

        final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        if (getArguments() != null) {
            final Bundle bundle = getArguments();
            if (bundle != null) {
                TransitionName = bundle.getString("TRANS_NAME");
            }

            //set transition
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                fab.setTransitionName(TransitionName);
            }
        }

        //registration
        fab.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       RegistrationFragment endFragment = new RegistrationFragment();

                                       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                           fab.setTransitionName("trans_clear");

                                           setSharedElementReturnTransition(TransitionInflater.from(
                                                   getActivity()).inflateTransition(R.transition.change_image_trans));
                                           setExitTransition(TransitionInflater.from(
                                                   getActivity()).inflateTransition(android.R.transition.fade));

                                           endFragment.setSharedElementEnterTransition(TransitionInflater.from(
                                                   getActivity()).inflateTransition(R.transition.change_image_trans));
                                           endFragment.setEnterTransition(TransitionInflater.from(
                                                   getActivity()).inflateTransition(android.R.transition.fade));

                                           TransitionName = fab.getTransitionName();

                                           //set transition
                                           Bundle bundle = new Bundle();
                                           bundle.putString("TRANS_NAME", TransitionName);
                                           endFragment.setArguments(bundle);
                                           FragmentManager fragmentManager = getFragmentManager();
                                           fragmentManager.beginTransaction()
                                                   .replace(R.id.container_login, endFragment)
                                                   .addSharedElement(fab, TransitionName)
                                                   .commit();

                                       } else {

                                       }


                                   }
                               }
        );
        return view;
    }
}