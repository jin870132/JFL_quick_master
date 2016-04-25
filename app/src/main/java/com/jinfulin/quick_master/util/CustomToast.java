package com.jinfulin.quick_master.util;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;


import net.steamcrafted.loadtoast.LoadToast;

/**
 * 防止toast多次弹出的工具类
 * @author 日常使用账户
 *
 */
public class CustomToast {

    private static Toast mToast;
    private static LoadToast lt;
    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        public void run() {
            mToast.cancel();
        }
    };
    private static Runnable r2 = new Runnable() {
        public void run() {
            lt.success();
        }
    };


    public static void showToast(String text) {
        Context mContext = UIUtils.getContext();
        mHandler.removeCallbacks(r);
        if (mToast != null)
            mToast.setText(text);
        else
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        mHandler.postDelayed(r, 2000);

        mToast.show();



    }
    public static void showToast(String text,Context mContext) {
        lt = new LoadToast(mContext);
        lt.setText(text);
//        lt.setTextColor(Color.RED).setBackgroundColor(Color.GREEN).setProgressColor(Color.BLUE);
        mHandler.postDelayed(r2, 2000);
        lt.show();
    }

    public static void showToast(int resId) {
        showToast(UIUtils.getContext().getResources().getString(resId));
    }

}
