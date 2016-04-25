package com.jinfulin.quick_master.cache;

/**
 * Created by king on 2015/12/1.
 */
import android.content.Context;
import android.content.SharedPreferences;

import com.jinfulin.quick_master.util.UIUtils;


public class CacheUtils {
    private static final String SP_NAME = "teekart_count";
    public static final String IS_APP_FIRST_OPEN = "is_app_first_open";
    public static final String AUTO_ENTER = "auto_enter";
    public static final String NAME = "name";
    public static final String PWD = "pwd";
    public static final String CONFIG = "Config";

    private static SharedPreferences sp;
    public static String RAWCOOKIES = "rawCookies";

    public static void putBoolean(String key, Boolean value) {
        if (sp == null) {
            Context context = UIUtils.getContext();
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(String key,
                                     Boolean defValue) {
        if (sp == null) {
            Context context = UIUtils.getContext();
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defValue);
    }
    /**
     * 缓存字符串
     * @param key
     * @param value
     */
    public static void putString(String key, String value){
        if (sp == null) {
            Context context = UIUtils.getContext();
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).commit();
    }
    /**
     * 获取缓存字符串
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(String key, String defValue){
        if (sp == null) {
            Context context = UIUtils.getContext();
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp.getString(key, defValue);
    }
}
