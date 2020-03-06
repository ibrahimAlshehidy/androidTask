package com.ibrahim.androidTask.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;


/**
 * Created by omarn on shadow3/5/2018.
 */

public class SharedPrefManager {
    final static String SHARED_Setting_Data = "setting_data";
    final static String SHARED_PREF_NAME = "masari_shared";
    final static String LOGIN_STATUS = "masari_shared_login_status";
    final static String FIRST_TIME = "masari_shared_first_time";
    final static String type = "masari_shared_type";
    final static String type2 = "masari_shared_type2";
    final static String serial = "serial";


    Context mContext;
    Bundle bundle = new Bundle();
    Bundle args = new Bundle();
    Bundle searchArgs = new Bundle();

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


    public String getLat() {
        final SharedPreferences sharedPreferences = mContext.getSharedPreferences(
                type,0);
        String value = sharedPreferences.getString(type,"");
        return value;
    }

    public void setLat(String lat) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(type,
                0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(type,lat);
        editor.apply();
    }


    public String getLng() {
        final SharedPreferences sharedPreferences = mContext.getSharedPreferences(
                type2,0);
        String value = sharedPreferences.getString(type2,"");
        return value;
    }

    public void setLng(String lng) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(type2,
                0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(type2,lng);
        editor.apply();
    }

    public String getSerial() {
        final SharedPreferences sharedPreferences = mContext.getSharedPreferences(
                serial,0);
        String value = sharedPreferences.getString(serial,"");
        return value;
    }

    public void setSerial(String seriall) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(serial,
                0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(serial,seriall);
        editor.apply();
    }

    /**
     * return userModel which hold all user data
     *
     * @return user model
     */
//    public Data getUserDate() {
//        Data userModel = new Data();
//        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME,0);
//        userModel.setId(sharedPreferences.getInt("id",0));
//        userModel.setEmail(sharedPreferences.getString("email",""));
//        userModel.setName(sharedPreferences.getString("name",""));
//        userModel.setMobile(sharedPreferences.getString("phone",""));
//        userModel.setStatus(sharedPreferences.getString("status",""));
//        userModel.setLat(sharedPreferences.getString("lat",""));
//        userModel.setLng(sharedPreferences.getString("lng",""));
//        userModel.setCreated_at(sharedPreferences.getString("created_at",""));
//        userModel.setImage(sharedPreferences.getString("image",""));
//        userModel.setToken(sharedPreferences.getString("token",""));
//        userModel.setVerified(sharedPreferences.getString("verified",""));
//        userModel.setOnline(sharedPreferences.getString("online",""));
//        userModel.setVip(sharedPreferences.getString("vip",""));
//        userModel.setCode(sharedPreferences.getInt("code",0));
//        userModel.setTotalRates(sharedPreferences.getInt("total_rates",0));
//        userModel.setCityId(sharedPreferences.getInt("city_id",0));
//        userModel.setCountryCodeId(sharedPreferences.getInt("country_code_id",0));
//        return userModel;
//    }

    //
//    /**
//     * saving user data to be used in profile
//     *
//     * @param user is the model which hold all user data
//     */
//    public void setUserDate(Data user) {
//        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME,0);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt("id",user.getId());
//        editor.putString("name",user.getName());
//        editor.putString("phone",user.getMobile());
//        editor.putString("email",user.getEmail());
//        editor.putString("image",user.getImage());
//        editor.putString("online",user.getOnline());
//        editor.putString("status",user.getStatus());
//        editor.putInt("code",user.getCode());
//        editor.putString("verified",user.getVerified());
//        editor.putString("lat",user.getLat());
//        editor.putString("lng",user.getLng());
//        editor.putString("created_at",user.getCreated_at());
//        editor.putString("vip",user.getVip());
//        editor.putInt("total_rates",user.getTotalRates());
//        editor.putInt("city_id",user.getCityId());
//        editor.putInt("country_code_id",user.getCountryCodeId());
//        editor.putString("token",user.getToken());
//
//
//        editor.apply();
//    }
    public void setSingleId(String singleId) {

        args.putString("singleId",singleId);
    }

    public Bundle getSingleId() {
        if (args.isEmpty()) {
        }
        return args;
    }


//    public void setSettingsData(Data settingsData) {
//        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_Setting_Data, 0);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("about", settingsData.getAbout());
//        editor.putString("policy", settingsData.getPolicy());
//        editor.putString("first_screen", settingsData.getFirstScreen());
//        editor.putString("second_screen", settingsData.getSecondScreen());
//        editor.putString("third_screen", settingsData.getThirdScreen());
//        editor.putString("logo", settingsData.getLogo());
//        editor.apply();
//
//    }
//
//    public Data getSettngsData() {
//        Data data = new Data();
//        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_Setting_Data, 0);
//        data.setAbout(sharedPreferences.getString("about",""));
//        data.setAbout(sharedPreferences.getString("policy",""));
//        data.setAbout(sharedPreferences.getString("first_screen",""));
//        data.setAbout(sharedPreferences.getString("second_screen",""));
//        data.setAbout(sharedPreferences.getString("third_screen",""));
//        data.setAbout(sharedPreferences.getString("logo",""));
//        return data;
//
//    }

    public void setSearch(String categoryId,String cityId,String type,String price,String model,String fe2a,String fe2aId,String year
            ,String kilo,String capacity,String colour,Boolean New,Boolean Used,Boolean Selling,Boolean Wanted,String previousFragment,String typeId,String plateCountry,String typeOfDelivery
            ,String typeGarageId,String transportationCompanies) {
        searchArgs.putString("categoryId",categoryId);
        searchArgs.putString("cityId",cityId);
        searchArgs.putString("type",type);
        searchArgs.putString("price",price);
        searchArgs.putString("model",model);
        searchArgs.putString("fe2a",fe2a);
        searchArgs.putString("fe2aId",fe2aId);
        searchArgs.putString("year",year);
        searchArgs.putString("kilo",kilo);
        searchArgs.putString("capacity",capacity);
        searchArgs.putString("colour",colour);
        searchArgs.putBoolean("New",New);
        searchArgs.putBoolean("Used",Used);
        searchArgs.putBoolean("Selling",Selling);
        searchArgs.putBoolean("Wanted",Wanted);
        searchArgs.putString("previousFragment",previousFragment);
        searchArgs.putString("typeId",typeId);
        searchArgs.putString("plateCountry",plateCountry);
        searchArgs.putString("typeOfDelivery",typeOfDelivery);
        searchArgs.putString("typeGarageId",typeGarageId);
        searchArgs.putString("transportationCompanies",transportationCompanies);


    }

    public Bundle getSearch() {
        if (searchArgs.isEmpty()) {
        }
        return searchArgs;

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
