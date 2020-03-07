package com.ibrahim.androidTask.Activities.Authentication.Register.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.ibrahim.androidTask.Activities.Authentication.Login.View.LoginActivity;
import com.ibrahim.androidTask.Activities.Authentication.Register.Presenter.RegisterPresenter;
import com.ibrahim.androidTask.Activities.MainActivity.View.MainActivity;
import com.ibrahim.androidTask.Models.UserModel;
import com.ibrahim.androidTask.R;
import com.ibrahim.androidTask.Utils.ParentClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import spencerstudios.com.bungeelib.Bungee;

public class RegisterActivity extends ParentClass implements RegisterView {
    UserModel userModel = new UserModel();
    RegisterPresenter registerPresenter;
    @BindView(R.id.etFirstName)
    EditText etFirstName;
    @BindView(R.id.etLastName)
    EditText etLastName;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        registerPresenter = new RegisterPresenter(RegisterActivity.this,this,userModel);
    }

    @Override
    public void goTOMainActivity() {
        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        Bungee.split(this);
    }

    @OnClick(R.id.tvEnter)
    void register() {
        registerPresenter.register(etFirstName,etLastName,etEmail,etPassword);
    }

    @OnClick(R.id.rlLogin)
    void goToLogin() {
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
        Bungee.split(this);
    }

}
