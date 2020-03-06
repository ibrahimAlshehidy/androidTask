package com.ibrahim.androidTask.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.ibrahim.androidTask.R;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;


/**
 * Created by cz on 19/03/2018.
 */

public class AppController extends MultiDexApplication {
    SharedPreferences sharedPreferences_language;
    private static AppController mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        sharedPreferences_language = getSharedPreferences("language",MODE_PRIVATE);
        initLanguage();
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

    public static void initLanguage() {

        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/Cairo_Regular.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    } // fun of initLanguage


}
