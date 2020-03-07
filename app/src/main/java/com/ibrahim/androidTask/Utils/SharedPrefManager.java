package com.ibrahim.androidTask.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.ibrahim.androidTask.Models.UserModel;

public class SharedPrefManager {
    final static String SHARED_PREF_NAME = "shared";
    final static String LOGIN_STATUS = "shared_login_status";
    final static String FIRST_TIME = "shared_first_time";

    Context mContext;

    public SharedPrefManager(Context mContext) {
        this.mContext = mContext;
    }

    public Boolean getLoginStatus() {
        final SharedPreferences sharedPreferences = mContext.getSharedPreferences(
                SHARED_PREF_NAME,0);
        Boolean value = sharedPreferences.getBoolean(LOGIN_STATUS,false);
        return value;
    }

    public void setLoginStatus(Boolean status) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME,
                0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LOGIN_STATUS,status);
        editor.apply();
    }

    public Boolean isFirstTime() {
        final SharedPreferences sharedPreferences = mContext.getSharedPreferences(
                SHARED_PREF_NAME,0);
        Boolean value = sharedPreferences.getBoolean(FIRST_TIME,true);
        return value;
    }

    public void setFirstTime(Boolean status) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME,
                0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(FIRST_TIME,status);
        editor.apply();
    }


    /**
     * return userModel which hold all user data
     *
     * @return user model
     */
    public UserModel getUserDate() {
        UserModel userModel = new UserModel();
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME,0);
        userModel.setFirstName(sharedPreferences.getString("firstName",""));
        userModel.setLastName(sharedPreferences.getString("lastName",""));
        userModel.setEmail(sharedPreferences.getString("email",""));
        userModel.setPassword(sharedPreferences.getString("password",""));
        return userModel;
    }


    /**
     * saving user data to be used in profile
     *
     * @param user is the model which hold all user data
     */
    public void setUserDate(UserModel user) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("firstName",user.getFirstName());
        editor.putString("lastName",user.getLastName());
        editor.putString("email",user.getEmail());
        editor.putString("password",user.getPassword());
        editor.apply();
    }


    /**
     * this method is responsible for user logout and clearing cache
     */
    public void Logout() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        setFirstTime(false);
    }


}
