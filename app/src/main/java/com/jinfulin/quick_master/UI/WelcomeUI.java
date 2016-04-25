package com.jinfulin.quick_master.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.jinfulin.quick_master.R;
import com.jinfulin.quick_master.beans.UserInfo;
import com.jinfulin.quick_master.cache.CacheUtils;
import com.jinfulin.quick_master.net.Callback;
import com.jinfulin.quick_master.net.NetWork;
import com.jinfulin.quick_master.util.AccountManager;
import com.jinfulin.quick_master.util.ConfigManager;
import com.jinfulin.quick_master.util.CustomToast;
import com.jinfulin.quick_master.util.UIUtils;


import org.json.JSONObject;

public class WelcomeUI extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIUtils.addActivity(this);
        setContentView(R.layout.welcome_activity);
        init();
    }

    private void init() {
        ImageView iv = (ImageView) findViewById(R.id.iv_loading);
        Animation anim = new AlphaAnimation(0.8f, 1.0f);
        anim.setDuration(1500);
        iv.startAnimation(anim);
        anim.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                //用户是否点击过 开始体验
                boolean isFirstOpen = CacheUtils.getBoolean(CacheUtils.IS_APP_FIRST_OPEN, true);
                if (isFirstOpen) {
                    System.out.println("用户没有打开过，打开引导界面");
                    startActivity(new Intent(WelcomeUI.this, GuideUI.class));
                    finish();
                } else {
                    System.out.println("用户打开过，打开登录界面");
                    autoLoging();
//					startActivity(new Intent(WelcomeUI.this, MainUI.class));
                }

            }
        });
    }


    /**
     * 判断是否自动登陆
     */
    public void autoLoging() {
        //判断是否是自动登录
        boolean autoEnter = CacheUtils.getBoolean(CacheUtils.AUTO_ENTER, false);
        if (autoEnter) {
            //进行GuideActivity联网登录
            String username = CacheUtils.getString(CacheUtils.NAME, "");
            String pwd = CacheUtils.getString(CacheUtils.PWD, "");
            login(username, pwd);
        } else {
            goLoginActivity();
        }
    }

    /**
     * 进行联网登录
     */
    private void login(String userName, String password) {
        if (TextUtils.isEmpty(userName)) {
            goLoginActivity();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            goLoginActivity();
            return;
        }
        NetWork.getInstance().login(userName, password, new Callback() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                UserInfo userInfo = gson.fromJson(result, UserInfo.class);
                AccountManager.getInstance().setUser(userInfo);
                getConfigFromNet();
            }

            @Override
            public void notice(JSONObject jsonObject, String message) {
                goLoginActivity();
            }


            @Override
            public void failed(String e) {
                goLoginActivity();
            }
        });
    }

    private void getConfigFromNet() {
        NetWork.getInstance().getConfigFromNet(new Callback() {
            @Override
            public void success(String result) {
                ConfigManager.getInstance().setConfig(result);
                startActivity(new Intent(WelcomeUI.this, MainUI.class));
                WelcomeUI.this.finish();
            }

            @Override
            public void notice(JSONObject jsonObject, String message) {
                CustomToast.showToast(message);
                goLoginActivity();
            }

            @Override
            public void failed(String e) {
                CustomToast.showToast(e);
                goLoginActivity();
            }
        });
    }

    private void goLoginActivity() {
        WelcomeUI.this.startActivity(new Intent(WelcomeUI.this, LogingActivity.class));
        WelcomeUI.this.finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        UIUtils.removeActivity(this);
    }
}
