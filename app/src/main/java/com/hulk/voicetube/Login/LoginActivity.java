package com.hulk.voicetube.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hulk.voicetube.R;

/**
 * Created by Administrator on 20/8/2017.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_login, new LoginFragment()).commit();

    }
}