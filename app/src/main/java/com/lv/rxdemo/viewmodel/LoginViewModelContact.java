package com.lv.rxdemo.viewmodel;

import android.content.Context;

/**
 * Created by Lv on 2016/10/25.
 */

public interface LoginViewModelContact {

    interface LoginView {

        Context getContext();

        void redirectRegister();

        boolean validateLogin();
    }

    interface ViewModel {

        void destroy();
    }
}
