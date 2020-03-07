package com.ibrahim.androidTask.Activities.Splash.Presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.ibrahim.androidTask.Activities.Authentication.Login.View.LoginActivity;
import com.ibrahim.androidTask.Activities.MainActivity.View.MainActivity;
import com.ibrahim.androidTask.Utils.ParentClass;

import spencerstudios.com.bungeelib.Bungee;

public class SplashPresenter extends ParentClass implements SplashViewPresenter {
    Context context;

    public SplashPresenter(Context context) {
        this.context = context;

    }

    @Override
    public void go() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sharedPrefManager.getLoginStatus()) {
                    Intent intent = new Intent(context,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    context.startActivity(intent);
                    Bungee.split(context);


                } else {
                    Intent intent = new Intent(context,LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    context.startActivity(intent);
                    Bungee.split(context);
                }
            }
        },3000);


    }
}
