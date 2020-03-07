package com.ibrahim.androidTask.Activities.Authentication.Login.Presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

import com.ibrahim.androidTask.Activities.Authentication.Login.View.LoginView;
import com.ibrahim.androidTask.R;
import com.ibrahim.androidTask.Utils.ParentClass;

public class LoginPresenter extends ParentClass implements LoginViewPresenter {
    Context context;
    LoginView loginView;

    public LoginPresenter(Context context,LoginView loginView) {
        this.context = context;
        this.loginView = loginView;
    }

    @Override
    public void login(EditText etEmail,EditText etPassword) {
        boolean cancel = false;
        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError(context.getString(R.string.enterPassword));
            cancel = true;
        }
        if (TextUtils.isEmpty(etEmail.getText().toString())) {
            etEmail.setError(context.getString(R.string.enterEmail));
            cancel = true;
        }
        if (!isEmailValid(etEmail.getText().toString())) {
            etEmail.setError(context.getString(R.string.enterValidEmail));
            cancel = true;
        }
        if (!sharedPrefManager.getUserDate().getEmail().equals(etEmail.getText().toString())) {
            etEmail.setError(context.getString(R.string.enterValidEmail));
            cancel = true;
        }
        if (!sharedPrefManager.getUserDate().getPassword().equals(etPassword.getText().toString())) {
            etPassword.setError(context.getString(R.string.enterCorrectPassword));
            cancel = true;
        }

        if (cancel) {
        } else {
            sharedPrefManager.setLoginStatus(true);
            loginView.goTOMainActivity();
        }
    }
}
