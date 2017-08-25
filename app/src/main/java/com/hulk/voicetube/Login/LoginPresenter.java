package com.hulk.voicetube.login;

import android.util.Log;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by hulk-out on 2017/8/24.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private static final String TAG = "LoginPresenter";

    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void doLogin() {
        Log.d(TAG, "doLogin: i'm coming");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.64.40.48:8080/")
                .build();
        LoginService loginService = retrofit.create(LoginService.class);
        Call<ResponseBody> call = loginService.getUser(1);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(
                    Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    @Override
    public void start() {

    }

    public interface LoginService {
        @GET("t/user/{id}")
        Call<ResponseBody> getUser(/** 这里的id表示的是上面的{id} */@Path("id") int id);
    }

}
