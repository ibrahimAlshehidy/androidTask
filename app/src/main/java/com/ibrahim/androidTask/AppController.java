package com.ibrahim.androidTask;

import android.content.Context;
import android.content.res.Configuration;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;


/**
 * Created by ibrahim on 19/03/2018.
 */

public class AppController extends MultiDexApplication {
    private static AppController mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;


    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    public static AppController getContext() {
        return mContext;
    } // fun of getContext


}
