package com.hulk.voicetube.login;

import com.hulk.voicetube.BasePresenter;
import com.hulk.voicetube.BaseView;

/**
 * Created by hulk-out on 2017/8/24.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void doLogin();
    }
}
