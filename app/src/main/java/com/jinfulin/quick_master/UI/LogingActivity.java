package com.jinfulin.quick_master.UI;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.jinfulin.quick_master.R;
import com.jinfulin.quick_master.base.BaseActivity;
import com.jinfulin.quick_master.beans.UserInfo;
import com.jinfulin.quick_master.cache.CacheUtils;
import com.jinfulin.quick_master.net.Callback;
import com.jinfulin.quick_master.net.NetWork;
import com.jinfulin.quick_master.util.AccountManager;
import com.jinfulin.quick_master.util.ConfigManager;
import com.jinfulin.quick_master.util.CustomToast;
import com.jinfulin.quick_master.util.KeyBoardUtils;
import com.jinfulin.quick_master.util.UIUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;


import org.json.JSONObject;

/**
 * 登录界面
 *
 * @author Administrator
 */
public class LogingActivity extends BaseActivity implements OnClickListener {
    @ViewInject(R.id.bt_loging)
    private AppCompatButton bt_loging;
    @ViewInject(R.id.et_userName)
    private AppCompatEditText et_userName;
    @ViewInject(R.id.et_password)
    private AppCompatEditText et_password;
    @ViewInject(R.id.iv_chebox)
    private AppCompatCheckBox iv_chebox;
    @ViewInject(R.id.login_form)
    private ScrollView scrollView;
    @ViewInject(R.id.til_username)
    private TextInputLayout til_username;
    @ViewInject(R.id.til_password)
    private TextInputLayout til_password;

    private String password;
    private int checkBoxChoose = 1;//1表示没被选中，2代表被选中
    private String userName;
    private ProgressDialog progressDialog;
    private ProgressDialog pDialog;
    private long time = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIUtils.addActivity(this);
        baseSetContentView(R.layout.activity_login_main);
        ViewUtils.inject(this);
        init();
        setTopBarAndAction();
    }

    private void init() {
        bt_loging.setOnClickListener(this);
        String name = CacheUtils.getString(CacheUtils.NAME, "");
        String pass = CacheUtils.getString(CacheUtils.PWD, "");
        et_userName.setText(name);
        et_password.setText(pass);
        //点击空白处关闭键盘
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                KeyBoardUtils.closeKeybord(et_userName);
                return false;
            }
        });

        iv_chebox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    checkBoxChoose = 2;
                } else {
                    checkBoxChoose = 1;
                }
            }
        });
    }

    private void setTopBarAndAction() {
        setBarTitleText("登录"); // 设置Title标题
    }


    private void login() {
        //重置错误信息
        til_username.setError(" ");
        til_password.setError(" ");

        // 获取登录信息
        userName = et_userName.getText().toString();
        password = et_password.getText().toString();

        //是否取消登录
        boolean cancel = false;
        View focusView = null;


        // 校验密码
        if (TextUtils.isEmpty(password)) {
            til_password.setError("密码为空");
            focusView = til_password;
            cancel = true;
        }

        // 校验用户名
        if (TextUtils.isEmpty(userName)) {
            til_username.setError("用户名为空");
            focusView = til_username;
            cancel = true;
        }

        if (cancel) {
            //使无效的输入框获取焦点
            focusView.requestFocus();
        } else {
            //登录
            if (pDialog == null) {
                pDialog = ProgressDialog.show(this, "", UIUtils.getString(R.string.progress_content));
            }
            NetWork.getInstance().login(userName, password, new Callback() {
                @Override
                public void success(String result) {
                    Gson gson = new Gson();
                    UserInfo userInfo = gson.fromJson(result, UserInfo.class);
                    AccountManager.getInstance().setUser(userInfo);
                    if (checkBoxChoose == 2) {//保存登录信息
                        CacheUtils.putBoolean(CacheUtils.AUTO_ENTER, true);
                        CacheUtils.putString(CacheUtils.NAME, userName);
                        CacheUtils.putString(CacheUtils.PWD, password);
                    } else {
                        CacheUtils.putBoolean(CacheUtils.AUTO_ENTER, false);
                    }
                    getConfigFromNet();

                }

                @Override
                public void notice(JSONObject jsonObject, String message) {
                    if (pDialog != null) {
                        pDialog.dismiss();
                        pDialog = null;
                    }
                    CustomToast.showToast(message, LogingActivity.this);
                }

                @Override
                public void failed(String e) {
                    if (pDialog != null) {
                        pDialog.dismiss();
                        pDialog = null;
                    }
                    CustomToast.showToast(e, LogingActivity.this);
                }
            });
        }

    }

    private void getConfigFromNet() {
        NetWork.getInstance().getConfigFromNet(new Callback() {
            @Override
            public void success(String result) {
                if (pDialog != null) {
                    pDialog.dismiss();
                    pDialog = null;
                }
//                CacheUtils.putString(CacheUtils.CONFIG,reuslt);
                ConfigManager.getInstance().setConfig(result);
                startActivity(new Intent(LogingActivity.this, MainUI.class));
                LogingActivity.this.finish();
            }

            @Override
            public void notice(JSONObject jsonObject, String message) {
                if (pDialog != null) {
                    pDialog.dismiss();
                    pDialog = null;
                }

            }


            @Override
            public void failed(String e) {
                if (pDialog != null) {
                    pDialog.dismiss();
                    pDialog = null;
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_loging:
                login();
                break;

            default:
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UIUtils.removeActivity(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - time < 2000) {// 点击2次的时间小于2秒，退出程序
                UIUtils.finishAll();
            } else {
                time = System.currentTimeMillis();
            }
            return true;

        }

        return super.onKeyDown(keyCode, event);
    }


}
