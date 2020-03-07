package com.ibrahim.androidTask.Activities.Authentication.Register.Presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

import com.ibrahim.androidTask.Activities.Authentication.Register.View.RegisterView;
import com.ibrahim.androidTask.Models.UserModel;
import com.ibrahim.androidTask.R;
import com.ibrahim.androidTask.Utils.ParentClass;

public class RegisterPresenter extends ParentClass implements RegisterViewPresenter {
    Context context;
    RegisterView registerView;
    UserModel userModel;

    public RegisterPresenter(Context context,RegisterView registerView,UserModel userModel) {
        this.context = context;
        this.registerView = registerView;
        this.userModel = userModel;
    }

    @Override
    public void register(EditText etFirstName,EditText etLastName,EditText etEmail,EditText etPassword) {
        boolean cancel = false;
        if (TextUtils.isEmpty(etFirstName.getText().toString())) {
            etFirstName.setError(context.getString(R.string.enterFirstName));
            cancel = true;
        }
        if (TextUtils.isEmpty(etLastName.getText().toString())) {
            etLastName.setError(context.getString(R.string.enterLastName));
            cancel = true;
        }
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

        if (cancel) {
        } else {
            userModel.setFirstName(etFirstName.getText().toString());
            userModel.setLastName(etLastName.getText().toString());
            userModel.setEmail(etEmail.getText().toString());
            userModel.setPassword(etPassword.getText().toString());
            sharedPrefManager.setUserDate(userModel);
            sharedPrefManager.setLoginStatus(true);
            registerView.goTOMainActivity();

        }
    }
}
