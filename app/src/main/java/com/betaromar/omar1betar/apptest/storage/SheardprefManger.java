package com.betaromar.omar1betar.apptest.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.betaromar.omar1betar.apptest.models.User;

public class SheardprefManger {
    private static final String MY_SHEARD_PREF_NAME ="my_sheard_preff";
    private static SheardprefManger mInstance;
    private Context mCtx;

    private SheardprefManger(Context mCtx){
        this.mCtx = mCtx;

    }
    public static synchronized SheardprefManger getInstance(Context mCtx){
        if(mInstance == null){
            mInstance = new SheardprefManger(mCtx);
        }
        return mInstance;
    }
    public void saveUser (User user){
        SharedPreferences sharedPreferences =mCtx.getSharedPreferences(MY_SHEARD_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id",user.getId());
        editor.putString("email",user.getEmail());
        editor.putString("name", user.getName());
        editor.putString("school",user.getSchool());
        editor.apply();

    }
    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences =mCtx.getSharedPreferences(MY_SHEARD_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id",-1)!= -1;

    }
    public User getUser(){
        SharedPreferences sharedPreferences =mCtx.getSharedPreferences(MY_SHEARD_PREF_NAME,Context.MODE_PRIVATE);

        return new User(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("email",null),
                sharedPreferences.getString("name",null),
                sharedPreferences.getString("school",null)

        );
    }
    public void cear(){
        SharedPreferences sharedPreferences =mCtx.getSharedPreferences(MY_SHEARD_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
