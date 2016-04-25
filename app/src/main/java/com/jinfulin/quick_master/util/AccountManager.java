package com.jinfulin.quick_master.util;

import android.content.Intent;

import com.google.gson.Gson;
import com.jinfulin.quick_master.UI.LogingActivity;
import com.jinfulin.quick_master.beans.UserInfo;
import com.jinfulin.quick_master.cache.CacheUtils;
import com.jinfulin.quick_master.factory.FragmentFactory;

/**
 * Created by king on 2015/12/02.
 */
public class AccountManager {

    private UserInfo mUser;
    private String mOauth;
    private Gson gson;

    private static AccountManager sInstance;
    private AccountManager() {
        gson = new Gson();
    }
    public static AccountManager getInstance() {
        if (sInstance == null) {
            synchronized ( AccountManager.class) {
                if (sInstance == null)
                    sInstance = new  AccountManager();

            }
        }
        return sInstance;
    }
    public void logout() {
        mUser = null;
        mOauth = null;
        sInstance = null;
        CacheUtils.putBoolean(CacheUtils.AUTO_ENTER, false);
        CacheUtils.putString(CacheUtils.NAME, "");
        CacheUtils.putString(CacheUtils.PWD, "");
        CacheUtils.putString(CacheUtils.RAWCOOKIES, "");
        FragmentFactory.clearnMap();
        goLogingActivity();

    }
    /**
     * 获取用户信息
     * @return
     */
    public UserInfo getUser() {
        return mUser;
    }

    /**
     * 设置用户信息
     *
     * @param user
     */
    public void setUser(UserInfo user) {
        this.mUser = user;
    }

    /**
     * 判断是否登录
     *
     * @return
     */
    public boolean isLogin() {
        if (mUser == null ||  mOauth == null ) {// 登录失效
            return false;
        }
        return true;
    }


    public static void goLogingActivity(){
        Intent itent = new Intent( UIUtils.getContext(), LogingActivity.class);
        itent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        UIUtils.getContext().startActivity(itent);
    }
    public  void setCookie(String rawCookies){
        CacheUtils.putString(CacheUtils.RAWCOOKIES, rawCookies);
    }
    public  String getCookie(){
        return  CacheUtils.getString(CacheUtils.RAWCOOKIES, "");
    }

}
