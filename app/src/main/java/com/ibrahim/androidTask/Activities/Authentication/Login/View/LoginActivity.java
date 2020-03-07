package com.ibrahim.androidTask.Activities.Authentication.Login.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.ibrahim.androidTask.Activities.Authentication.Login.Presenter.LoginPresenter;
import com.ibrahim.androidTask.Activities.Authentication.Register.View.RegisterActivity;
import com.ibrahim.androidTask.Activities.MainActivity.View.MainActivity;
import com.ibrahim.androidTask.R;
import com.ibrahim.androidTask.Utils.ParentClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import spencerstudios.com.bungeelib.Bungee;

public class LoginActivity extends ParentClass implements LoginView {
    LoginPresenter loginPresenter;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(LoginActivity.this,this);

    }


    @Override
    public void goTOMainActivity() {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        Bungee.split(this);
    }

    @OnClick(R.id.tvEnter)
    void login() {
        loginPresenter.login(etEmail,etPassword);
    }

    @OnClick(R.id.rlRegister)
    void goToRegister() {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
        Bungee.split(this);
    }

}
